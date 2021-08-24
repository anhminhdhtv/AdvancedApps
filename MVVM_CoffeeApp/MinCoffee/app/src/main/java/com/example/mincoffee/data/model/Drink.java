package com.example.mincoffee.data.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Drink implements Serializable {
    private String name;
    private String description;
    private String imageURL;
    private int price;
    private int type;

    public Drink (String name, String description, String imageURL, int price, int type){
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.type = type;
    }
}
