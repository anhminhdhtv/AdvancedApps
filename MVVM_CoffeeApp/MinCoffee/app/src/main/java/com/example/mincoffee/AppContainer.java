package com.example.mincoffee;

import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;

public class AppContainer {
    public UserRepository userRepository = new UserRepository();
    public DrinkRepository drinkRepository = new DrinkRepository();
}
