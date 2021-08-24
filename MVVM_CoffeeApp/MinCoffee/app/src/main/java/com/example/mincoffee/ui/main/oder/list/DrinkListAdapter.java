package com.example.mincoffee.ui.main.oder.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mincoffee.R;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.utils.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.ViewHolder> {

    private List<Drink> mDrinkList;
    private OnItemClickListener mOnItemClickListener;

    public DrinkListAdapter(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        setHasStableIds(true);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDrinkList(List<Drink> drinkList) {
        if (mDrinkList == null) {
            mDrinkList = drinkList;
        } else {
            mDrinkList.clear();
            mDrinkList.addAll(drinkList);
        }
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_drink, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drink drink = mDrinkList.get(position);
        holder.display(drink);
        holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(drink));
    }

    @Override
    public int getItemCount() {
        if(mDrinkList == null){
            return 0;
        } else {
            return  mDrinkList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvDescription, mTvPrice;
        private ImageView mIvDrink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View rootView){
            mTvName = rootView.findViewById(R.id.tv_drink_name);
            mTvDescription = rootView.findViewById(R.id.tv_drink_description);
            mTvPrice = rootView.findViewById(R.id.tv_drink_price);
            mIvDrink = rootView.findViewById(R.id.iv_drink_image);
        }

        public void display(Drink drink){
            mTvName.setText(drink.getName());
            mTvDescription.setText(drink.getDescription());
            mTvPrice.setText(Utilities.castToStringPrice(drink.getPrice()));
            Picasso.get().load(drink.getImageURL()).into(mIvDrink);
        }
    }
}
