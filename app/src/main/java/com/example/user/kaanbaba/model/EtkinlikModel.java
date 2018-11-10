package com.example.user.kaanbaba.model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EtkinlikModel {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String EtkinlikBaslik;
    String EtkinlikAciklama;
    String EtkinlikTarih;
    String id;
    String pictureUrl;
    int resim;
//    int Position;

    public EtkinlikModel() {

    }

    public String getId() {
        return id;

    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtkinlikTarih() {
        return EtkinlikTarih;
    }

    public void setEtkinlikTarih(String etkinlikTarih) {
        EtkinlikTarih = etkinlikTarih;
    }
    public String getEtkinlikAciklama() {
        return EtkinlikAciklama;
    }

    public void setEtkinlikAciklama(String etkinlikAciklama) {
        EtkinlikAciklama = etkinlikAciklama;
    }
    public String getEtkinlikBaslik() {
        return EtkinlikBaslik;
    }

    public void setEtkinlikBaslik(String etkinlikBaslik) {
        EtkinlikBaslik = etkinlikBaslik;
    }

//    public int getPosition() {
//        return Position;
//    }
//
//    public void setPosition(int position) {
//        Position = position;
//    }

    public EtkinlikModel(String etkinlikName, String etkinlikAciklama, String etkinlikTarih,String id,String pictureUrl) {
        this.id =id;
        this.EtkinlikBaslik=etkinlikName;
        this.EtkinlikAciklama=etkinlikAciklama;
        this.EtkinlikTarih=etkinlikTarih;
        this.pictureUrl = pictureUrl;
//      this.Position=position;
    }

}
