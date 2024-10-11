package com.app.ClientService.services;


import com.app.ClientService.dao.OtpVerificationRepository;
import com.app.ClientService.models.OtpVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.TimeZone;

@Service
public class OtpService {

    @Autowired
    private OtpVerificationRepository otpVerificationRepository;

    private final String httpLogin = "ami";
    private final String httpPwd = "FfVf3nnx";
    private final String wbLogin = "AA02";
    private final String wbPwd = "xJvPv1Jg";
    private final String wbAccount = "AMI";
    private final String application = "AMI";

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void generateAndSaveOtp(String numTel) {
        String otp = generateOtp();

        // Définir l'expiration à 1 minute (60000 millisecondes)
        long now = System.currentTimeMillis();
        long expiration = now + 60000; // 1 minute d'expiration

        // Sauvegarder l'OTP avec l'expiration dans la base de données
        saveOtp(numTel, otp, expiration);
    }

    public void sendOtp(String numTel, String otp) throws IOException {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(httpLogin, httpPwd);

        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String date = dateFormat.format(today);

        String url = "https://wbm.tn/wbmonitor/send/webapi/v4/send_generic";
        String requestBody = "{"
                + "\"login\": \"" + wbLogin + "\","
                + "\"pass\": \"" + wbPwd + "\","
                + "\"compte\": \"" + wbAccount + "\","
                + "\"dest_num\": \"" + numTel + "\","
                + "\"msg\": \"Your OTP code is: " + otp + "\","
                + "\"type\": \"0\","
                + "\"auto_detect\": \"1\","
                + "\"dt\": \"" + date + "\","
                + "\"hr\": \"00\","
                + "\"mn\": \"00\","
                + "\"label\": \"" + application + "\","
                + "\"ref\": \"OTP Verification\""
                + "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IOException("Failed to send SMS: " + response);
        }
    }
    public void resendOtp(String numTel, String otp) throws IOException {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(httpLogin, httpPwd);

        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String date = dateFormat.format(today);

        String url = "https://wbm.tn/wbmonitor/send/webapi/v4/send_generic";
        String requestBody = "{"
                + "\"login\": \"" + wbLogin + "\","
                + "\"pass\": \"" + wbPwd + "\","
                + "\"compte\": \"" + wbAccount + "\","
                + "\"dest_num\": \"" + numTel + "\","
                + "\"msg\": \"Votre Code de vérification est : " + otp + "\","
                + "\"type\": \"0\","
                + "\"auto_detect\": \"1\","
                + "\"dt\": \"" + date + "\","
                + "\"hr\": \"00\","
                + "\"mn\": \"00\","
                + "\"label\": \"" + application + "\","
                + "\"ref\": \"OTP Verification\""
                + "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IOException("Failed to send SMS: " + response);
        }
    }

    public void saveOtp(String numTel, String otp, long expiration) {
        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setNumTel(numTel);
        otpVerification.setOtp(otp);
        otpVerification.setVerified(false);
        otpVerification.setExpirationTime(new Date(expiration)); // Convertir le timestamp en objet Date
        otpVerificationRepository.save(otpVerification);
    }


    public boolean verifyOtp(String numTel, String otp) {
        Optional<OtpVerification> otpVerificationOpt = otpVerificationRepository.findByNumTelAndOtp(numTel, otp);
        if (otpVerificationOpt.isPresent()) {
            OtpVerification otpVerification = otpVerificationOpt.get();
            if (!otpVerification.isVerified()) {
                // Vérifier si l'OTP est encore valide
                Date expirationTime = otpVerification.getExpirationTime();
                Date now = new Date();
                if (expirationTime != null && expirationTime.after(now)) {
                    otpVerification.setVerified(true);
                    otpVerificationRepository.save(otpVerification);
                    return true; // OTP valide
                }
            }
        }
        return false; // OTP invalide ou expiré
    }
}