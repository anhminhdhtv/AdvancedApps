package com.example.mincoffee.di.component;

import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;
import com.example.mincoffee.viewModel.ViewModelProviderFactory;

public class AppComponent {
    public UserRepository userRepository = new UserRepository();
    public DrinkRepository drinkRepository = new DrinkRepository();
    public ViewModelProviderFactory viewModelProviderFactory = new ViewModelProviderFactory(
            userRepository,
            drinkRepository
    );

}
