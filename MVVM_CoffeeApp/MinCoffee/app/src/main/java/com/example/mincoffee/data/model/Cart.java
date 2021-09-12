package com.example.mincoffee.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart implements Serializable {
    private List<ReservedDrink> reservedDrinkList = new ArrayList<>();
    private Voucher voucher;

    public int getTotalPrice() {
        int totalPrice = 0;
        if (reservedDrinkList.size() != 0) {
            for (ReservedDrink drink :
                    reservedDrinkList) {
                totalPrice += drink.getTotalPrice();
            }
        }
        return totalPrice;
    }

    public int getTotalDrinks(){
        int total = 0;
        if (reservedDrinkList.size() != 0) {
            for (ReservedDrink drink :
                    reservedDrinkList) {
                total += drink.getAmount();
            }
        }
        return total;
    }

    public void addReservedDrink(ReservedDrink reservedDrink) {
        reservedDrinkList.add(reservedDrink);
    }

    public void removeReservedDrink(int index) {
        reservedDrinkList.remove(index);
    }

    public void updateReservedDrink(int index, ReservedDrink reservedDrink) {
        reservedDrinkList.set(index, reservedDrink);
    }

    public int getRefund(){
//        return Math.round(getTotalPrice()*(voucher.getDiscountPercent()/100));
        return 10000;
    }



    public int getToPay(){
        return this.getTotalPrice() - getRefund();
    }
}
