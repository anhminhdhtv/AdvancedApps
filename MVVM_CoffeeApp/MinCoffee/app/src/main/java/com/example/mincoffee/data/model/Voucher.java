package com.example.mincoffee.data.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Voucher {
    private String name;
    private String condition;
    private int discountPercent;
    private int maxDiscount;
    private LocalDate expiredTime;
}
