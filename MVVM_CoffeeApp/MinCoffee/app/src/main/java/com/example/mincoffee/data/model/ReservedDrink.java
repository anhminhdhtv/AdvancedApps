package com.example.mincoffee.data.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReservedDrink implements Serializable {
    private Drink drink;
    private int amount;
    private int size;
    private int totalPrice;

    public ReservedDrink(Drink drink, int amount, int size, int totalPrice) {
        this.drink = drink;
        this.amount = amount;
        this.size = size;
        this.totalPrice = totalPrice;
    }
    public ReservedDrink(){}
}
