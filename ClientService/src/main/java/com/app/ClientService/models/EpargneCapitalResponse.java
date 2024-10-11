package com.app.ClientService.models;


public class EpargneCapitalResponse {

    private int date_Effet;
    private int date_naissance;
    private int durmois;
    private String fract;
    private int capital;
    private int salaire_imposable;
    private double mntprimetotal;
    private double mntprime_Fract;
    private double mnt_gain_fiscal;
    private double mnt_gain_financier;
    private double mnt_epargne_constitu;

    public int getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(int date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getDate_Effet() {
        return date_Effet;
    }

    public void setDate_Effet(int date_Effet) {
        this.date_Effet = date_Effet;
    }

    public int getDurmois() {
        return durmois;
    }

    public void setDurmois(int durmois) {
        this.durmois = durmois;
    }

    public String getFract() {
        return fract;
    }

    public void setFract(String fract) {
        this.fract = fract;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getSalaire_imposable() {
        return salaire_imposable;
    }

    public void setSalaire_imposable(int salaire_imposable) {
        this.salaire_imposable = salaire_imposable;
    }

    public double getMntprimetotal() {
        return mntprimetotal;
    }

    public void setMntprimetotal(double mntprimetotal) {
        this.mntprimetotal = mntprimetotal;
    }

    public double getMntprime_Fract() {
        return mntprime_Fract;
    }

    public void setMntprime_Fract(double mntprime_Fract) {
        this.mntprime_Fract = mntprime_Fract;
    }

    public double getMnt_gain_fiscal() {
        return mnt_gain_fiscal;
    }

    public void setMnt_gain_fiscal(double mnt_gain_fiscal) {
        this.mnt_gain_fiscal = mnt_gain_fiscal;
    }

    public double getMnt_gain_financier() {
        return mnt_gain_financier;
    }

    public void setMnt_gain_financier(double mnt_gain_financier) {
        this.mnt_gain_financier = mnt_gain_financier;
    }

    public double getMnt_epargne_constitu() {
        return mnt_epargne_constitu;
    }

    public void setMnt_epargne_constitu(double mnt_epargne_constitu) {
        this.mnt_epargne_constitu = mnt_epargne_constitu;
    }


    public EpargneCapitalResponse(int dateEffet, int date_naissance, int durmois, String fract, int capital, int salaireImposable, double mntPrimeTotal, double mntPrimeFract, double mntGainFiscal, double mntGainFinancier, double mntEpargneConstitue) {
        this.date_Effet = dateEffet;
        this.date_naissance = this.date_naissance;
        this.durmois = durmois;
        this.fract = fract;
        this.capital = capital;
        this.salaire_imposable = salaireImposable;
        this.mntprimetotal = mntPrimeTotal;
        this.mntprime_Fract = mntPrimeFract;
        this.mnt_gain_fiscal = mntGainFiscal;
        this.mnt_gain_financier = mntGainFinancier;
        this.mnt_epargne_constitu = mntEpargneConstitue;
    }

    public EpargneCapitalResponse() {
    }



}
