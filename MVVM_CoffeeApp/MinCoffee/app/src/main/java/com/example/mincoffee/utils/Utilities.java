package com.example.mincoffee.utils;

import java.text.DecimalFormat;

public class Utilities {
    public static String castToStringPrice(int price){
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        return myFormatter.format(price);
    }
}
