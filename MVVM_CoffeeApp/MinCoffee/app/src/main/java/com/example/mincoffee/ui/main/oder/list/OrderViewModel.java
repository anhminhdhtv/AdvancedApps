package com.example.mincoffee.ui.main.oder.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mincoffee.data.model.Cart;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.data.repository.DrinkRepository;
import com.example.mincoffee.data.repository.UserRepository;
import com.example.mincoffee.ui.widgets.CartFloatButton;

import java.util.List;

public class OrderViewModel extends ViewModel {
    private DrinkRepository mDrinkRepository;
    private UserRepository mUserRepository;
    private final LiveData<List<Drink>> mDrinkList;
    private final MutableLiveData<Integer> mDrinkType = new MutableLiveData<>();
    private final LiveData<User> mUserInfo;
    private final MutableLiveData<Cart> mCart = new MutableLiveData<>();


    public OrderViewModel(UserRepository userRepository, DrinkRepository drinkRepository) {
        this.mDrinkRepository = drinkRepository;
        this.mUserRepository = userRepository;
        this.mUserInfo = this.mUserRepository.getUserInfo();
        this.mDrinkList = Transformations.switchMap(mDrinkType,
                (drinkType) -> this.mDrinkRepository.getDrinkList(drinkType));
        this.mCart.postValue(new Cart());
    }

    public void loadDrinkList(int drinkType) {
        mDrinkType.setValue(drinkType);
    }

    public LiveData<List<Drink>> getDrinkList() {
        return mDrinkList;
    }

    public LiveData<User> getUserInfo() {
        return mUserInfo;
    }

    public LiveData<Cart> getCartInfo() {
        return mCart;
    }
    public Cart getCartData(){
        return mCart.getValue();
    }


    public void addItemToCart(ReservedDrink reservedDrink){
        Cart cart = this.mCart.getValue();
        cart.getReservedDrinkList().add(reservedDrink);
        this.mCart.setValue(cart);
    }
}
