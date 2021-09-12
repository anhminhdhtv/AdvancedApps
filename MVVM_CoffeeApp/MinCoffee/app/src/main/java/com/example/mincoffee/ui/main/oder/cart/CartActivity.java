package com.example.mincoffee.ui.main.oder.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mincoffee.MyApplication;
import com.example.mincoffee.R;
import com.example.mincoffee.data.model.Cart;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.ui.widgets.FullHeightListView;
import com.example.mincoffee.ui.widgets.PayButton;
import com.example.mincoffee.utils.Utilities;
import com.example.mincoffee.viewModel.ViewModelFactory;

public class CartActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTvAddress, mTvUserName, mTvTotalPrice, mTvDiscount, mTvDiscountValue, mTvDelete;
    private FullHeightListView mLvDrinks;
    private CartViewModel mCartViewModel;
    private Cart mCart;
    private SelectedDrinkListAdapter mSelectedDrinkListAdapter;
    private PayButton mBtnPayment;
    private ConstraintLayout mDiscountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mCart = (Cart) getIntent().getSerializableExtra("cart_data");
        initViews();
        setup();
        subscribeToModel();
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mTvAddress = findViewById(R.id.tv_address);
        mTvUserName = findViewById(R.id.tv_user_name);
        mTvDiscount = findViewById(R.id.tv_discount);
        mTvDiscountValue = findViewById(R.id.tv_discount_value);
        mTvDelete = findViewById(R.id.tv_delete);
        mTvTotalPrice = findViewById(R.id.tv_total_price);
        mLvDrinks = findViewById(R.id.lv_drinks);
        mBtnPayment = findViewById(R.id.btn_make_payment);
        mDiscountView = findViewById(R.id.view_discount);
    }

    private void setup() {
        mSelectedDrinkListAdapter = new SelectedDrinkListAdapter(this, index -> {

        });
        mLvDrinks.setAdapter(mSelectedDrinkListAdapter);

        mToolbar.setNavigationOnClickListener(v -> finish());

        Supplier<CartViewModel> supplier = () -> new CartViewModel(
                MyApplication.self().getAppComponent().userRepository,
                mCart
        );
        ViewModelFactory<CartViewModel> factory = new ViewModelFactory<>(CartViewModel.class, supplier);
        mCartViewModel = new ViewModelProvider(this, factory).get(CartViewModel.class);
//        mBtnPayment.setOnItemClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mDiscountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VouchersBottomSheet vouchersBottomSheet = new VouchersBottomSheet();
                vouchersBottomSheet.show(getSupportFragmentManager(), VouchersBottomSheet.class.getName());
            }
        });
    }

    private void subscribeToModel() {
        mCartViewModel.getUserInfo().observe(this, this::displayUserInfo);
        mCartViewModel.getCart().observe(this, this::displayCart
        );
    }

    private void displayUserInfo(User user) {
        mTvAddress.setText(user.getAddress());
        mTvUserName.setText(user.getUserName());
    }

    private void displayCart(Cart cart) {
        mSelectedDrinkListAdapter.setReservedDrinkList(cart.getReservedDrinkList());
        mTvTotalPrice.setText(Utilities.castToStringPrice(cart.getTotalPrice()));
        mBtnPayment.setData(cart.getTotalDrinks(), cart.getToPay());
    }

}