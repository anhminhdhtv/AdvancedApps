package com.example.mincoffee.ui.main.oder.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mincoffee.R;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SelectedDrinkListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReservedDrink> mReservedDrinkList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public SelectedDrinkListAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setReservedDrinkList(List<ReservedDrink> reservedDrinkList) {
        if (this.mReservedDrinkList.size() > 0) {
            this.mReservedDrinkList.clear();
        }

        this.mReservedDrinkList.addAll(reservedDrinkList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mReservedDrinkList == null ? 0 : mReservedDrinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return mReservedDrinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = LayoutInflater.from(this.mContext).inflate(R.layout.item_selected_drink, null);
        } else {
            itemView = convertView;
        }
        ReservedDrink reservedDrink = mReservedDrinkList.get(position);
        ((TextView) itemView.findViewById(R.id.tv_item_amount)).setText(reservedDrink.getAmount() + "X");
        ((TextView) itemView.findViewById(R.id.tv_item_name)).setText(reservedDrink.getDrink().getName());
        ((TextView) itemView.findViewById(R.id.tv_item_size)).setText(reservedDrink.getSize().toString());
        ((TextView) itemView.findViewById(R.id.tv_item_total_price)).setText(Utilities.castToStringPrice(reservedDrink.getTotalPrice()));

        itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(position));

        return itemView;
    }

    public interface OnItemClickListener {
        void onItemClick(int index);
    }
}
