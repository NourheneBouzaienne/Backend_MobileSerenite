package com.app.ClientService.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contrat<S, O> {
    private String NUMCNT;
    private Long CODPROD;
    private String FORMCNT;
    private String NATCLT;
    private Long IDCLT;
    private Long IDDELEGA;


    private String LIBPRDT;

    private String FRACT;
    private String NOM_INT;

    private String LIBGRNT;

    private String NOMCOMMERCIAL;


    private Float SOMME_PRIMGRNT;
    private String DEBEFFET;
    private String FINEFFET;

    private Integer DATE_LANC;
    private String  DATECALPM;
    private String NOMREDCL;
    private Long MNTPRNET;
    private  String DUREE;

    private Integer TAUX_RENDEMENT;
    private Float TAUX_TMG;
    private Float EPARGNE;
    private Float VALEUR_PB;
    private Float VALEUR_RACHAT;
    private String EMAIL;


    @JsonProperty("DATE_LANC")

    public Integer getDATE_LANC() {
        return DATE_LANC;
    }
    @JsonProperty("DATE_LANC")
    public void setDATE_LANC(Integer DATE_LANC) {
        this.DATE_LANC = DATE_LANC;
    }
    @JsonProperty("DATECALPM")
    public String getDATECALPM() {
        return DATECALPM;
    }
    @JsonProperty("DATECALPM")
    public void setDATECALPM(String DATECALPM) {
        this.DATECALPM = DATECALPM;
    }
    @JsonProperty("NOMREDCL")
    public String getNOMREDCL() {
        return NOMREDCL;
    }
    @JsonProperty("NOMREDCL")
    public void setNOMREDCL(String NOMREDCL) {
        this.NOMREDCL = NOMREDCL;
    }
    @JsonProperty("MNTPRNET")
    public Long getMNTPRNET() {
        return MNTPRNET;
    }
    @JsonProperty("MNTPRNET")
    public void setMNTPRNET(Long MNTPRNET) {
        this.MNTPRNET = MNTPRNET;
    }
    @JsonProperty("DUREE")
    public String getDUREE() {
        return DUREE;
    }
    @JsonProperty("DUREE")
    public void setDUREE(String DUREE) {
        this.DUREE = DUREE;
    }
    @JsonProperty("TAUX_RENDEMENT")
    public Integer getTAUX_RENDEMENT() {
        return TAUX_RENDEMENT;
    }
    @JsonProperty("TAUX_RENDEMENT")
    public void setTAUX_RENDEMENT(Integer TAUX_RENDEMENT) {
        this.TAUX_RENDEMENT = TAUX_RENDEMENT;
    }
    @JsonProperty("TAUX_TMG")
    public Float getTAUX_TMG() {
        return TAUX_TMG;
    }
    @JsonProperty("TAUX_TMG")
    public void setTAUX_TMG(Float TAUX_TMG) {
        this.TAUX_TMG = TAUX_TMG;
    }
    @JsonProperty("EPARGNE")
    public Float getEPARGNE() {
        return EPARGNE;
    }
    @JsonProperty("EPARGNE")
    public void setEPARGNE(Float EPARGNE) {
        this.EPARGNE = EPARGNE;
    }
    @JsonProperty("VALEUR_PB")
    public Float getVALEUR_PB() {
        return VALEUR_PB;
    }
    @JsonProperty("VALEUR_PB")
    public void setVALEUR_PB(Float VALEUR_PB) {
        this.VALEUR_PB = VALEUR_PB;
    }
    @JsonProperty("VALEUR_RACHAT")
    public Float getVALEUR_RACHAT() {
        return VALEUR_RACHAT;
    }
    @JsonProperty("VALEUR_RACHAT")
    public void setVALEUR_RACHAT(Float VALEUR_RACHAT) {
        this.VALEUR_RACHAT = VALEUR_RACHAT;
    }
    @JsonProperty("EMAIL")
    public String getEMAIL() {
        return EMAIL;
    }
    @JsonProperty("EMAIL")
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
    @JsonProperty("DEBEFFET")
    public String getDEBEFFET() {
        return DEBEFFET;
    }
    @JsonProperty("DEBEFFET")
    public void setDEBEFFET(String DEBEFFET) {
        this.DEBEFFET = DEBEFFET;
    }

    @JsonProperty("FINEFFET")
    public String getFINEFFET() {
        return FINEFFET;
    }
    @JsonProperty("FINEFFET")
    public void setFINEFFET(String FINEFFET) {
        this.FINEFFET = FINEFFET;
    }

    @JsonProperty("SOMME_PRIMGRNT")
    public Float getSOMME_PRIMGRNT() {
        return SOMME_PRIMGRNT;
    }

    @JsonProperty("SOMME_PRIMGRNT")
    public void setSOMME_PRIMGRNT(Float SOMME_PRIMGRNT) {
        this.SOMME_PRIMGRNT = SOMME_PRIMGRNT;
    }



    @JsonProperty("NOMCOMMERCIAL")
    public String getNOMCOMMERCIAL() {
        return NOMCOMMERCIAL;
    }

    @JsonProperty("NOMCOMMERCIAL")
    public void setNOMCOMMERCIAL(String NOMCOMMERCIAL) {
        this.NOMCOMMERCIAL = NOMCOMMERCIAL;
    }

    @JsonProperty("LIBGRNT")
    public String getLIBGRNT() {
        return LIBGRNT;
    }
    @JsonProperty("LIBGRNT")
    public void setLIBGRNT(String LIBGRNT) {
        this.LIBGRNT = LIBGRNT;
    }

    @JsonProperty("NOM_INT")

    public String getNOM_INT() {
        return NOM_INT;
    }

    @JsonProperty("NOM_INT")
    public void setNOM_INT(String NOM_INT) {
        this.NOM_INT = NOM_INT;
    }

    @JsonProperty("FRACT")

    public String getFRACT() {
        return FRACT;
    }


    @JsonProperty("FRACT")
    public void setFRACT(String FRACT) {
        this.FRACT = FRACT;
    }

    @JsonProperty("LIBPRDT")

    public String getLIBPRDT() {
        return LIBPRDT;
    }

    @JsonProperty("LIBPRDT")

    public void setLIBPRDT(String LIBPRDT) {
        this.LIBPRDT = LIBPRDT;
    }

    public Contrat() {
    }

    public Contrat(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, String LIBPRDT, String FRACT, String NOM_INT, String LIBGRNT, String NOMCOMMERCIAL, Float SOMME_PRIMGRNT, String DEBEFFET, String FINEFFET, Integer DATE_LANC, String DATECALPM, String NOMREDCL, Long MNTPRNET, String DUREE, Integer TAUX_RENDEMENT, Float TAUX_TMG, Float EPARGNE, Float VALEUR_PB, Float VALEUR_RACHAT, String EMAIL, String DEBCNT, String FINCNT) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
        this.LIBPRDT = LIBPRDT;
        this.FRACT = FRACT;
        this.NOM_INT = NOM_INT;
        this.LIBGRNT = LIBGRNT;
        this.NOMCOMMERCIAL = NOMCOMMERCIAL;
        this.SOMME_PRIMGRNT = SOMME_PRIMGRNT;
        this.DEBEFFET = DEBEFFET;
        this.FINEFFET = FINEFFET;
        this.DATE_LANC = DATE_LANC;
        this.DATECALPM = DATECALPM;
        this.NOMREDCL = NOMREDCL;
        this.MNTPRNET = MNTPRNET;
        this.DUREE = DUREE;
        this.TAUX_RENDEMENT = TAUX_RENDEMENT;
        this.TAUX_TMG = TAUX_TMG;
        this.EPARGNE = EPARGNE;
        this.VALEUR_PB = VALEUR_PB;
        this.VALEUR_RACHAT = VALEUR_RACHAT;
        this.EMAIL = EMAIL;
        this.DEBCNT = DEBCNT;
        this.FINCNT = FINCNT;
    }

 /* public Contrat(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, String CIN, String LIBPRDT, String FRACT, String NOM_INT, String LIBGRNT, String NOMCOMMERCIAL, String RESULT, String BULL, Float SOMME_PRIMGRNT, String DEBEFFET, String FINEFFET, Long NBUNITLM, String UNTLIMIT, String DEBCNT, String FINCNT, String SITUAT) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
        this.CIN = CIN;
        this.LIBPRDT = LIBPRDT;
        this.FRACT = FRACT;
        this.NOM_INT = NOM_INT;
        this.LIBGRNT = LIBGRNT;
        this.NOMCOMMERCIAL = NOMCOMMERCIAL;
        this.RESULT = RESULT;
        this.BULL = BULL;
        this.SOMME_PRIMGRNT = SOMME_PRIMGRNT;
        this.DEBEFFET = DEBEFFET;
        this.FINEFFET = FINEFFET;
        this.NBUNITLM = NBUNITLM;
        this.UNTLIMIT = UNTLIMIT;
        this.DEBCNT = DEBCNT;
        this.FINCNT = FINCNT;
        this.SITUAT = SITUAT;
    }*/

    @JsonProperty("NUMCNT")
    public String getNUMCNT() {
        return NUMCNT;
    }
    @JsonProperty("NUMCNT")
    public void setNUMCNT(String NUMCNT) {
        this.NUMCNT = NUMCNT;
    }


    @JsonProperty("CODPROD")
    public Long getCODPROD() {
        return CODPROD;
    }
    @JsonProperty("CODPROD")
    public void setCODPROD(Long CODPROD) {
        this.CODPROD = CODPROD;
    }

    @JsonProperty("FORMCNT")
    public String getFORMCNT() {
        return FORMCNT;
    }

    @JsonProperty("FORMCNT")
    public void setFORMCNT(String FORMCNT) {
        this.FORMCNT = FORMCNT;
    }


    @JsonProperty("NATCLT")
    public String getNATCLT() {
        return NATCLT;
    }

    @JsonProperty("NATCLT")
    public void setNATCLT(String NATCLT) {
        this.NATCLT = NATCLT;
    }

    @JsonProperty("IDCLT")
    public Long getIDCLT() {
        return IDCLT;
    }

    @JsonProperty("IDCLT")
    public void setIDCLT(Long IDCLT) {
        this.IDCLT = IDCLT;
    }

    @JsonProperty("IDDELEGA")
    public Long getIDDELEGA() {
        return IDDELEGA;
    }

    @JsonProperty("IDDELEGA")
    public void setIDDELEGA(Long IDDELEGA) {
        this.IDDELEGA = IDDELEGA;
    }


    @JsonProperty("DEBCNT")
    public String getDEBCNT() {
        return DEBCNT;
    }


    @JsonProperty("DEBCNT")
    public void setDEBCNT(String DEBCNT) {
        this.DEBCNT = DEBCNT;
    }


    @JsonProperty("FINCNT")
    public String getFINCNT() {
        return FINCNT;
    }


    @JsonProperty("FINCNT")
    public void setFINCNT(String FINCNT) {
        this.FINCNT = FINCNT;
    }

    @JsonProperty("SITUAT")
    public String getSITUAT() {
        return SITUAT;
    }

    @JsonProperty("SITUAT")

    public void setSITUAT(String SITUAT) {
        this.SITUAT = SITUAT;
    }

    private String DEBCNT;
    private String FINCNT;

    private String SITUAT;

}
