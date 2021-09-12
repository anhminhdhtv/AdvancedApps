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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.example.mincoffee.MyApplication;
import com.example.mincoffee.R;
import com.example.mincoffee.data.enums.DrinkSize;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.ui.BaseActivity;
import com.example.mincoffee.ui.main.oder.cart.CartViewModel;
import com.example.mincoffee.utils.AppConstant;
import com.example.mincoffee.utils.Utilities;
import com.example.mincoffee.viewModel.ViewModelFactory;
import com.squareup.picasso.Picasso;

public class DrinkDetailActivity extends BaseActivity {
    private ImageView mIvDrink;
    private TextView mTvName, mTvPrice, mTvDescription, mTvAmount;
    private Toolbar mToolbar;
    private ImageButton mBtnPlus, mBtnMinus;
    private RadioButton mRadioBtnSmall, mRadioBtnLarger;
    private AppCompatButton mBtnOrder;
    private DrinkDetailViewModel mDrinkDetailViewModel;
    private ReservedDrink mReservedDrink;
    private boolean mIsNewSelectedDrink = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        mReservedDrink = (ReservedDrink) getIntent().getSerializableExtra("new_selected_drink");

        if (mReservedDrink == null) {
            mReservedDrink = (ReservedDrink) getIntent().getSerializableExtra("old_selected_drink");
            mIsNewSelectedDrink = false;
        }

        initViews();
        display();
        setup();
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
        mToolbar.setNavigationOnClickListener(v -> finish());

        mRadioBtnSmall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mRadioBtnLarger.setChecked(false);
                mDrinkDetailViewModel.setSize(DrinkSize.SMALL);
            }
        });

        mRadioBtnLarger.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mRadioBtnSmall.setChecked(false);
                mDrinkDetailViewModel.setSize(DrinkSize.LARGER);
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
            returnIntent.putExtra("reserved_drink", this.mReservedDrink);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

//        mDrinkDetailViewModel = new ViewModelProvider(this).get(DrinkDetailViewModel.class);
//        mDrinkDetailViewModel.setSelectedDrink(mReservedDrink);
        Supplier<DrinkDetailViewModel> supplier = () -> new DrinkDetailViewModel(mReservedDrink);
        ViewModelFactory<DrinkDetailViewModel> factory = new ViewModelFactory<>(DrinkDetailViewModel.class, supplier);
        mDrinkDetailViewModel = new ViewModelProvider(this, factory).get(DrinkDetailViewModel.class);
        subscribeToModel();
    }

    private void display() {
        Picasso.get().load(mReservedDrink.getDrink().getImageURL()).into(mIvDrink);
        mTvName.setText(mReservedDrink.getDrink().getName());
        mTvPrice.setText(Utilities.castToStringPrice(mReservedDrink.getDrink().getPrice()));
        mTvDescription.setText(mReservedDrink.getDrink().getDescription());
    }

    private void subscribeToModel() {
        mDrinkDetailViewModel.getReservedDrink().observe(this, reservedDrink -> {
            updateViews(reservedDrink);
            this.mReservedDrink = reservedDrink;
        });
    }

    private void updateViews(ReservedDrink reservedDrink) {
        mTvAmount.setText(String.valueOf(reservedDrink.getAmount()));
        String btnText = String.format(
                "ORDER - %s",
                Utilities.castToStringPrice(reservedDrink.getTotalPrice())
        );
        int btnBackground = getColor(R.color.hex_01998d);

        if (mIsNewSelectedDrink) {
            if (reservedDrink.getAmount() == 1) {
                mBtnMinus.setEnabled(false);
            } else {
                mBtnMinus.setEnabled(true);
            }
        } else {
            if (reservedDrink.getAmount() == 0) {
                btnText = "Remove this drink";
                btnBackground = getColor(R.color.hex_fc0000);
            }
        }
        mBtnOrder.setText(btnText);
        mBtnOrder.setBackgroundColor(btnBackground);
    }
}