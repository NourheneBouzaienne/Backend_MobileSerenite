package com.app.ClientService.dao;

import com.app.ClientService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByUsername(String username);

        User findByEmail(String email);
        User findUserByUsername(String username);

        User findByActivationCode(String activationCode);

        User findByNumTel(String numTel);




        Boolean existsByUsername(String username);


        Boolean existsByEmail(String email);

}
