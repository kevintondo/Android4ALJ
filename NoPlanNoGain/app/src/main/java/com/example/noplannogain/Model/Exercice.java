package com.example.noplannogain.Model;

public class Exercice {

    private int image_id;
    private String name;

    public Exercice(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
