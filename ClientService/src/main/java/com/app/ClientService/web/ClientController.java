package com.app.ClientService.web;


import com.app.ClientService.beans.Contrat;
import com.app.ClientService.beans.Quittance;
import com.app.ClientService.dao.EpargneResponseRepository;
import com.app.ClientService.dao.RoleRepository;
import com.app.ClientService.dao.SimulateurRepository;
import com.app.ClientService.dao.UserRepository;
import com.app.ClientService.models.*;
import com.app.ClientService.proxies.MiddlewarePortefeuille;
import com.app.ClientService.security.jwt.JwtUtils;
import com.app.ClientService.security.services.UserDetailsImpl;
import com.app.ClientService.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Client")
public class ClientController {
    private final MiddlewarePortefeuille Middlewareportefeuille;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimulateurRepository simulateurRepository;


    @Autowired
    EpargneResponseRepository epargneResponseRepository;

    @Autowired
    UserService userService;
    @Autowired
    private OtpService otpService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;
    @Autowired
    SimulateurService simulateurService;


    @Autowired
    RenteService renteService;












    private static final Logger logger = LoggerFactory.getLogger(ClientController.class.getName());


    public ClientController(MiddlewarePortefeuille middlewareportefeuille) {
        Middlewareportefeuille = middlewareportefeuille;
    }


 @GetMapping("/ContratsClient")
    public List<Contrat> addContratsClient(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {

       String username = userDetails.getUsername(); // Récupération username  de l'utilisateur
        System.out.println("Username,"+username);
        String CIN = userDetails.getCin(); // Utilisez getCin pour récupérer le CIN
        User client = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));

        List<Contrat> contratsClient = Middlewareportefeuille.getContratsClient(CIN, numCNT);

        if (client != null && !client.isAuthentificated() && contratsClient != null) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }

        return contratsClient;
    }
    @GetMapping("/Quittances")
    public List<Quittance> listQuittances(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {

        String username = userDetails.getUsername(); // Récupération username  de l'utilisateur
        System.out.println("Username,"+username);
        String CIN = userDetails.getCin(); // Utilisez getCin pour récupérer le CIN
        User client = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));

        List<Quittance> quittances = Middlewareportefeuille.listQuittancesByContrat(numCNT);

        if (client != null && !client.isAuthentificated() && quittances != null) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }
        return quittances;
    }

    @GetMapping("/ContratsClientF")
    public List<Contrat> addContratsClientF(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {

        String username = userDetails.getUsername(); // Récupération username  de l'utilisateur
        System.out.println("Username,"+username);
        String CIN = userDetails.getCin(); // Utilisez getCin pour récupérer le CIN
        User client = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));

        List<Contrat> contratsClient = Middlewareportefeuille.getContratsClient(CIN, numCNT);

        if (client != null && !client.isAuthentificated() && contratsClient != null) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }

        return contratsClient;
    }
    @GetMapping("/AddContratsClientV2")
    public ResponseEntity<Object> addContratsClientv2(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        String username = userDetails.getUsername(); // Retrieve username
        System.out.println("Username: " + username);
        String CIN = userDetails.getCin(); // Use getCin to retrieve CIN

        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<Contrat> contratsClient = Middlewareportefeuille.getContratsClient(CIN, numCNT);

        if (contratsClient == null || contratsClient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No contracts found for the given contract number.");
        }

        if (!client.isAuthentificated() && !contratsClient.isEmpty()) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }


        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 500px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header { text-align: center; background-color: #1c4191; padding: 10px; color: #fff; border-radius: 5px; }" +
                ".header img { max-width: 80px; height: auto; margin: 0 auto; display: block; }" +
                ".header h1 { font-size: 24px; font-weight: bold; margin: 8px 0 0; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer { text-align: center; padding: 10px; }" +
                ".footer p { margin: 0; font-size: 18px; }" +
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 20px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 16px; }" +
                "}" +
                "</style>";

        String emailBody = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" + styles + "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "    <div class=\"header\">" +
                "        <img src=\"cid:logoImage\" alt=\"AMI Assurances Logo\">" + // Utilisation de CID pour l'image
                "    </div>" +
                "    <div class=\"content\">" +
                "        <p><strong>Bonjour " + client.getPrenom() + ",</strong></p>" +
                "        <p>Nous vous remercions de votre fidélité et de la confiance que vous portez à notre compagnie.</p>" +
                "        <p>Nous vous invitons à vous rendre à votre agence habituelle pour finaliser les accès confidentiels par la signature du document attestant le consentement réciproque, conformément à la réglementation en vigueur.</p>" +
                "        <p>Au plaisir de vous servir.</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>Au plaisir de vous servir.<br />L’équipe commerciale</p>" +
                "    </div>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo(client.getEmail());
            helper.addCc("nourhene.bouzaienne7@gmail.com");
            helper.setSubject("Contrat de consentement en attente");
            helper.setText(emailBody, true);

            // Charger l'image depuis les ressources et l'attacher
            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\nbouzaiene\\Downloads\\Backend\\ClientService\\src\\main\\resources\\static\\images\\logoBlanc.png"));
            helper.addInline("logoImage", file); // CID pour l'image

            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès à : " + client.getEmail());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email.");
        }

        return ResponseEntity.ok(contratsClient);
    }

    @GetMapping("/ContratsClientsEnabled")
    public  List<Contrat>  listContratsClient(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername(); // Retrieve username
        System.out.println("Username: " + username);
        String CIN = userDetails.getCin(); // Use getCin to retrieve CIN

        User client = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));

        List<Contrat> contratsClient =   Middlewareportefeuille.listContratsClientById(CIN) ;

        return contratsClient ;


    }

    @GetMapping("/ContratsClient2")
    public List<Contrat> addContratsClient(@RequestParam String numCNT) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();
            // Récupérez d'autres détails si nécessaire
            String CIN = userDetails.getCin(); // Vous devrez implémenter getCin dans UserDetailsImpl

            User client = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            List<Contrat> contratsClient = Middlewareportefeuille.getContratsClient(CIN, numCNT);

            if (client != null && !client.isAuthentificated() && contratsClient != null) {
                client.setAuthentificated(true);
                userRepository.save(client);
            }

            return contratsClient;
        } else {
            throw new IllegalStateException("User not authenticated or UserDetails not available.");
        }
    }

    @GetMapping("/ContratsClientFinal")
    public List<Contrat> addContratsClientF(@RequestParam String numCNT) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();
            // Récupérez d'autres détails si nécessaire
            String CIN = userDetails.getCin(); // Vous devrez implémenter getCin dans UserDetailsImpl

            User client = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            List<Contrat> contratsClient = Middlewareportefeuille.getContratsClientF(CIN, numCNT);

            if (client != null && !client.isAuthentificated() && contratsClient != null) {
                client.setAuthentificated(true);
                userRepository.save(client);
            }

            return contratsClient;
        } else {
            throw new IllegalStateException("User not authenticated or UserDetails not available.");
        }
    }

    @PostMapping("/SimulateurVersementPeriodique")
    public List<EpargneResponse> getSimulateur(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EpargneRequest epargneRequest
          ) {

        String username = userDetails.getUsername();

        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Associer l'utilisateur à la requête
        epargneRequest.setUser(client);

        // Persister l'entité EpargnePrimeUniqueRequest (hérite de Simulateur)
        simulateurRepository.save(epargneRequest);

        List<EpargneResponse> epargneSim = simulateurService.getSimulateurVersementPeriodique(epargneRequest);

        for (EpargneResponse response : epargneSim) {
            response.setSimulateur(epargneRequest);  // Associer la réponse à la simulation
            epargneResponseRepository.save(response);  // Enregistrer la réponse dans la base de données
        }

        // Envoyer l'email de devis
        sendDevisEmailAgenceVP(client, epargneRequest, epargneSim);

        return epargneSim;
    }

    // Méthode pour envoyer l'email de devis
    private void sendDevisEmailAgenceVP(User client, EpargneRequest epargneRequest, List<EpargneResponse> epargneSim) {
        String emailBody = generateEmailBodyVP(client, epargneRequest, epargneSim);

        // Envoi de l'email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com");
            helper.setSubject("Nouvelle Simulation AMI Sérénité");
            helper.setText(emailBody, true);
            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    // Méthode pour générer le corps de l'email
    private String generateEmailBodyVP(User client, EpargneRequest epargneRequest, List<EpargneResponse> epargneSim) {
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" +
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";

        String periodicite = "";
        if ("T".equals(epargneRequest.getFract())) {
            periodicite = "Trimestriel";
        } else if ("S".equals(epargneRequest.getFract())) {
            periodicite = "semestriel";
        } else if ("A".equals(epargneRequest.getFract())) {
            periodicite = "Annuel";
        } else if ("M".equals(epargneRequest.getFract())) {
            periodicite = "Mensuel";
        }

        StringBuilder emailBody = new StringBuilder();
        emailBody.append(styles)
                .append("<div class='container'>")
                .append("<div class='header'>")
                .append("<h1>Devis de Simulation </h1>")
                .append("</div>")
                .append("<div class='content'>")
                .append("<p><strong>Bonjour,</strong></p>")
                .append("<p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en téléchargeant un devis.</p>")
                .append("<p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>")

                // Informations du client
                .append("<h2>Voici les informations du client :</h2>")
                .append("<ul>")
                .append("<li><strong>Nom :</strong> ").append(client.getName()).append("</li>")
                .append("<li><strong>Prénom :</strong> ").append(client.getPrenom()).append("</li>")
                .append("<li><strong>Email :</strong> ").append(client.getEmail()).append("</li>")
                .append("<li><strong>Téléphone :</strong> ").append(client.getNumTel()).append("</li>")
                .append("<li><strong>Date de naissance :</strong> ")
                .append(formatDate(epargneRequest.getDate_naissance())).append("</li>")
                .append("</ul>")

                // Informations du contrat
                .append("<h2>Informations du contrat :</h2>")
                .append("<ul>")
                .append("<li><strong>Type de contrat :</strong> ").append("Prime Unique").append("</li>")
                .append("<li><strong>Durée du contrat :</strong> ").append(epargneRequest.getDurmois() / 12).append(" ans</li>")
                .append("<li><strong>Périodicité :</strong> ").append(periodicite)
                .append("<li><strong>Montant versement initial</strong> ").append(epargneRequest.getMntversemment_intial()).append(" DT</li>")
                .append("<li><strong>Versements réguliers :</strong> ").append(epargneRequest.getMntprime_investi()).append(" DT</li>")
                .append("<li><strong>Taux d'indexation :</strong> ").append(epargneRequest.get_Tx_indexation()).append("%")
                .append("<li><strong>Salaire imposable :</strong> ").append(epargneRequest.getSalaire_imposable()).append(" DT</li>")
                .append("</ul>")

                // Détails de la simulation
                .append("<h2>Détails de la Simulation :</h2>")
                .append("<table border='1'>")
                .append("<tr><th>Année</th><th>Prime Commerciale en DT</th><th>Cumul des Primes en DT</th><th>Epargne Constituée en DT</th><th>Gain Financier en DT</th><th>Gain Fiscal en DT</th></tr>");

        for (EpargneResponse response : epargneSim) {
            emailBody.append("<tr>")
                    .append("<td>").append(response.getAnnee()).append("</td>")
                    .append("<td>").append(response.getPrimeCommercial()).append("</td>")
                    .append("<td>").append(response.getTotalPrimeCumulee()).append("</td>")
                    .append("<td>").append(response.getEpargneConstituees()).append("</td>")
                    .append("<td>").append(response.getGainFinancier()).append("</td>")
                    .append("<td>").append(response.getGainFiscal()).append("</td>")
                    .append("</tr>");
        }

        emailBody.append("</table>")
                .append("</div>")
                .append("<div class='footer'>")
                .append("<p>Cordialement,</p>")
                .append("<p><strong>AMI Assurances</strong></p>")
                .append("</div>")
                .append("</div>");

        return emailBody.toString();
    }



    @PostMapping("/SimulateurPrimeUnique")
    public List<EpargneResponse> getSimulateurPrimeUnique(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EpargnePrimeUniqueRequest epargneRequest
    ) {
        // Récupérer l'utilisateur connecté
        String username = userDetails.getUsername();
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Associer l'utilisateur à la requête
        epargneRequest.setUser(client);

        // Persister l'entité EpargnePrimeUniqueRequest (hérite de Simulateur)
        simulateurRepository.save(epargneRequest);

        // Appeler le service pour effectuer la simulation
        List<EpargneResponse> epargneSim = simulateurService.getSimulateurPrimeUnique(epargneRequest);

        // Persister chaque réponse de la simulation (EpargneResponse) avec le simulateur
        for (EpargneResponse response : epargneSim) {
            response.setSimulateur(epargneRequest);  // Associer la réponse à la simulation
            epargneResponseRepository.save(response);  // Enregistrer la réponse dans la base de données
        }

        // Envoyer l'email de devis
        sendDevisEmailAgence(client, epargneRequest, epargneSim);

        // Retourner la liste des réponses simulées
        return epargneSim;
    }

    // Méthode pour envoyer l'email de devis
    private void sendDevisEmailAgence(User client, EpargnePrimeUniqueRequest epargneRequest, List<EpargneResponse> epargneSim) {
        String emailBody = generateEmailBody(client, epargneRequest, epargneSim);

        // Envoi de l'email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com");
            helper.setSubject("Nouvelle Simulation AMI Sérénité");
            helper.setText(emailBody, true);
            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    // Méthode pour générer le corps de l'email
    private String generateEmailBody(User client, EpargnePrimeUniqueRequest epargneRequest, List<EpargneResponse> epargneSim) {
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" +
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";



        StringBuilder emailBody = new StringBuilder();
        emailBody.append(styles)
                .append("<div class='container'>")
                .append("<div class='header'>")
                .append("<h1>Devis de Simulation </h1>")
                .append("</div>")
                .append("<div class='content'>")
                .append("<p><strong>Bonjour,</strong></p>")
                .append("<p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en téléchargeant un devis.</p>")
                .append("<p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>")

                // Informations du client
                .append("<h2>Voici les informations du client :</h2>")
                .append("<ul>")
                .append("<li><strong>Nom :</strong> ").append(client.getName()).append("</li>")
                .append("<li><strong>Prénom :</strong> ").append(client.getPrenom()).append("</li>")
                .append("<li><strong>Email :</strong> ").append(client.getEmail()).append("</li>")
                .append("<li><strong>Téléphone :</strong> ").append(client.getNumTel()).append("</li>")
                .append("<li><strong>Date de naissance :</strong> ")
                .append(formatDate(epargneRequest.getDate_naissance())).append("</li>")
                .append("</ul>")

                // Informations du contrat
                .append("<h2>Informations du contrat :</h2>")
                .append("<ul>")
                .append("<li><strong>Type de contrat :</strong> ").append("Prime Unique").append("</li>")
                .append("<li><strong>Durée du contrat :</strong> ").append(epargneRequest.getDurmois() / 12).append(" ans</li>")
                .append("<li><strong>Montant de la prime investie :</strong> ").append(epargneRequest.getMntprime_investi()).append(" DT</li>")
                .append("<li><strong>Salaire imposable :</strong> ").append(epargneRequest.getSalaire_imposable()).append(" DT</li>")
                .append("</ul>")

                // Détails de la simulation
                .append("<h2>Détails de la Simulation :</h2>")
                .append("<table border='1'>")
                .append("<tr><th>Année</th><th>Prime Commerciale en DT</th><th>Cumul des Primes en DT</th><th>Epargne Constituée en DT</th><th>Gain Financier en DT</th><th>Gain Fiscal en DT</th></tr>");

        for (EpargneResponse response : epargneSim) {
            emailBody.append("<tr>")
                    .append("<td>").append(response.getAnnee()).append("</td>")
                    .append("<td>").append(response.getPrimeCommercial()).append("</td>")
                    .append("<td>").append(response.getTotalPrimeCumulee()).append("</td>")
                    .append("<td>").append(response.getEpargneConstituees()).append("</td>")
                    .append("<td>").append(response.getGainFinancier()).append("</td>")
                    .append("<td>").append(response.getGainFiscal()).append("</td>")
                    .append("</tr>");
        }

        emailBody.append("</table>")
                .append("</div>")
                .append("<div class='footer'>")
                .append("<p>Cordialement,</p>")
                .append("<p><strong>AMI Assurances</strong></p>")
                .append("</div>")
                .append("</div>");

        return emailBody.toString();
    }

    private String formatDate(int dateInt) {
        // Convertir l'entier en chaîne
        String dateString = String.valueOf(dateInt);
        // Parser la chaîne en LocalDate
        LocalDate dateNaissance = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        // Formater la date dans le format souhaité
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy"); // Ex : "1er novembre 2024"
        return dateNaissance.format(formatter);
    }





   /* @PostMapping("/SimulateurPrimeUnique")
    public List<EpargneResponse> getSimulateurPrimeUnique(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EpargnePrimeUniqueRequest epargneRequest
    ) {
        // Récupérer l'utilisateur connecté
        String username = userDetails.getUsername();
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Associer l'utilisateur à la requête
        epargneRequest.setUser(client);

        // Persister l'entité EpargnePrimeUniqueRequest (hérite de Simulateur)
        simulateurRepository.save(epargneRequest);

        // Appeler le service pour effectuer la simulation
        List<EpargneResponse> epargneSim = simulateurService.getSimulateurPrimeUnique(epargneRequest);

        // Persister chaque réponse de la simulation (EpargneResponse) avec le simulateur
        for (EpargneResponse response : epargneSim) {
            response.setSimulateur(epargneRequest);  // Associer la réponse à la simulation
            epargneResponseRepository.save(response);  // Enregistrer la réponse dans la base de données
        }

        // Retourner la liste des réponses simulées
        return epargneSim;
    }
*/

    @PostMapping("/SimulateurParObjectif")
    public List<EpargneResponse> getSimulateurParObjectif(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EpargneRequestObjectif epargneRequest
    ) {

        String username = userDetails.getUsername();

        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        epargneRequest.setUser(client);
        simulateurRepository.save(epargneRequest);

        List<EpargneResponse> epargneSim = simulateurService.getSimulateurParObjectif(epargneRequest);

        for (EpargneResponse response : epargneSim) {
            response.setSimulateur(epargneRequest);  // Associer la réponse à la simulation
            epargneResponseRepository.save(response);  // Enregistrer la réponse dans la base de données
        }

        // Envoyer l'email de devis
        sendDevisEmailAgenceObjectif(client, epargneRequest, epargneSim);

        return epargneSim;
    }

    // Méthode pour envoyer l'email de devis
    private void sendDevisEmailAgenceObjectif(User client, EpargneRequestObjectif epargneRequest, List<EpargneResponse> epargneSim) {
        String emailBody = generateEmailBodyObjectif(client, epargneRequest, epargneSim);

        // Envoi de l'email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com");
            helper.setSubject("Nouvelle Simulation AMI Sérénité");
            helper.setText(emailBody, true);
            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    // Méthode pour générer le corps de l'email
    private String generateEmailBodyObjectif(User client, EpargneRequestObjectif epargneRequest, List<EpargneResponse> epargneSim) {
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" +
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";

        String periodicite = "";
        if ("T".equals(epargneRequest.getFract())) {
            periodicite = "Trimestriel";
        } else if ("S".equals(epargneRequest.getFract())) {
            periodicite = "Semestriel";
        } else if ("A".equals(epargneRequest.getFract())) {
            periodicite = "Annuel";
        } else if ("M".equals(epargneRequest.getFract())) {
            periodicite = "Mensuel";
        }


        StringBuilder emailBody = new StringBuilder();
        emailBody.append(styles)
                .append("<div class='container'>")
                .append("<div class='header'>")
                .append("<h1>Devis de Simulation </h1>")
                .append("</div>")
                .append("<div class='content'>")
                .append("<p><strong>Bonjour,</strong></p>")
                .append("<p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en téléchargeant un devis.</p>")
                .append("<p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>")

                // Informations du client
                .append("<h2>Voici les informations du client :</h2>")
                .append("<ul>")
                .append("<li><strong>Nom :</strong> ").append(client.getName()).append("</li>")
                .append("<li><strong>Prénom :</strong> ").append(client.getPrenom()).append("</li>")
                .append("<li><strong>Email :</strong> ").append(client.getEmail()).append("</li>")
                .append("<li><strong>Téléphone :</strong> ").append(client.getNumTel()).append("</li>")
                .append("<li><strong>Date de naissance :</strong> ")
                .append(formatDate(epargneRequest.getDate_naissance())).append("</li>")
                .append("</ul>")

                // Informations du contrat
                .append("<h2>Informations du contrat :</h2>")
                .append("<ul>")
                .append("<li><strong>Type de contrat :</strong> ").append("Prime Unique").append("</li>")
                .append("<li><strong>Durée du contrat :</strong> ").append(epargneRequest.getDurmois() / 12).append(" ans</li>")
                .append("<li><strong>Périodicité :</strong> ").append(periodicite)
                .append("<li><strong>Montant versement initial</strong> ").append(epargneRequest.getMnt_epargne_constitu()).append(" DT</li>")
                .append("<li><strong>Salaire imposable :</strong> ").append(epargneRequest.getSalaire_imposable()).append(" DT</li>")
                .append("</ul>")

                // Détails de la simulation
                .append("<h2>Détails de la Simulation :</h2>")
                .append("<table border='1'>")
                .append("<tr><th>Année</th><th>Prime Commerciale en DT</th><th>Cumul des Primes en DT</th><th>Epargne Constituée en DT</th><th>Gain Financier en DT</th><th>Gain Fiscal en DT</th></tr>");

        for (EpargneResponse response : epargneSim) {
            emailBody.append("<tr>")
                    .append("<td>").append(response.getAnnee()).append("</td>")
                    .append("<td>").append(response.getPrimeCommercial()).append("</td>")
                    .append("<td>").append(response.getTotalPrimeCumulee()).append("</td>")
                    .append("<td>").append(response.getEpargneConstituees()).append("</td>")
                    .append("<td>").append(response.getGainFinancier()).append("</td>")
                    .append("<td>").append(response.getGainFiscal()).append("</td>")
                    .append("</tr>");
        }

        emailBody.append("</table>")
                .append("</div>")
                .append("<div class='footer'>")
                .append("<p>Cordialement,</p>")
                .append("<p><strong>AMI Assurances</strong></p>")
                .append("</div>")
                .append("</div>");

        return emailBody.toString();
    }


    @PostMapping("/SimulateurSelonCapital")
    public EpargneCapitalResponse getSimulateurSelonCapital(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EpargneRequestCapital epargneRequest
    ) {
        String username = userDetails.getUsername();

        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Récupère un seul objet au lieu d'une liste
        EpargneCapitalResponse epargneSim = simulateurService.getSimulateurSelonCapital(epargneRequest);

        return epargneSim;
    }


    @PostMapping("/RentePrimeUnique")
    public RenteResponse getRentePrimeUnique(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RenteRequestUnique renteRequest
    ) {
        String username = userDetails.getUsername();
        logger.info("Demande de rente reçue pour l'utilisateur: " + username);
        logger.info("Détails de la demande : " + renteRequest.toString());

        try {
            // Récupérer l'utilisateur à partir de la base de données
            User client = userRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        logger.error("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
                        return new UsernameNotFoundException("User not found with username: " + username);
                    });

            logger.info("Utilisateur trouvé : " + client.toString());

            RenteResponse renteResponse = renteService.getRentePrimeUnique(renteRequest);
            logger.info("Données envoyées à l'API : {}", renteRequest.toString());

            return renteResponse;
        } catch (HttpServerErrorException e) {
            logger.error("Statut de l'erreur : {}", e.getStatusCode());
            logger.error("Corps de la réponse : {}", e.getResponseBodyAsString());
            logger.error("En-têtes de la réponse : {}", e.getResponseHeaders());

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur du serveur distant", e);
        } catch (RestClientException e) {
            logger.error("Erreur de connexion avec le serveur distant", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service indisponible", e);
        }
    }




    @PostMapping("/RenteVersementsPeriodiques")
    public RenteResponse getRenteVersementsPeriodiques(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RenteRequest renteRequest
    ) {
        String username = userDetails.getUsername();

        // Retrieve the user from the database
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Call the service to get a single RenteResponse
        RenteResponse renteResponse = renteService.getRenteVersements(renteRequest);

        // Return the single RenteResponse object
        return renteResponse;
    }


    @PostMapping("/RenteObjectif")
    public RenteResponse getRenteParObjectif(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RenteRequestObjectif renteRequest
    ) {
        String username = userDetails.getUsername();

        // Retrieve the user from the database
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Call the service to get a single RenteResponse
        RenteResponse renteResponse = renteService.getRenteParObjectif(renteRequest);

        // Return the single RenteResponse object
        return renteResponse;
    }


    @GetMapping("/sendEmailAgence")
    public ResponseEntity<Object> emailAgence(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String contractType) {
        String username = userDetails.getUsername();
        System.out.println("Username: " + username);
        String CIN = userDetails.getCin();

        // Récupération du client depuis le repository
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Styles pour l'email
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" + // Style pour le logo
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";

        // Corps de l'email avec du HTML stylisé
        String emailBody = styles +
                "<div class='container'>" +
                "  <div class='header'>" +
                "    <h1>Nouvelle Simulation AMI Sérénité </h1>" +
                "  </div>" +
                "  <div class='content'>" +
                "    <p><strong>Bonjour,</strong></p>" +
                "    <p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en téléchargeant un devis.</p>" +
                "    <p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>" +
                "    <p><strong>Voici les informations du client :</strong></p>" +
                "    <ul>" +
                "      <li><strong>Nom :</strong> " + client.getName() + "</li>" +
                "      <li><strong>Prénom :</strong> " + client.getPrenom() + "</li>" +
                "      <li><strong>Email :</strong> " + client.getEmail() + "</li>" +
                "      <li><strong>Téléphone :</strong> " + client.getNumTel() + "</li>" +
                "      <li><strong>Type de contrat simulé :</strong> " + contractType + "</li>" +
                "    </ul>" +
                "  </div>" +
                "  <div class='footer'>" +
                "    <p>Cordialement,</p>" +
                "    <p><strong>AMI Assurances</strong></p>" +
                "  </div>" +
                "</div>";

        // Envoi de l'email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com"); // Destinataire, peut être modifié
            helper.setSubject("Devis - Simulation d'épargne");
            helper.setText(emailBody, true); // true pour dire que le contenu est du HTML
            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/sendEmailAgenceRDV")
    public ResponseEntity<Object> emailAgenceRDV(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam String contractType,
            @RequestParam String additionalInfo) {  // New parameter
        String username = userDetails.getUsername();
        System.out.println("Username: " + username);
        String CIN = userDetails.getCin();

        // Retrieve client from the repository
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Email styles
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" +
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";

        // Email body with HTML styling
        String emailBody = styles +
                "<div class='container'>" +
                "  <div class='header'>" +
                "    <h1>Prise RDV - AMI Sérénité  </h1>" +
                "  </div>" +
                "  <div class='content'>" +
                "    <p><strong>Bonjour,</strong></p>" +
                "    <p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en prenant un RDV.</p>" +
                "    <p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>" +
                "    <p><strong>Voici les informations du client :</strong></p>" +
                "    <ul>" +
                "      <li><strong>Nom :</strong> " + client.getName() + "</li>" +
                "      <li><strong>Prénom :</strong> " + client.getPrenom() + "</li>" +
                "      <li><strong>Email :</strong> " + client.getEmail() + "</li>" +
                "      <li><strong>Téléphone :</strong> " + client.getNumTel() + "</li>" +
                "      <li><strong>Type de contrat simulé :</strong> " + contractType + "</li>" +
                "      <li><strong>Date de RDV :</strong> " + additionalInfo + "</li>" +  // Updated label
                "    </ul>" +
                "  </div>" +
                "  <div class='footer'>" +
                "    <p>Cordialement,</p>" +
                "    <p><strong>AMI Assurances</strong></p>" +
                "  </div>" +
                "</div>";

        // Sending the email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com");  // Recipient, can be modified
            helper.setSubject("Devis - Simulation d'épargne");
            helper.setText(emailBody, true);  // true to indicate HTML content
            javaMailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/sendDevisEmailAgence")
    public ResponseEntity<Object> emailDevisAgence(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String contractType, @RequestBody EpargnePrimeUniqueRequest simulationRequest,@RequestBody EpargneResponse epargneResponse) {
        String username = userDetails.getUsername();
        System.out.println("Username: " + username);
        String CIN = userDetails.getCin();

        // Récupération du client depuis le repository
        User client = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Styles pour l'email
        String styles = "<style>" +
                "body { font-family: 'Helvetica Neue', Arial, sans-serif; color: #333; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".header, .footer { text-align: center; background-color: #1c4191; padding: 15px; color: #fff; border-radius: 5px; }" +
                ".content { padding: 20px; background-color: #fff; border-radius: 0 0 5px 5px; }" +
                ".content p { margin: 15px 0; font-size: 16px; line-height: 1.6; }" +
                ".content strong { color: #ed3026; }" +
                ".footer p { margin: 0; font-size: 20px; }" +
                ".footer img { max-width: 150px; height: auto; margin-top: 15px; }" + // Style pour le logo
                "@media (max-width: 600px) {" +
                "    .container { width: 100%; padding: 10px; }" +
                "    .header h1 { font-size: 22px; }" +
                "    .content p { font-size: 14px; }" +
                "    .footer { font-size: 18px; }" +
                "}" +
                "</style>";

        // Corps de l'email avec du HTML stylisé
        String emailBody = styles +
                "<div class='container'>" +
                "  <div class='header'>" +
                "    <h1>Nouvelle Simulation AMI Sérénité </h1>" +
                "  </div>" +
                "  <div class='content'>" +
                "    <p><strong>Bonjour,</strong></p>" +
                "    <p>Un client ou un prospect vient de procéder à une simulation d'épargne via notre application mobile et a manifesté son intérêt en téléchargeant un devis.</p>" +
                "    <p>Merci de prendre contact avec ce client potentiel et l'accompagner dans les étapes suivantes pour la concrétisation du contrat.</p>" +
                "    <p><strong>Voici les informations du client :</strong></p>" +
                "    <ul>" +
                "      <li><strong>Nom :</strong> " + client.getName() + "</li>" +
                "      <li><strong>Prénom :</strong> " + client.getPrenom() + "</li>" +
                "      <li><strong>Email :</strong> " + client.getEmail() + "</li>" +
                "      <li><strong>Téléphone :</strong> " + client.getNumTel() + "</li>" +
                "      <li><strong>Type de contrat simulé :</strong> " + contractType + "</li>" +
                "    </ul>" +
                "  </div>" +
                "  <div class='footer'>" +
                "    <p>Cordialement,</p>" +
                "    <p><strong>AMI Assurances</strong></p>" +
                "  </div>" +
                "</div>";

        // Envoi de l'email
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("no-reply@assurancesami.com"));
            helper.setTo("nourah.bouzaienne7@gmail.com"); // Destinataire, peut être modifié
            helper.setSubject("Devis - Simulation d'épargne");
            helper.setText(emailBody, true); // true pour dire que le contenu est du HTML
            javaMailSender.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }

        return ResponseEntity.ok().build();
    }




}


