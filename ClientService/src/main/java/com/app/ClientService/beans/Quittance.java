package com.app.ClientService.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Quittance {

    @JsonProperty("NUMQUITT")
    private long numQuitt;

    @JsonProperty("NUMCNT")
    private String numCnt;

    @JsonProperty("NUMAVT")
    private int numAvt;

    @JsonProperty("NUMMAJ")
    private int numMaj;

    @JsonProperty("CODORSYS")
    private String codOrSys;

    @JsonProperty("DATECREA")
    private int dateCrea;

    @JsonProperty("TYPEEMMIS")
    private String typeEmmis;

    @JsonProperty("CODAVT")
    private String codAvt;

    @JsonProperty("CODFAM")
    private String codFam;

    @JsonProperty("CODPROD")
    private String codProd;

    @JsonProperty("CODFORMU")
    private String codFormu;

    @JsonProperty("CODEDEVIS")
    private String codeDevis;

    @JsonProperty("ECHANNIV")
    private int echAnniv;

    @JsonProperty("FRACT")
    private String fract;

    @JsonProperty("NATCLT")
    private String natClt;

    @JsonProperty("IDCLT")
    private long idClt;

    @JsonProperty("NOMREDCL")
    private String nomRedCl;

    @JsonProperty("ADRCORES")
    private int adrCores;

    @JsonProperty("NATINT")
    private String natInt;

    @JsonProperty("IDINT")
    private int idInt;

    @JsonProperty("IDDELEGA")
    private int idDelega;

    @JsonProperty("CODECONFI")
    private String codeConfi;

    @JsonProperty("CODECOASS")
    private String codeCoAss;

    @JsonProperty("CODEREASS")
    private String codeReAss;

    @JsonProperty("STATQUIT")
    private String statQuit;

    @JsonProperty("STADECTX")
    private String stadeCtx;

    @JsonProperty("DATEEMISS")
    private int dateEmiss;

    @JsonProperty("DEBEFFQUI")
    private int debEffQui;

    @JsonProperty("FINEFFQUI")
    private int finEffQui;

    @JsonProperty("DATEANNUL")
    private int dateAnnul;

    @JsonProperty("DATEPAIEM")
    private int datePaiem;

    @JsonProperty("MOTIFANNU")
    private String motifAnnu;

    @JsonProperty("MNTPRNET")
    private double mntPrNet;

    @JsonProperty("MNTACCESS")
    private double mntAccess;

    @JsonProperty("MNTFRAPPE")
    private double mntFrappe;

    @JsonProperty("MNTPRASSI")
    private double mntPrAssi;

    @JsonProperty("MNTCOMMIS")
    private double mntCommis;

    @JsonProperty("MNTTAXE")
    private double mntTaxe;

    @JsonProperty("TTCOMPTAX")
    private double ttCompTax;

    @JsonProperty("COMPTAXE1")
    private double compTaxe1;

    @JsonProperty("COMPTAXE2")
    private double compTaxe2;

    @JsonProperty("COMPTAXE3")
    private double compTaxe3;

    @JsonProperty("MNTPRIMET")
    private double mntPrimEt;

    @JsonProperty("MNTNETREG")
    private double mntNetReg;

    @JsonProperty("MNTACOMPT")
    private double mntAcompt;

    @JsonProperty("MNTCOMPAY")
    private double mntCompay;

    @JsonProperty("DATEPREVE")
    private int datePreve;

    @JsonProperty("INDPOLY")
    private String indPoly;

    @JsonProperty("INSERTFC")
    private String insertFC;

    @JsonProperty("GESTIONAI")
    private String gestionAi;

    @JsonProperty("GESTANNUL")
    private String gestAnnul;

    @JsonProperty("UPDATE_IDENT")
    private int updateIdent;

    @JsonProperty("CNAT")
    private String cnat;

    @JsonProperty("NUMPERS")
    private long numPers;

    @JsonProperty("CLASSPERS")
    private String classPers;

    @JsonProperty("TYPEID")
    private String typeId;

    @JsonProperty("ID")
    private String id;

    @JsonProperty("TITRE")
    private String titre;

    @JsonProperty("NOM1")
    private String nom1;

    @JsonProperty("NOM2")
    private String nom2;

    @JsonProperty("NATIONAL")
    private String national;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("DATEP")
    private int dateP;

    @JsonProperty("TYPEPERS")
    private String typePers;

    @JsonProperty("USER")
    private String user;

    @JsonProperty("USER1")
    private String user1;

    @JsonProperty("LIBPRDT")
    private String libPrdt;

    @JsonProperty("DATEFFET")
    private int dateEffet;

    @JsonProperty("TYPRISQ")
    private String typRisque;

    @JsonProperty("PRERESIL")
    private int preResil;

    public long getNumQuitt() {
        return numQuitt;
    }

    public void setNumQuitt(long numQuitt) {
        this.numQuitt = numQuitt;
    }

    public String getNumCnt() {
        return numCnt;
    }

    public void setNumCnt(String numCnt) {
        this.numCnt = numCnt;
    }

    public int getNumAvt() {
        return numAvt;
    }

    public void setNumAvt(int numAvt) {
        this.numAvt = numAvt;
    }

    public int getNumMaj() {
        return numMaj;
    }

    public void setNumMaj(int numMaj) {
        this.numMaj = numMaj;
    }

    public String getCodOrSys() {
        return codOrSys;
    }

    public void setCodOrSys(String codOrSys) {
        this.codOrSys = codOrSys;
    }

    public int getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(int dateCrea) {
        this.dateCrea = dateCrea;
    }

    public String getTypeEmmis() {
        return typeEmmis;
    }

    public void setTypeEmmis(String typeEmmis) {
        this.typeEmmis = typeEmmis;
    }

    public String getCodAvt() {
        return codAvt;
    }

    public void setCodAvt(String codAvt) {
        this.codAvt = codAvt;
    }

    public String getCodFam() {
        return codFam;
    }

    public void setCodFam(String codFam) {
        this.codFam = codFam;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getCodFormu() {
        return codFormu;
    }

    public void setCodFormu(String codFormu) {
        this.codFormu = codFormu;
    }

    public String getCodeDevis() {
        return codeDevis;
    }

    public void setCodeDevis(String codeDevis) {
        this.codeDevis = codeDevis;
    }

    public int getEchAnniv() {
        return echAnniv;
    }

    public void setEchAnniv(int echAnniv) {
        this.echAnniv = echAnniv;
    }

    public String getFract() {
        return fract;
    }

    public void setFract(String fract) {
        this.fract = fract;
    }

    public String getNatClt() {
        return natClt;
    }

    public void setNatClt(String natClt) {
        this.natClt = natClt;
    }

    public long getIdClt() {
        return idClt;
    }

    public void setIdClt(long idClt) {
        this.idClt = idClt;
    }

    public String getNomRedCl() {
        return nomRedCl;
    }

    public void setNomRedCl(String nomRedCl) {
        this.nomRedCl = nomRedCl;
    }

    public int getAdrCores() {
        return adrCores;
    }

    public void setAdrCores(int adrCores) {
        this.adrCores = adrCores;
    }

    public String getNatInt() {
        return natInt;
    }

    public void setNatInt(String natInt) {
        this.natInt = natInt;
    }

    public int getIdInt() {
        return idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    public int getIdDelega() {
        return idDelega;
    }

    public void setIdDelega(int idDelega) {
        this.idDelega = idDelega;
    }

    public String getCodeConfi() {
        return codeConfi;
    }

    public void setCodeConfi(String codeConfi) {
        this.codeConfi = codeConfi;
    }

    public String getCodeCoAss() {
        return codeCoAss;
    }

    public void setCodeCoAss(String codeCoAss) {
        this.codeCoAss = codeCoAss;
    }

    public String getCodeReAss() {
        return codeReAss;
    }

    public void setCodeReAss(String codeReAss) {
        this.codeReAss = codeReAss;
    }

    public String getStatQuit() {
        return statQuit;
    }

    public void setStatQuit(String statQuit) {
        this.statQuit = statQuit;
    }

    public String getStadeCtx() {
        return stadeCtx;
    }

    public void setStadeCtx(String stadeCtx) {
        this.stadeCtx = stadeCtx;
    }

    public int getDateEmiss() {
        return dateEmiss;
    }

    public void setDateEmiss(int dateEmiss) {
        this.dateEmiss = dateEmiss;
    }

    public int getDebEffQui() {
        return debEffQui;
    }

    public void setDebEffQui(int debEffQui) {
        this.debEffQui = debEffQui;
    }

    public int getFinEffQui() {
        return finEffQui;
    }

    public void setFinEffQui(int finEffQui) {
        this.finEffQui = finEffQui;
    }

    public int getDateAnnul() {
        return dateAnnul;
    }

    public void setDateAnnul(int dateAnnul) {
        this.dateAnnul = dateAnnul;
    }

    public int getDatePaiem() {
        return datePaiem;
    }

    public void setDatePaiem(int datePaiem) {
        this.datePaiem = datePaiem;
    }

    public String getMotifAnnu() {
        return motifAnnu;
    }

    public void setMotifAnnu(String motifAnnu) {
        this.motifAnnu = motifAnnu;
    }

    public double getMntPrNet() {
        return mntPrNet;
    }

    public void setMntPrNet(double mntPrNet) {
        this.mntPrNet = mntPrNet;
    }

    public double getMntAccess() {
        return mntAccess;
    }

    public void setMntAccess(double mntAccess) {
        this.mntAccess = mntAccess;
    }

    public double getMntFrappe() {
        return mntFrappe;
    }

    public void setMntFrappe(double mntFrappe) {
        this.mntFrappe = mntFrappe;
    }

    public double getMntPrAssi() {
        return mntPrAssi;
    }

    public void setMntPrAssi(double mntPrAssi) {
        this.mntPrAssi = mntPrAssi;
    }

    public double getMntCommis() {
        return mntCommis;
    }

    public void setMntCommis(double mntCommis) {
        this.mntCommis = mntCommis;
    }

    public double getMntTaxe() {
        return mntTaxe;
    }

    public void setMntTaxe(double mntTaxe) {
        this.mntTaxe = mntTaxe;
    }

    public double getTtCompTax() {
        return ttCompTax;
    }

    public void setTtCompTax(double ttCompTax) {
        this.ttCompTax = ttCompTax;
    }

    public double getCompTaxe1() {
        return compTaxe1;
    }

    public void setCompTaxe1(double compTaxe1) {
        this.compTaxe1 = compTaxe1;
    }

    public double getCompTaxe2() {
        return compTaxe2;
    }

    public void setCompTaxe2(double compTaxe2) {
        this.compTaxe2 = compTaxe2;
    }

    public double getCompTaxe3() {
        return compTaxe3;
    }

    public void setCompTaxe3(double compTaxe3) {
        this.compTaxe3 = compTaxe3;
    }

    public double getMntPrimEt() {
        return mntPrimEt;
    }

    public void setMntPrimEt(double mntPrimEt) {
        this.mntPrimEt = mntPrimEt;
    }

    public double getMntNetReg() {
        return mntNetReg;
    }

    public void setMntNetReg(double mntNetReg) {
        this.mntNetReg = mntNetReg;
    }

    public double getMntAcompt() {
        return mntAcompt;
    }

    public void setMntAcompt(double mntAcompt) {
        this.mntAcompt = mntAcompt;
    }

    public double getMntCompay() {
        return mntCompay;
    }

    public void setMntCompay(double mntCompay) {
        this.mntCompay = mntCompay;
    }

    public int getDatePreve() {
        return datePreve;
    }

    public void setDatePreve(int datePreve) {
        this.datePreve = datePreve;
    }

    public String getIndPoly() {
        return indPoly;
    }

    public void setIndPoly(String indPoly) {
        this.indPoly = indPoly;
    }

    public String getInsertFC() {
        return insertFC;
    }

    public void setInsertFC(String insertFC) {
        this.insertFC = insertFC;
    }

    public String getGestionAi() {
        return gestionAi;
    }

    public void setGestionAi(String gestionAi) {
        this.gestionAi = gestionAi;
    }

    public String getGestAnnul() {
        return gestAnnul;
    }

    public void setGestAnnul(String gestAnnul) {
        this.gestAnnul = gestAnnul;
    }

    public int getUpdateIdent() {
        return updateIdent;
    }

    public void setUpdateIdent(int updateIdent) {
        this.updateIdent = updateIdent;
    }

    public String getCnat() {
        return cnat;
    }

    public void setCnat(String cnat) {
        this.cnat = cnat;
    }

    public long getNumPers() {
        return numPers;
    }

    public void setNumPers(long numPers) {
        this.numPers = numPers;
    }

    public String getClassPers() {
        return classPers;
    }

    public void setClassPers(String classPers) {
        this.classPers = classPers;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom1() {
        return nom1;
    }

    public void setNom1(String nom1) {
        this.nom1 = nom1;
    }

    public String getNom2() {
        return nom2;
    }

    public void setNom2(String nom2) {
        this.nom2 = nom2;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDateP() {
        return dateP;
    }

    public void setDateP(int dateP) {
        this.dateP = dateP;
    }

    public String getTypePers() {
        return typePers;
    }

    public void setTypePers(String typePers) {
        this.typePers = typePers;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getLibPrdt() {
        return libPrdt;
    }

    public void setLibPrdt(String libPrdt) {
        this.libPrdt = libPrdt;
    }

    public int getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(int dateEffet) {
        this.dateEffet = dateEffet;
    }

    public String getTypRisque() {
        return typRisque;
    }

    public void setTypRisque(String typRisque) {
        this.typRisque = typRisque;
    }

    public int getPreResil() {
        return preResil;
    }

    public void setPreResil(int preResil) {
        this.preResil = preResil;
    }


    public Quittance() {
    }



}
