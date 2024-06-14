package com.example.verket.Model;

public class ProduitModel {
    public String id;
    public String imageproduit;
    public String nameproduit;
    public String descriptionproduit;
    public String prixproduit;
    public String quantiteproduit;
    public String categorieproduit;
    public String codepromo;
    public String pourcentagedecodepromo;
    public String pourcentage;

    public String date_pirime;


    public ProduitModel(String id, String imageproduit, String nameproduit, String descriptionproduit, String prixproduit, String quantiteproduit, String categorieproduit, String codepromo, String pourcentagedecodepromo, String pourcentage, String date_pirime) {
        this.id = id;
        this.imageproduit = imageproduit;
        this.nameproduit = nameproduit;
        this.descriptionproduit = descriptionproduit;
        this.prixproduit = prixproduit;
        this.quantiteproduit = quantiteproduit;
        this.categorieproduit = categorieproduit;
        this.codepromo = codepromo;
        this.pourcentagedecodepromo = pourcentagedecodepromo;
        this.pourcentage = pourcentage;
        this.date_pirime = date_pirime;
    }

    public ProduitModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageproduit() {
        return imageproduit;
    }

    public void setImageproduit(String imageproduit) {
        this.imageproduit = imageproduit;
    }

    public String getNameproduit() {
        return nameproduit;
    }

    public void setNameproduit(String nameproduit) {
        this.nameproduit = nameproduit;
    }

    public String getDescriptionproduit() {
        return descriptionproduit;
    }

    public void setDescriptionproduit(String descriptionproduit) {
        this.descriptionproduit = descriptionproduit;
    }

    public String getPrixproduit() {
        return prixproduit;
    }

    public void setPrixproduit(String prixproduit) {
        this.prixproduit = prixproduit;
    }

    public String getQuantiteproduit() {
        return quantiteproduit;
    }

    public void setQuantiteproduit(String quantiteproduit) {
        this.quantiteproduit = quantiteproduit;
    }

    public String getCategorieproduit() {
        return categorieproduit;
    }

    public void setCategorieproduit(String categorieproduit) {
        this.categorieproduit = categorieproduit;
    }

    public String getCodepromo() {
        return codepromo;
    }

    public void setCodepromo(String codepromo) {
        this.codepromo = codepromo;
    }

    public String getPourcentagedecodepromo() {
        return pourcentagedecodepromo;
    }

    public void setPourcentagedecodepromo(String pourcentagedecodepromo) {
        this.pourcentagedecodepromo = pourcentagedecodepromo;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getDate_pirime() {
        return date_pirime;
    }

    public void setDate_pirime(String date_pirime) {
        this.date_pirime = date_pirime;
    }
}
