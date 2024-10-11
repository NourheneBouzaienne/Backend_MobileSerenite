package com.app.ClientService.services;
import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsTextualMessage;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OtpSmsService {

    private static final String API_URL = "https://y3qdp9.api.infobip.com/2fa/2/pin";
    private static final String AUTHORIZATION_HEADER = "App 6684f4553f820754bc5710993b5e3519-c26ee060-c768-4d3e-9164-307cdd938bbe";
    private static final String APPLICATION_ID = "défaut";
    private static final String MESSAGE_ID = "yourRealMessageId";

    private final OkHttpClient client = new OkHttpClient();

    public String sendOtpSms(String phoneNumber, String otp) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            String jsonBody = String.format(
                    "{\"applicationId\":\"%s\",\"messageId\":\"%s\",\"from\":\"ServiceSMS\",\"to\":\"%s\"}",
                    APPLICATION_ID, MESSAGE_ID, phoneNumber);
            System.out.println("JSON Body: " + jsonBody);

            RequestBody body = RequestBody.create(mediaType, jsonBody);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .method("POST", body)
                    .addHeader("Authorization", AUTHORIZATION_HEADER)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return "OTP SMS sent successfully to " + phoneNumber;
            } else {
                System.out.println("Failed to send OTP SMS: " + response.code() + " " + response.message());
                return "Failed to send OTP SMS: " + response.code() + " " + response.message();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send OTP SMS: " + e.getMessage();
        }
    }
}