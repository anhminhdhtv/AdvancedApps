package com.example.mincoffee.ui.main.oder.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.mincoffee.R;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.ui.BaseActivity;
import com.example.mincoffee.ui.main.oder.list.OnItemClickListener;
import com.example.mincoffee.utils.AppConstant;
import com.example.mincoffee.utils.Utilities;
import com.squareup.picasso.Picasso;

public class DrinkDetailActivity extends BaseActivity {
    private ImageView mIvDrink;
    private TextView mTvName, mTvPrice, mTvDescription, mTvAmount;
    private Drink mDrink;
    private Toolbar mToolbar;
    private ImageButton mBtnPlus, mBtnMinus;
    private RadioButton mRadioBtnSmall, mRadioBtnLarger;
    private Button mBtnOrder;
    private DrinkDetailViewModel mDrinkDetailViewModel;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrink = (Drink) getIntent().getSerializableExtra("bundle");

        setContentView(R.layout.activity_drink_detail);
        initViews();
        display();
        setup();

        mDrinkDetailViewModel = new ViewModelProvider(this).get(DrinkDetailViewModel.class);
        mDrinkDetailViewModel.setSelectedDrink(mDrink);
        subscribeToModel();
    }

    private void initViews() {
        mIvDrink = findViewById(R.id.iv_drink);
        mTvName = findViewById(R.id.tv_drink_name);
        mTvPrice = findViewById(R.id.tv_drink_price);
        mTvDescription = findViewById(R.id.tv_drink_description);
        mTvAmount = findViewById(R.id.tv_amount);
        mToolbar = findViewById(R.id.toolbar);
        mBtnPlus = findViewById(R.id.btn_plus);
        mBtnMinus = findViewById(R.id.btn_minus);
        mRadioBtnSmall = findViewById(R.id.radioBtn_small);
        mRadioBtnLarger = findViewById(R.id.radioBtn_larger);
        mBtnOrder = findViewById(R.id.btn_order);
    }

    private void setup() {
        mRadioBtnSmall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mRadioBtnLarger.setSelected(false);
                mDrinkDetailViewModel.setSize(AppConstant.DrinkSize.SMALL);
            }
        });

        mRadioBtnLarger.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mRadioBtnSmall.setSelected(false);
                mDrinkDetailViewModel.setSize(AppConstant.DrinkSize.LARGER);
            }
        });

        mBtnPlus.setOnClickListener(v -> {
            mDrinkDetailViewModel.plusDrink();
        });

        mBtnMinus.setOnClickListener(v -> {
            mDrinkDetailViewModel.minusDrink();
        });

        mBtnOrder.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("reserved_drink", mDrinkDetailViewModel.getReservedDrink());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }

    private void display() {
        Picasso.get().load(mDrink.getImageURL()).into(mIvDrink);
        mTvName.setText(mDrink.getName());
        mTvPrice.setText(Utilities.castToStringPrice(mDrink.getPrice()));
        mTvDescription.setText(mDrink.getDescription());
    }

    private void subscribeToModel() {
        mDrinkDetailViewModel.getAmountOfDrink().observe(this, amount -> {
            mTvAmount.setText(String.valueOf(amount));
        });

        mDrinkDetailViewModel.getPrice().observe(this, price -> {
            mBtnOrder.setText(String.format("ORDER - %s", Utilities.castToStringPrice(price)));
        });
    }

}