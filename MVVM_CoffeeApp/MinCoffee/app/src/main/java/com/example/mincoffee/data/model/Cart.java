package com.example.mincoffee.data.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ReservedDrink> reservedDrinkList = new ArrayList<>();
    private Voucher voucher;
    private int totalPrice;
}
