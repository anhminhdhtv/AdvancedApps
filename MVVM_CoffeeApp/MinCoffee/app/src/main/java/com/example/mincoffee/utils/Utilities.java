package com.example.mincoffee.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String castToStringPrice(int price){
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        return myFormatter.format(price);
    }

    public static String formatDate (LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
}
