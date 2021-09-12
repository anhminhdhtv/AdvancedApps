package com.example.mincoffee.ui.main.oder.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.model.Cart;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.data.repository.UserRepository;

public class CartViewModel extends ViewModel {
    private UserRepository mUserRepository;
    private final LiveData<User> mUserInfo;
    private MutableLiveData<Cart> mCart = new MutableLiveData<>();
    private MutableLiveData<ReservedDrink> mReservedDrink = new MutableLiveData<>();


    public CartViewModel(UserRepository userRepository, Cart cartData) {
        this.mUserRepository = userRepository;
        this.mUserInfo = this.mUserRepository.getUserInfo();
        this.mCart.postValue(cartData);
    }

    public LiveData<User> getUserInfo() {
        return mUserInfo;
    }

    public LiveData<Cart> getCart(){
        return this.mCart;
    }

    public void setCart(Cart cart){
        this.mCart.postValue(cart);
    }

    public void setReservedDrink(ReservedDrink reservedDrink) {
        this.mReservedDrink.postValue(reservedDrink);
    }

    public LiveData<ReservedDrink> getReservedDrink() {
        return mReservedDrink;
    }
}
