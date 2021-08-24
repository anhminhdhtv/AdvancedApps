package com.example.mincoffee.viewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;
import com.example.mincoffee.ui.main.oder.list.OrderViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final UserRepository mUserRepository;
    private final DrinkRepository mDrinkRepository;

    public MainViewModelFactory(UserRepository userRepository, DrinkRepository drinkRepository) {
        this.mUserRepository = userRepository;
        this.mDrinkRepository = drinkRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == OrderViewModel.class) {
            return (T) new OrderViewModel(this.mUserRepository, this.mDrinkRepository);
        } else {
            return super.create(modelClass);
        }
    }
}
