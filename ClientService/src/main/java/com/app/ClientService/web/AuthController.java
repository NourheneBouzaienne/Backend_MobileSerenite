package com.app.ClientService.web;


import com.app.ClientService.beans.Contrat;
import com.app.ClientService.dao.RoleRepository;
import com.app.ClientService.dao.UserRepository;
import com.app.ClientService.models.Client;
import com.app.ClientService.models.ERole;
import com.app.ClientService.models.Role;
import com.app.ClientService.models.User;
import com.app.ClientService.payload.request.LoginRequest;
import com.app.ClientService.payload.request.OtpVerificationRequest;
import com.app.ClientService.payload.request.SignupRequest;
import com.app.ClientService.payload.response.JwtResponse;
import com.app.ClientService.payload.response.MessageResponse;
import com.app.ClientService.proxies.MiddlewarePortefeuille;
import com.app.ClientService.security.jwt.JwtUtils;
import com.app.ClientService.security.services.UserDetailsImpl;
import com.app.ClientService.services.EmailService;
import com.app.ClientService.services.OtpService;
import com.app.ClientService.services.OtpSmsService;
import com.app.ClientService.services.UserService;
import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final MiddlewarePortefeuille Middlewareportefeuille;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    private final String INFOBIP_API_URL = "https://y3qdp9.api.infobip.com/2fa/2/pin";
    private final String INFOBIP_API_KEY = "0ca03af9034a70790796cc1f469cf823-0d038bc2-19ad-40c0-8561-f240ac4650b6";
    @Autowired
    private OtpSmsService otpSmsService;

    @Autowired
    private OtpService otpService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    UserService userService;

    private final String httpLogin = "ami";
    private final String httpPwd = "FfVf3nnx";
    private final String wbLogin = "AA02";
    private final String wbPwd = "xJvPv1Jg";
    private final String wbAccount = "AMI";
    private final String application = "your application name - v1.0";
    private final String label = "AMI";
    private final String reference = "test generic SMS api v4";

    public AuthController(MiddlewarePortefeuille middlewareportefeuille) {
        Middlewareportefeuille = middlewareportefeuille;
    }




    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam String numTel,
            @RequestParam String otp,
            @RequestParam String newPassword) {

        // Vérification de l'OTP
        if (!otpService.verifyOtp(numTel, otp)) {
            return ResponseEntity.status(400).body("Invalid OTP");
        }

        // Changer le mot de passe
        boolean isChanged = userService.changePassword(numTel, newPassword);
        if (isChanged) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to change password");
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/sendOtpF")
    public ResponseEntity<String> sendOtpF(@RequestParam String numTel) {
        try {
            // Vérifier si le numéro de téléphone est vide ou null
            if (numTel == null || numTel.isEmpty()) {
                return ResponseEntity.badRequest().body("Numéro de téléphone requis");
            }

            // Générer un OTP
            String otp = otpService.generateOtp();

            // Envoyer l'OTP et le sauvegarder
            otpService.sendOtp(numTel, otp);
            otpService.saveOtp(numTel, otp, System.currentTimeMillis() + 60000);

            return ResponseEntity.ok("OTP sent successfully");
        } catch (IllegalArgumentException e) {
            // Si le numéro de téléphone est invalide
            return ResponseEntity.badRequest().body("Numéro de téléphone invalide");
        } catch (IOException e) {
            // Si une erreur survient lors de l'envoi de l'OTP
            return ResponseEntity.status(500).body("Failed to send OTP");
        }
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<String> resendOtp(@RequestParam String numTel) {
        String otp = otpService.generateOtp();
        try {
            otpService.sendOtp(numTel, otp);
            otpService.saveOtp(numTel, otp, System.currentTimeMillis() + 60000);

            return ResponseEntity.ok("OTP sent successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to send OTP");
        }
    }


    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam String numTel, @RequestParam String otp) {
        boolean isVerified = otpService.verifyOtp(numTel, otp);
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }

    @PostMapping("/completeRegistration")
    public ResponseEntity<?> registerUserF(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String activationCode = UUID.randomUUID().toString();
        // Create new user's account

        User user = new Client(
                signUpRequest.getActivationCode(),
                signUpRequest.isEnabled(),
                signUpRequest.isAuthentificated(),
                signUpRequest.getName(),
                signUpRequest.getPrenom(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getNumTel(),
                signUpRequest.getDateNaissance(),
                signUpRequest.getCin(),
                encoder.encode(signUpRequest.getPassword())
        );

        user.setEnabled(false);
        user.setAuthentificated(false);
        user.setActivationCode(activationCode);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "gestionnaire":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User est ajouté avec succés.");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendSMS(@RequestParam String destNum, @RequestParam String msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(httpLogin, httpPwd);

        String url = "https://wbm.tn/wbmonitor/send/webapi/v4/send_generic";
        String requestBody = "{"
                + "\"login\": \"" + wbLogin + "\","
                + "\"pass\": \"" + wbPwd + "\","
                + "\"compte\": \"" + wbAccount + "\","
                + "\"dest_num\": \"" + destNum + "\","
                + "\"msg\": \"" + msg + "\","
                + "\"type\": \"0\","
                + "\"auto_detect\": \"1\","
                + "\"dt\": \"07/06/2024\","
                + "\"hr\": \"15\","
                + "\"mn\": \"36\","
                + "\"label\": \"" + label + "\","
                + "\"ref\": \"" + reference + "\""
                + "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }

/*    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtpCode(@RequestBody String numTel) {
        // Supprime tous les caractères non numériques du numéro de téléphone
        numTel = numTel.replaceAll("[^0-9]", "");

        // Valide la longueur du numéro de téléphone
        if (numTel.length() == 0 || numTel.length() > 50) {
            return ResponseEntity.badRequest().body("Invalid phone number format");
        }

        // Génère un code OTP
        String otp = otpService.generateOtp();

        try {
            // Envoie l'OTP par SMS
            otpService.sendOtp(numTel, otp);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send SMS: " + e.getMessage());
        }

        // Enregistre l'OTP généré
        otpService.saveOtp(numTel, otp);

        return ResponseEntity.ok("OTP sent successfully");
    }*/
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody String phoneNumber) {
        String otp = generateOtp(); // Générez ici votre code OTP si nécessaire
        String result = otpSmsService.sendOtpSms(phoneNumber, otp);
        return ResponseEntity.ok(result);
    }

    // Méthode fictive pour générer un OTP, vous pouvez l'implémenter selon vos besoins
    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }


    @PostMapping("/phone")
    public ResponseEntity<String> verifyPhoneNumber2(@RequestBody String phoneNumber) {

        // Appel à l'API Infobip pour envoyer un code PIN
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            String jsonBody = String.format(
                    "{\"applicationId\":\"%s\",\"messageId\":\"%s\",\"from\":\"ServiceSMS\",\"to\":\"%s\"}",
                    "yourRealAppId", "yourRealMessageId", phoneNumber);
            System.out.println("JSON Body: " + jsonBody);

            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonBody);
            Request request = new Request.Builder()
                    .url("https://y3qdp9.api.infobip.com/2fa/2/pin")
                    .method("POST", body)
                    .addHeader("Authorization", "App 6684f4553f820754bc5710993b5e3519-c26ee060-c768-4d3e-9164-307cdd938bbe")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println("Response code: " + response.code());
            System.out.println("Response body: " + responseBody);

            if (response.isSuccessful()) {
                return ResponseEntity.ok("Phone number verified");
            } else {
                return ResponseEntity.status(response.code()).body("Failed to verify phone number: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
    @PostMapping("/phone2")
    public ResponseEntity<String> sendPhoneNumber(@RequestBody String phoneNumber) {
        // Nettoyer le numéro de téléphone pour supprimer les espaces et caractères spéciaux
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Vérifier la longueur du numéro de téléphone
        if (phoneNumber.length() == 0 || phoneNumber.length() > 50) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid phone number format");
        }

        // Créer et configurer l'API client Infobip
        ApiClient apiClient = ApiClient.forApiKey(ApiKey.from(INFOBIP_API_KEY))
                .withBaseUrl(BaseUrl.from(INFOBIP_API_URL))
                .build();
        SmsApi smsApi = new SmsApi(apiClient);

        String otp = generateOtp();

        // Configurer le message SMS
        SmsTextualMessage smsMessage = new SmsTextualMessage()
                .from("ServiceSMS")
                .addDestinationsItem(new SmsDestination().to(phoneNumber))
                .text("Your OTP code is: " + otp);

        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
                .messages(List.of(smsMessage));

        try {
            // Envoyer le message SMS
            SmsResponse smsResponse = smsApi.sendSmsMessage(smsMessageRequest).execute();
            return ResponseEntity.ok("SMS sent successfully. Message ID: " + smsResponse.getMessages().get(0).getMessageId());
        } catch (ApiException apiException) {
            // Gérer les exceptions de l'API
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SMS: " + apiException.getMessage());
        }

    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.isAuthentificated(),
                    userDetails.enabled(),
                    userDetails.getCin(),
                    userDetails.getDateNaissance(),
                    roles));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vérifier votre identifiant et / ou mot de passe");
        }

    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/signupClient")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String activationCode = UUID.randomUUID().toString();
        // Create new user's account

        User user = new Client(
                signUpRequest.getActivationCode(),
                signUpRequest.isEnabled(),
                signUpRequest.isAuthentificated(),
                signUpRequest.getName(),
                signUpRequest.getPrenom(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getNumTel(),
                signUpRequest.getDateNaissance(),
                signUpRequest.getCin(),
                encoder.encode(signUpRequest.getPassword())
        );

        user.setEnabled(false);
        user.setAuthentificated(false);
        user.setActivationCode(activationCode);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "gestionnaire":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        // Envoyez un e-mail d'activation au nouvel utilisateur
        // String activationLink = "http://localhost:8060/activate/" + activationCode;
        //Envoyer un code d'activation
        //String activationLink =  activationCode;

        //emailService.sendActivationEmail(user.getEmail(), activationLink);

        return ResponseEntity.ok("Votre compte a été crée avec succés.");
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @GetMapping("/ContratsClient")
    List<Contrat> addContratsClient(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        String username = userDetails.getUsername(); // Récupération du username de l'utilisateur connecté

        User client = userRepository.findUserByUsername(username); // Recherche de l'utilisateur par username

        if (client != null && !client.isAuthentificated()) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }

        // Récupération du CIN de l'utilisateur
        String CIN = client != null ? client.getCin() : null;

        List<Contrat> contratsClient = Middlewareportefeuille.getContratsClient(CIN, numCNT);

        return contratsClient;
    }




}
