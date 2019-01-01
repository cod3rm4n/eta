package com.c4ltech.eta.model;

public class BildirimModel {

    String BildiriBaslik;

    String BildiriTarih;
    String idm;
    String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BildirimModel() {


    }

    public BildirimModel(String idm) {
        this.idm = idm;
    }

    public String getIdm() {
        return idm;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }




    public BildirimModel(String bildiriBaslik, String bildiriTarih,String idma) {
       this.BildiriBaslik = bildiriBaslik;
        this.BildiriTarih = bildiriTarih;
        this.idm=idma;

    }


    public String getBildiriBaslik() {
        return BildiriBaslik;
    }

    public void setBildiriBaslik(String bildiriBaslik) {
        BildiriBaslik = bildiriBaslik;
    }

    public String getBildiriTarih() {
        return BildiriTarih;
    }

    public void setBildiriTarih(String bildiriTarih) {
        BildiriTarih = bildiriTarih;
    }

}
