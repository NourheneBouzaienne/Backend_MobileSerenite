package com.app.ClientService.models;

public class RenteResponse {

    private int date_Effet;
    private int date_naissance;
    private int durmois;
    private String fract;
    private String fract_rente;
    private double mntprime_investi;
    private double salaire_imposable;
    private char typE_rente;
    private double _Tx_tmg;
    private double frai_Service_Rente;
    private int duree_rente_parAN;
    private double mntprimetotal;
    private double mnt_gain_fiscal;
    private double mnt_gain_financier;
    private double mnt_epargne_constitu;
    private double mnt_rente;
    private double mntprime;
    // Getters and setters

    public double getMntprime() {
        return mntprime;
    }

    public void setMntprime(double mntprime) {
        this.mntprime = mntprime;
    }

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

    public double getMntprime_investi() {
        return mntprime_investi;
    }

    public void setMntprime_investi(double mntprime_investi) {
        this.mntprime_investi = mntprime_investi;
    }

    public double getSalaire_imposable() {
        return salaire_imposable;
    }

    public void setSalaire_imposable(double salaire_imposable) {
        this.salaire_imposable = salaire_imposable;
    }

    public char getTypE_rente() {
        return typE_rente;
    }

    public void setTypE_rente(char typE_rente) {
        this.typE_rente = typE_rente;
    }

    public double get_Tx_tmg() {
        return _Tx_tmg;
    }

    public void set_Tx_tmg(double _Tx_tmg) {
        this._Tx_tmg = _Tx_tmg;
    }

    public double getFrai_Service_Rente() {
        return frai_Service_Rente;
    }

    public void setFrai_Service_Rente(double frai_Service_Rente) {
        this.frai_Service_Rente = frai_Service_Rente;
    }

    public int getDuree_rente_parAN() {
        return duree_rente_parAN;
    }

    public void setDuree_rente_parAN(int duree_rente_parAN) {
        this.duree_rente_parAN = duree_rente_parAN;
    }

    public double getMntprimetotal() {
        return mntprimetotal;
    }

    public void setMntprimetotal(double mntprimetotal) {
        this.mntprimetotal = mntprimetotal;
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

    public double getMnt_rente() {
        return mnt_rente;
    }

    public void setMnt_rente(double mnt_rente) {
        this.mnt_rente = mnt_rente;
    }

    public RenteResponse(int date_Effet, int date_naissance, int durmois, String fract, String fract_rente, double mntprime_investi, double salaire_imposable, char typE_rente, double _Tx_tmg, double frai_Service_Rente, int duree_rente_parAN, double mntprimetotal, double mnt_gain_fiscal, double mnt_gain_financier, double mnt_epargne_constitu, double mnt_rente, double mntprime) {
        this.date_Effet = date_Effet;
        this.date_naissance = date_naissance;
        this.durmois = durmois;
        this.fract = fract;
        this.fract_rente = fract_rente;
        this.mntprime_investi = mntprime_investi;
        this.salaire_imposable = salaire_imposable;
        this.typE_rente = typE_rente;
        this._Tx_tmg = _Tx_tmg;
        this.frai_Service_Rente = frai_Service_Rente;
        this.duree_rente_parAN = duree_rente_parAN;
        this.mntprimetotal = mntprimetotal;
        this.mnt_gain_fiscal = mnt_gain_fiscal;
        this.mnt_gain_financier = mnt_gain_financier;
        this.mnt_epargne_constitu = mnt_epargne_constitu;
        this.mnt_rente = mnt_rente;
        this.mntprime = mntprime;
    }

    public RenteResponse() {
    }
}
