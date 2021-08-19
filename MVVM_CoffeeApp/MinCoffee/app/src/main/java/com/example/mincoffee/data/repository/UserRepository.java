package com.example.mincoffee.data.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mincoffee.data.model.User;

import java.time.LocalDate;

public class UserRepository {
    public LiveData<User> getUserInfo(){
        MutableLiveData<User> data = new MutableLiveData<>();
        data.postValue(generateMockUser());
        return data;
    }

    private User generateMockUser(){
        return new User("0978836331",
                "Minh",
                "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                LocalDate.of(1996, 11, 12),
                "No Trang Long");
    }
}
