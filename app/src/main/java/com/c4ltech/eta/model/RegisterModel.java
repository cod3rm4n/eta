package com.c4ltech.eta.model;

public class RegisterModel {
    private String ad;
    private String soyad;
    private String eMail;
    private String registerPass;
    private String sehir;
    private String cepNo;



    public RegisterModel() {

    }

    public RegisterModel(String ad, String soyad, String eMail, String registerPass, String sehir, String cepNo) {
        this.ad = ad;
        this.soyad = soyad;
        this.eMail = eMail;
        this.registerPass = registerPass;
        this.sehir = sehir;
        this.cepNo = cepNo;

    }

    public String getAd () {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRegisterPass() {
        return registerPass;
    }

    public void setRegisterPass(String registerPass) {
        this.registerPass = registerPass;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getCepNo() {
        return cepNo;
    }

    public void setCepNo(String cepNo) {
        this.cepNo = cepNo;
    }


}

