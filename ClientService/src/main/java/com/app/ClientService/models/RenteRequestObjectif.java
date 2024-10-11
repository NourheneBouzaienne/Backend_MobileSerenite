package com.app.ClientService.models;

public class RenteRequestObjectif {

    private int date_Effet;
    private int date_naissance;
    private int durmois;
    private double  capital;
    private double salaire_imposable;
    private double _Tx_tmg;
    private String fract;
    private String fract_rente;
    private char typE_rente;
    private int duree_rente_parAN;
    private double frai_Service_Rente;

    public int getDate_Effet() {
        return date_Effet;
    }

    public void setDate_Effet(int date_Effet) {
        this.date_Effet = date_Effet;
    }

    public int getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(int date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getDurmois() {
        return durmois;
    }

    public void setDurmois(int durmois) {
        this.durmois = durmois;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getSalaire_imposable() {
        return salaire_imposable;
    }

    public void setSalaire_imposable(double salaire_imposable) {
        this.salaire_imposable = salaire_imposable;
    }

    public double get_Tx_tmg() {
        return _Tx_tmg;
    }

    public void set_Tx_tmg(double _Tx_tmg) {
        this._Tx_tmg = _Tx_tmg;
    }

    public String getFract() {
        return fract;
    }

    public void setFract(String fract) {
        this.fract = fract;
    }

    public String getFract_rente() {
        return fract_rente;
    }

    public void setFract_rente(String fract_rente) {
        this.fract_rente = fract_rente;
    }

    public char getTypE_rente() {
        return typE_rente;
    }

    public void setTypE_rente(char typE_rente) {
        this.typE_rente = typE_rente;
    }

    public int getDuree_rente_parAN() {
        return duree_rente_parAN;
    }

    public void setDuree_rente_parAN(int duree_rente_parAN) {
        this.duree_rente_parAN = duree_rente_parAN;
    }

    public double getFrai_Service_Rente() {
        return frai_Service_Rente;
    }

    public void setFrai_Service_Rente(double frai_Service_Rente) {
        this.frai_Service_Rente = frai_Service_Rente;
    }


    public RenteRequestObjectif(int date_Effet, int date_naissance, int durmois, double capital, double salaire_imposable, double _Tx_tmg, String fract, String fract_rente, char typE_rente, int duree_rente_parAN, double frai_Service_Rente) {
        this.date_Effet = date_Effet;
        this.date_naissance = date_naissance;
        this.durmois = durmois;
        this.capital = capital;
        this.salaire_imposable = salaire_imposable;
        this._Tx_tmg = _Tx_tmg;
        this.fract = fract;
        this.fract_rente = fract_rente;
        this.typE_rente = typE_rente;
        this.duree_rente_parAN = duree_rente_parAN;
        this.frai_Service_Rente = frai_Service_Rente;
    }


    public RenteRequestObjectif() {
    }


}
