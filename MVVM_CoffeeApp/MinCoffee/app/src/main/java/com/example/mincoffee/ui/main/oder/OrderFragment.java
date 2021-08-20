package com.example.mincoffee.ui.main.oder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mincoffee.MyApplication;
import com.example.mincoffee.R;
import com.example.mincoffee.data.model.Drink;
import com.example.mincoffee.data.model.User;
import com.example.mincoffee.utils.AppConstant;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import lombok.SneakyThrows;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    private TextView mTvAddress;
    private RecyclerView mRvDrinkList;
    private TabLayout mTlDrinkType;
    private DrinkListAdapter mDrinkListAdapter;
    private OrderViewModel mOrderViewModel;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_oder, container, false);
        initViews(rootView);
        setup();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOrderViewModel = new ViewModelProvider(
                this,
                MyApplication.self().getAppComponent().viewModelProviderFactory)
                .get(OrderViewModel.class);
        subscribeToModel(mOrderViewModel.getSelectedDrinkList(), mOrderViewModel.getUserInfo());
        mOrderViewModel.loadDrinkList(AppConstant.DrinkType.COFFEE);
    }

    private void initViews(View rootItem) {
        mTvAddress = rootItem.findViewById(R.id.tv_address);
        mRvDrinkList = rootItem.findViewById(R.id.rv_list_drink);
        mTlDrinkType = rootItem.findViewById(R.id.tl_drink_type);
    }

    private void setup() {
        mDrinkListAdapter = new DrinkListAdapter(drink -> {

        });
        mRvDrinkList.setAdapter(mDrinkListAdapter);

        mTlDrinkType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mOrderViewModel.loadDrinkList(AppConstant.DrinkType.COFFEE);
                        break;
                    case 1:
                        mOrderViewModel.loadDrinkList(AppConstant.DrinkType.TEA);
                        break;
                    case 2:
                        mOrderViewModel.loadDrinkList(AppConstant.DrinkType.FRUIT);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void subscribeToModel(LiveData<List<Drink>> drinkList, LiveData<User> userLiveData) {
        drinkList.observe(getViewLifecycleOwner(), drinks -> mDrinkListAdapter.setDrinkList(drinks));
        userLiveData.observe(getViewLifecycleOwner(), user -> mTvAddress.setText(user.getAddress()));
    }
}