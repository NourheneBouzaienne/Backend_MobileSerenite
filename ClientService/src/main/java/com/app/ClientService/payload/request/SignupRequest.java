package com.app.ClientService.payload.request;

import java.util.Date;
import java.util.Set;

public class SignupRequest {
    private String username;


    private String email;


    private String password;


    private String name;

    private String prenom;


    private boolean enabled;

    private String activationCode;


    private boolean isAuthentificated;
    private String numTel;

    private String dateNaissance;

    private Set<String> role;
    private String cin;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isAuthentificated() {
        return isAuthentificated;
    }

    public void setAuthentificated(boolean authentificated) {
        isAuthentificated = authentificated;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public SignupRequest(String username, String email, String password, String name, String prenom, boolean enabled, String activationCode, boolean isAuthentificated, String numTel, String dateNaissance,String cin, Set<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.enabled = enabled;
        this.activationCode = activationCode;
        this.isAuthentificated = isAuthentificated;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.role = role;
    }
}
