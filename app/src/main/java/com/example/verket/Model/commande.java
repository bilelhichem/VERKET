package com.example.verket.Model;

public class commande {
    String email ;
    String numerodetele ;
    String imagedeprod ;
    String quantite ;
    String pricetotal ;

    public commande(String email, String numerodetele, String imagedeprod, String quantite, String pricetotal) {

        this.email = email;
        this.numerodetele = numerodetele;
        this.imagedeprod = imagedeprod;
        this.quantite = quantite;
        this.pricetotal = pricetotal;
    }

    public commande() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerodetele() {
        return numerodetele;
    }

    public void setNumerodetele(String numerodetele) {
        this.numerodetele = numerodetele;
    }

    public String getImagedeprod() {
        return imagedeprod;
    }

    public void setImagedeprod(String imagedeprod) {
        this.imagedeprod = imagedeprod;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPricetotal() {
        return pricetotal;
    }

    public void setPricetotal(String pricetotal) {
        this.pricetotal = pricetotal;
    }
}
