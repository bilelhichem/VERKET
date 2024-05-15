package com.example.verket.Model;

public class UserModel {

    public String nameus ;
    public String prenus ;
    public String emailus;

    public UserModel(String nameus, String prenus, String emailus) {
        this.nameus = nameus;
        this.prenus = prenus;
        this.emailus = emailus;
    }

    public String getNameus() {
        return nameus;
    }

    public void setNameus(String nameus) {
        this.nameus = nameus;
    }

    public String getPrenus() {
        return prenus;
    }

    public void setPrenus(String prenus) {
        this.prenus = prenus;
    }

    public String getEmailus() {
        return emailus;
    }

    public void setEmailus(String emailus) {
        this.emailus = emailus;
    }
}
