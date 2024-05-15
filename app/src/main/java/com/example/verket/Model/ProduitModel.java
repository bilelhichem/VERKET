package com.example.verket.Model;

public class ProduitModel {
    public String  imageproduit ;
    public  String nameproduit ;
    public  String descriptionproduit  ;
    public  String prixproduit ;
    public  String quantiteproduit ;
    public  String categorieproduit ;
    public String codepromo ;
    public String pourcentage ;

    public ProduitModel(String imageproduit, String nameproduit, String descriptionproduit, String prixproduit, String quantiteproduit, String categorieproduit, String codepromo, String pourcentage) {
        this.imageproduit = imageproduit;
        this.nameproduit = nameproduit;
        this.descriptionproduit = descriptionproduit;
        this.prixproduit = prixproduit;
        this.quantiteproduit = quantiteproduit;
        this.categorieproduit = categorieproduit;
        this.codepromo = codepromo;
        this.pourcentage = pourcentage;
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

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }
}
