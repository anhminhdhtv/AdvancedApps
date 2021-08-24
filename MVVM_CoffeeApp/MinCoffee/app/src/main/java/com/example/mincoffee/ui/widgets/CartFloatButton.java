package com.example.mincoffee.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.mincoffee.R;
import com.example.mincoffee.utils.Utilities;

public class CartFloatButton extends CardView {
    private TextView mTvItemCount, mTvTotalPrice;

    public CartFloatButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CartFloatButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CartFloatButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.float_button_cart, this, true);
        mTvItemCount = rootView.findViewById(R.id.tv_item_count);
        mTvTotalPrice = rootView.findViewById(R.id.tv_total_price);
        this.setVisibility(GONE);
    }

    public void setData(int itemCount, int totalPrice) {
        if (itemCount <= 0) {
            this.setVisibility(GONE);
        } else {
            this.setVisibility(VISIBLE);
            mTvItemCount.setText(itemCount + " items");
            mTvTotalPrice.setText(Utilities.castToStringPrice(totalPrice) + "đ");
        }
    }

    public void setOnItemClickListener(OnClickListener onItemClickListener){
        this.setOnItemClickListener(onItemClickListener);
    }
}
