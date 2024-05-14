package com.example.verket.Model;

public class CardModel {
    private String title;
    private int imageResource;

    public CardModel(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }
}
