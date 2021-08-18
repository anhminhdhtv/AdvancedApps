package com.example.mincoffee.data.model;

import android.icu.util.LocaleData;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private String phoneNumber;
    private String userName;
    private String imageURL;
    private LocalDate birthday;
    private String address;

    public User(String phoneNumber, String userName, String imageURL, LocalDate birthday, String address) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.imageURL = imageURL;
        this.birthday = birthday;
        this.address = address;
    }
}
