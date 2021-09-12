package com.example.mincoffee.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mincoffee.R;
import com.example.mincoffee.utils.Utilities;

public class PayButton extends ConstraintLayout {
    private TextView mTvItemCount, mTvTotalPrice;

    public PayButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PayButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PayButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PayButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.button_pay, this, true);
        mTvItemCount = rootView.findViewById(R.id.tv_item_count);
        mTvTotalPrice = rootView.findViewById(R.id.tv_total_price);
    }

    public void setData(int itemCount, int totalPrice) {
        mTvItemCount.setText(itemCount + " items");
        mTvTotalPrice.setText(Utilities.castToStringPrice(totalPrice) + "đ");
    }

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        this.setOnItemClickListener(onItemClickListener);
    }
}
