package com.example.mincoffee.ui.main.oder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;

import java.util.List;

public class OrderViewModel extends ViewModel {
    private DrinkRepository mDrinkRepository;
    private UserRepository mUserRepository;
    private final LiveData<List<Drink>> mSelectedDrinkList;
    private final MutableLiveData<Integer> mDrinkType = new MutableLiveData<>();
    private final LiveData<User> mUserInfo;

    public OrderViewModel(UserRepository userRepository, DrinkRepository drinkRepository) {
        this.mDrinkRepository = drinkRepository;
        this.mUserRepository = userRepository;
        this.mUserInfo = this.mUserRepository.getUserInfo();
        this.mSelectedDrinkList = Transformations.switchMap(mDrinkType,
                (drinkType) -> this.mDrinkRepository.getDrinkList(drinkType));
    }

    public void loadDrinkList(int drinkType) {
        mDrinkType.setValue(drinkType);
    }

    public LiveData<List<Drink>> getSelectedDrinkList() {
        return mSelectedDrinkList;
    }

    public LiveData<User> getUserInfo() {
        return mUserInfo;
    }
}
