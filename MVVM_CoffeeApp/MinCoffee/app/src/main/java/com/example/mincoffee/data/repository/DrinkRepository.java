package com.example.mincoffee.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mincoffee.data.model.Drink;

import java.util.Arrays;
import java.util.List;

public class DrinkRepository {

    public LiveData<List<Drink>> getDrinkList() {
        MutableLiveData<List<Drink>> data = new MutableLiveData<>();
        data.postValue(generateMockDrinkList());
        return data;
    }

    private List<Drink> generateMockDrinkList() {
        return Arrays.asList(
                new Drink("Coffee",
                        "description1",
                        "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                        32000,
                        0),
                new Drink("Coffee",
                        "description1",
                        "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                        32000,
                        0),
                new Drink("Coffee",
                        "description1",
                        "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                        32000,
                        0),
                new Drink(
                        "Coffee",
                        "description1",
                        "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                        32000,
                        0),
                new Drink("Coffee",
                        "description1",
                        "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                        32000,
                        0));
    }

}
