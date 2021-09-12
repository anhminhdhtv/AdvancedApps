package com.example.mincoffee.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mincoffee.R;
import com.example.mincoffee.utils.Utilities;

public class RadioButtonSizeItem extends ConstraintLayout {
    private RadioButton mRadioButton;
    private TextView mTvValue;
    public RadioButtonSizeItem(@NonNull Context context) {
        super(context);
    }

    public RadioButtonSizeItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioButtonSizeItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RadioButtonSizeItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.radio_button_size, this, true);
        mRadioButton = rootView.findViewById(R.id.radioBtn);
        mTvValue = rootView.findViewById(R.id.tv_radio_btn_value);
    }

    public void setData(int itemCount, int totalPrice) {
        mRadioButton.setText(itemCount + " items");
    }

    public void setValue(int value){
        mTvValue.setText(Utilities.castToStringPrice(value) + "đ");
    }

    public void setOnCheckedChangeListener(OnClickListener onItemClickListener) {
    }
}
