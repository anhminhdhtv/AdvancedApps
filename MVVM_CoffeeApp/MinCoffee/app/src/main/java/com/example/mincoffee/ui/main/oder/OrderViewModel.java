package com.example.mincoffee.ui.main.oder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;
import com.example.mincoffee.utils.AppConstant;

import java.util.List;
import java.util.stream.Collectors;

public class OrderViewModel extends ViewModel {
    private DrinkRepository mDrinkRepository;
    private UserRepository mUserRepository;
    private final LiveData<List<Drink>> mAllDrinks;
    private final MutableLiveData<List<Drink>> mSelectedDrinkList = new MutableLiveData<>();
    private final LiveData<User> mUserInfo;

    public OrderViewModel(UserRepository userRepository, DrinkRepository drinkRepository) {
        this.mDrinkRepository = drinkRepository;
        this.mUserRepository = userRepository;
        this.mAllDrinks = this.mDrinkRepository.getDrinkList();
        this.mUserInfo = this.mUserRepository.getUserInfo();
    }

    public void loadDrinkList(int drinkType) {
        List<Drink> selectedDrinks = mAllDrinks.getValue().stream()
                .filter(drink -> drink.getType() == drinkType)
                .collect(Collectors.toList());

        mSelectedDrinkList.postValue(selectedDrinks);
    }

    public LiveData<List<Drink>> getSelectedDrinkList() {
        return mSelectedDrinkList;
    }

    public LiveData<User> getUserInfo() {
        return mUserInfo;
    }
}
