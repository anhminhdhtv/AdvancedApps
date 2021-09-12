package com.example.mincoffee.ui.main.oder.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.enums.DrinkSize;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.utils.AppConstant;

public class DrinkDetailViewModel extends ViewModel {

    private MutableLiveData<ReservedDrink> mReservedDrink = new MutableLiveData<>();

    public void setSelectedDrink(ReservedDrink selectedDrink) {
        mReservedDrink.postValue(selectedDrink);
    }

    public DrinkDetailViewModel(ReservedDrink reservedDrink){
        mReservedDrink.postValue(reservedDrink);
    }

    public void plusDrink() {
        ReservedDrink reservedDrink = mReservedDrink.getValue();
        reservedDrink.increaseItemNumber();
        mReservedDrink.setValue(reservedDrink);
    }

    public void minusDrink() {
        ReservedDrink reservedDrink = mReservedDrink.getValue();
        reservedDrink.decreaseItemNumber();
        mReservedDrink.setValue(reservedDrink);
    }

    public LiveData<ReservedDrink> getReservedDrink(){
        return mReservedDrink;
    }

    public void setSize(DrinkSize size) {
        ReservedDrink reservedDrink = mReservedDrink.getValue();
        reservedDrink.setSize(size);
        mReservedDrink.setValue(reservedDrink);
    }
}
