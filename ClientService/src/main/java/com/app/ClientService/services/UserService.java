package com.app.ClientService.services;

import com.app.ClientService.dao.UserRepository;
import com.app.ClientService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository; // Votre repository pour accéder aux utilisateurs
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Méthode pour changer le mot de passe
    public boolean changePassword(String numTel, String newPassword) {
        // Trouver l'utilisateur par numéro de téléphone
        User user = userRepository.findByNumTel(numTel);
        if (user == null) {
            return false; // L'utilisateur n'existe pas
        }

        // Encoder le nouveau mot de passe
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword); // Mettre à jour le mot de passe

        // Enregistrer les changements dans la base de données
        userRepository.save(user);
        return true; // Changement de mot de passe réussi
    }

}
