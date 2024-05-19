package com.example.verket.Model;

public class partenaire {
    public String namepart;
    public String prenpart;
    public String emailpart;
    public String telepart;
    public String marquepart;
    public String descmarque;
    public String uplimage;
    public String upcartnat;
    public String imagemarque;

    public String situation ;

    public String id ;

    public partenaire(String namepart, String prenpart, String emailpart, String telepart, String marquepart, String descmarque, String uplimage, String upcartnat, String imagemarque, String situation,String id) {
        this.namepart = namepart;
        this.prenpart = prenpart;
        this.emailpart = emailpart;
        this.telepart = telepart;
        this.marquepart = marquepart;
        this.descmarque = descmarque;
        this.uplimage = uplimage;
        this.upcartnat = upcartnat;
        this.imagemarque = imagemarque;
        this.situation = situation;

        this.id  = id ;
    }


    public partenaire() {
    }


    public String getNamepart() {
        return namepart;
    }

    public void setNamepart(String namepart) {
        this.namepart = namepart;
    }

    public String getPrenpart() {
        return prenpart;
    }

    public void setPrenpart(String prenpart) {
        this.prenpart = prenpart;
    }

    public String getEmailpart() {
        return emailpart;
    }

    public void setEmailpart(String emailpart) {
        this.emailpart = emailpart;
    }

    public String getTelepart() {
        return telepart;
    }

    public void setTelepart(String telepart) {
        this.telepart = telepart;
    }

    public String getMarquepart() {
        return marquepart;
    }

    public void setMarquepart(String marquepart) {
        this.marquepart = marquepart;
    }

    public String getDescmarque() {
        return descmarque;
    }

    public void setDescmarque(String descmarque) {
        this.descmarque = descmarque;
    }

    public String getUplimage() {
        return uplimage;
    }

    public void setUplimage(String uplimage) {
        this.uplimage = uplimage;
    }

    public String getUpcartnat() {
        return upcartnat;
    }

    public void setUpcartnat(String upcartnat) {
        this.upcartnat = upcartnat;
    }

    public String getImagemarque() {
        return imagemarque;
    }

    public void setImagemarque(String imagemarque) {
        this.imagemarque = imagemarque;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
