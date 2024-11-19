package com.app.ClientService.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String name;
    private String prenom;
    private String email;

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

    private String cin;

    private String dateNaissance;

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    private boolean isAuthentificated ;

    private boolean enabled ;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAuthentificated() {
        return isAuthentificated;
    }

    public void setAuthentificated(boolean authentificated) {
        isAuthentificated = authentificated;
    }

    private List<String> roles;


    public JwtResponse(String accessToken, Long id, String username,String name,String prenom, String email,boolean isAuthentificated,boolean enabled,String cin,String dateNaissance,List<String> roles ) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.isAuthentificated = isAuthentificated;
        this.enabled = enabled;
        this.cin= cin;
        this.dateNaissance= dateNaissance;
        this.roles = roles;

    }
    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
