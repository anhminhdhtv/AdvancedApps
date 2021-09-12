package com.example.mincoffee.data.model;

import com.example.mincoffee.data.enums.DrinkSize;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReservedDrink implements Serializable {
    private Drink drink;
    private int amount;
    private DrinkSize size;
    private int totalPrice;

    public ReservedDrink(Drink drink, int amount, DrinkSize size) {
        this.drink = drink;
        this.amount = amount;
        this.size = size;
    }

    public ReservedDrink() {
    }

    public int getTotalPrice() {
        return (drink.getPrice() + getSizePrice(this.size)) * amount;
    }

    public void increaseItemNumber() {
        amount++;
    }

    public void decreaseItemNumber() {
        amount--;
    }

    private int getSizePrice(DrinkSize size) {
        int price = 0;
        if (size.equals(DrinkSize.LARGER)) {
            price = 10000;
        }
        return price;
    }
}
