package com.example.mincoffee.ui.main.oder.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mincoffee.R;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.Voucher;
import com.example.mincoffee.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class VoucherListAdapter extends RecyclerView.Adapter<VoucherListAdapter.ViewHolder> {

    private List<Voucher> mVoucherList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public VoucherListAdapter(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_voucher, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Voucher voucher = mVoucherList.get(position);
        holder.setData(voucher);
        holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return mVoucherList.size();
    }

    public void setVoucherList(List<Voucher> voucherList) {
        if (mVoucherList.size() > 0) {
            mVoucherList.clear();
        }
        mVoucherList.addAll(voucherList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvVoucherName, mTvCondition, mTvExpires, mTvPercent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mTvVoucherName = itemView.findViewById(R.id.tv_voucher_name);
            mTvCondition = itemView.findViewById(R.id.tv_voucher_condition);
            mTvExpires = itemView.findViewById(R.id.tv_voucher_expire);
            mTvPercent = itemView.findViewById(R.id.tv_voucher_percent);
        }

        public void setData(Voucher voucher) {
            mTvVoucherName.setText(voucher.getName());
            mTvCondition.setText(voucher.getCondition());
            mTvExpires.setText(Utilities.formatDate(voucher.getExpiredTime()));
            mTvPercent.setText("- " + voucher.getName() + "%");
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int index);
    }
}
