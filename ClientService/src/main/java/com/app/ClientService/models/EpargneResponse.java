package com.app.ClientService.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EpargneResponse {

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

    // Getters and Setters
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
}
