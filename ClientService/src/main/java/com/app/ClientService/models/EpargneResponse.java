package com.app.ClientService.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class EpargneResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epargne_response_seq")
    @SequenceGenerator(name = "epargne_response_seq", sequenceName = "epargne_response_sequence", allocationSize = 1)
    private Long id;

    @JsonProperty("Annee")
    private int annee;

    @JsonProperty("Prime_commercial")
    private double primeCommercial;

    @JsonProperty("Total_Prime_Cumulée")
    private double totalPrimeCumulee;

    @JsonProperty("Epargne_Constituées")
    private double epargneConstituees;

    @JsonProperty("Gain_Financier")
    private double gainFinancier;

    @JsonProperty("Gain_Fiscal")
    private double gainFiscal;

    // Relation avec Simulateur
    @ManyToOne
    @JoinColumn(name = "simulateur_id")
    private Simulateur simulateur;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getPrimeCommercial() {
        return primeCommercial;
    }

    public void setPrimeCommercial(double primeCommercial) {
        this.primeCommercial = primeCommercial;
    }

    public double getTotalPrimeCumulee() {
        return totalPrimeCumulee;
    }

    public void setTotalPrimeCumulee(double totalPrimeCumulee) {
        this.totalPrimeCumulee = totalPrimeCumulee;
    }

    public double getEpargneConstituees() {
        return epargneConstituees;
    }

    public void setEpargneConstituees(double epargneConstituees) {
        this.epargneConstituees = epargneConstituees;
    }

    public double getGainFinancier() {
        return gainFinancier;
    }

    public void setGainFinancier(double gainFinancier) {
        this.gainFinancier = gainFinancier;
    }

    public double getGainFiscal() {
        return gainFiscal;
    }

    public void setGainFiscal(double gainFiscal) {
        this.gainFiscal = gainFiscal;
    }

    public Simulateur getSimulateur() {
        return simulateur;
    }

    public void setSimulateur(Simulateur simulateur) {
        this.simulateur = simulateur;
    }
}
