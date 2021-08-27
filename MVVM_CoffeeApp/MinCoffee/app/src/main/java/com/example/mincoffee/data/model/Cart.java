package com.example.mincoffee.data.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart {
    private List<ReservedDrink> reservedDrinkList = new ArrayList<>();
    private Voucher voucher;
    private int totalPrice;

    public int getTotalPrice() {
        if (reservedDrinkList.size() != 0) {
            for (ReservedDrink drink :
                    reservedDrinkList) {
                totalPrice += drink.getTotalPrice();
            }
        }
        return totalPrice;
    }

    public void addReservedDrink(ReservedDrink reservedDrink) {
        reservedDrinkList.add(reservedDrink);
    }

    public void removeReservedDrink(ReservedDrink reservedDrink) {
        reservedDrinkList.remove(reservedDrink);
    }

    public void updateReservedDrink(ReservedDrink reservedDrink) {
        int index = reservedDrinkList.indexOf(reservedDrink);
        reservedDrinkList.set(index, reservedDrink);
    }
}
