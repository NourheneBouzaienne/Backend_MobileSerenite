package com.app.ClientService.models;




import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{

    public Client() {
    }

    public Client(String activationCode, boolean enabled, boolean isAuthentificated,String name, String prenom, String username, String email, String numTel, String dateNaissance,String cin,String password) {
        super(activationCode, enabled, name, prenom, username, email, numTel, isAuthentificated, password, dateNaissance,cin);
    }


}
