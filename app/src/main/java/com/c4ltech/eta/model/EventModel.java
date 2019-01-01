package com.c4ltech.eta.model;
public class EventModel {
    private String etkinlikTitle;
    private String etkinlikAciklama;
    private String etkinlikYeri;
    private String etkinlikTarih;
    private String etkinlikDüzenleyen;


    public String getEtkinlikTitle() {
        return etkinlikTitle;
    }

    public void setEtkinlikTitle(String etkinlikTitle) {
        this.etkinlikTitle = etkinlikTitle;
    }


    public String getEtkinlikAciklama() {
        return etkinlikAciklama;
    }

    public void setEtkinlikAciklama(String etkinlikAciklama) {
        this.etkinlikAciklama = etkinlikAciklama;
    }

    public String getEtkinlikYeri() {
        return etkinlikYeri;
    }

    public void setEtkinlikYeri(String etkinlikYeri) {
        this.etkinlikYeri = etkinlikYeri;
    }

    public String getEtkinlikTarih() {
        return etkinlikTarih;
    }

    public void setEtkinlikTarih(String etkinlikTarih) {
        this.etkinlikTarih = etkinlikTarih;
    }

    public String getEtkinlikDüzenleyen() {
        return etkinlikDüzenleyen;
    }

    public void setEtkinlikDüzenleyen(String etkinlikDüzenleyen) {
        this.etkinlikDüzenleyen = etkinlikDüzenleyen;
    }


    public EventModel(String etkinlikTitle, int etkinlikFoto, String etkinlikAciklama, String etkinlikYeri, String etkinlikTarih, String etkinlikDüzenleyen) {
        this.etkinlikTitle = etkinlikTitle;
        this.etkinlikAciklama = etkinlikAciklama;
        this.etkinlikYeri = etkinlikYeri;
        this.etkinlikTarih = etkinlikTarih;
        this.etkinlikDüzenleyen = etkinlikDüzenleyen;
    }
    public EventModel(){

    }

}
