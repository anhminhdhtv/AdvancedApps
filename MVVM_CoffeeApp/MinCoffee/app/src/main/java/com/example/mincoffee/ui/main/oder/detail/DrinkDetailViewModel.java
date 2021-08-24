package com.example.mincoffee.ui.main.oder.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.ReservedDrink;

public class DrinkDetailViewModel extends ViewModel {

    private Drink mSelectedDrink;
    private MutableLiveData<Integer> mPrice = new MutableLiveData<>();
    private MutableLiveData<Integer> mAmount = new MutableLiveData<>();
    private int mSizePrice;

    public DrinkDetailViewModel() {
        mAmount.postValue(1);
    }

    public void setSelectedDrink(Drink selectedDrink) {
        mSelectedDrink = selectedDrink;
        mPrice.postValue(mSelectedDrink.getPrice());
    }

    public LiveData<Integer> getAmountOfDrink() {
        return mAmount;
    }

    public LiveData<Integer> getPrice() {
        return mPrice;
    }

    public void plusDrink() {
        mAmount.setValue(mAmount.getValue() + 1);
        update();
    }

    public void minusDrink() {
        mAmount.setValue(mAmount.getValue() - 1);
        update();
    }

    public ReservedDrink getReservedDrink() {
        return new ReservedDrink(
                mSelectedDrink,
                mAmount.getValue(),
                mSizePrice,
                mPrice.getValue());
    }


    public void setSize(int size) {
        switch (size) {
            case 0:
                mSizePrice = 0;
                break;
            case 1:
                mSizePrice = 10000;
                break;
        }
        update();
    }

    private void update() {
        int price = (mSelectedDrink.getPrice() + mSizePrice) * mAmount.getValue();
        mPrice.setValue(price);
    }
}
