package com.example.mincoffee.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mincoffee.data.model.Drink;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DrinkRepository {

    private final List<Drink> mMockDrinkList = Arrays.asList(
            new Drink("Coffee 1",
                    "description 1",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    0),
            new Drink("Coffee 2",
                    "description 2",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    0),
            new Drink("Coffee 3",
                    "description 3",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    0),
            new Drink(
                    "Coffee 4",
                    "description 4",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    0),
            new Drink("Coffee 5",
                    "description 5",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    0),
            new Drink("Tea 1",
                    "description 1",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    1),
            new Drink("Tea 2",
                    "description 2",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    1),
            new Drink("Tea 3",
                    "description3",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    1),
            new Drink(
                    "Tea 4",
                    "description 4",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    1),
            new Drink("Tea 5",
                    "description5",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    1),
            new Drink("Fruit 1",
                    "description 1",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    2),
            new Drink("Fruit 2",
                    "description 2",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    2),
            new Drink("Fruit 3",
                    "description3",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    2),
            new Drink(
                    "Fruit 4",
                    "description 4",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    2),
            new Drink("Fruit 5",
                    "description5",
                    "https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg",
                    32000,
                    2));

    public LiveData<List<Drink>> getDrinkList() {
        MutableLiveData<List<Drink>> data = new MutableLiveData<>();
        data.postValue(mMockDrinkList);
        return data;
    }

    public LiveData<List<Drink>> getDrinkList(int drinkType) {
        MutableLiveData<List<Drink>> data = new MutableLiveData<>();
        data.postValue(mMockDrinkList.stream()
                .filter(drink -> drink.getType() == drinkType)
                .collect(Collectors.toList()));
        return data;
    }

}
