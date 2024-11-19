package com.app.ClientService.models;


import javax.persistence.Entity;

@Entity
public class EpargneRequestObjectif  extends Simulateur{

    private int date_Effet;
    private int date_naissance;
    private int durmois;
    private double  mnt_epargne_constitu;
    private double salaire_imposable;
    private double _Tx_tmg;
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

    public double getMnt_epargne_constitu() {
        return mnt_epargne_constitu;
    }

    public void setMnt_epargne_constitu(double mnt_epargne_constitu) {
        this.mnt_epargne_constitu = mnt_epargne_constitu;
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

    public EpargneRequestObjectif(int date_Effet, int date_naissance, int durmois, double mnt_epargne_constitu, double salaire_imposable, double _Tx_tmg, String fract) {
        this.date_Effet = date_Effet;
        this.date_naissance = date_naissance;
        this.durmois = durmois;
        this.mnt_epargne_constitu = mnt_epargne_constitu;
        this.salaire_imposable = salaire_imposable;
        this._Tx_tmg = _Tx_tmg;
        this.fract = fract;
    }

    public EpargneRequestObjectif() {
    }





}
