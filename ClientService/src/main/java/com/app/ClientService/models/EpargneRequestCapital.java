package com.app.ClientService.models;

public class EpargneRequestCapital {

    private int date_Effet;
    private int date_naissance;
    private int durmois;
    private double  capital;
    private double salaire_imposable;
    private String fract;

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

    public String getFract() {
        return fract;
    }

    public void setFract(String fract) {
        this.fract = fract;
    }


    public EpargneRequestCapital(int date_Effet, int date_naissance, int durmois, double capital, double salaire_imposable, String fract) {
        this.date_Effet = date_Effet;
        this.date_naissance = date_naissance;
        this.durmois = durmois;
        this.capital = capital;
        this.salaire_imposable = salaire_imposable;
        this.fract = fract;
    }





}
