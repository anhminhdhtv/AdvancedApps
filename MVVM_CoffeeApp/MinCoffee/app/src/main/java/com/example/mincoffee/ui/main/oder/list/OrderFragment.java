package com.example.mincoffee.ui.main.oder.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Supplier;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mincoffee.MyApplication;
import com.example.mincoffee.R;
import com.example.mincoffee.data.enums.DrinkSize;
import com.example.mincoffee.data.model.ReservedDrink;
import com.example.mincoffee.ui.main.oder.cart.CartActivity;
import com.example.mincoffee.ui.main.oder.detail.DrinkDetailActivity;
import com.example.mincoffee.ui.main.oder.detail.DrinkDetailViewModel;
import com.example.mincoffee.ui.widgets.CartFloatButton;
import com.example.mincoffee.utils.AppConstant;
import com.example.mincoffee.viewModel.ViewModelFactory;
import com.google.android.material.tabs.TabLayout;

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
    private CartFloatButton mBtnOrder;
    private ActivityResultLauncher<Intent> mDrinkDetailLauncher;
    private ActivityResultLauncher<Intent> mCartLauncher;

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
//        mOrderViewModel = new ViewModelProvider(
//                this,
//                MyApplication.self().getAppComponent().viewModelFactory)
//                .get(OrderViewModel.class);

        Supplier<OrderViewModel> supplier = () -> new OrderViewModel(
                MyApplication.self().getAppComponent().userRepository,
                MyApplication.self().getAppComponent().drinkRepository);
        ViewModelFactory<OrderViewModel> factory = new ViewModelFactory<>(OrderViewModel.class, supplier);
        mOrderViewModel = new ViewModelProvider(this, factory).get(OrderViewModel.class);
        subscribeToModel();
        mOrderViewModel.loadDrinkList(AppConstant.DrinkType.COFFEE);
    }

    private void initViews(View rootItem) {
        mTvAddress = rootItem.findViewById(R.id.tv_address);
        mRvDrinkList = rootItem.findViewById(R.id.rv_list_drink);
        mTlDrinkType = rootItem.findViewById(R.id.tl_drink_type);
        mBtnOrder = rootItem.findViewById(R.id.btn_cart);
    }

    private void setup() {
        mDrinkListAdapter = new DrinkListAdapter(drink -> {
            ReservedDrink reservedDrink = new ReservedDrink(drink, 1, DrinkSize.SMALL);
            Intent intent = new Intent(requireActivity(), DrinkDetailActivity.class);
            intent.putExtra("new_selected_drink", reservedDrink);
            mDrinkDetailLauncher.launch(intent);
        });
        mRvDrinkList.setAdapter(mDrinkListAdapter);

        mTlDrinkType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
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

        mBtnOrder.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CartActivity.class);
            intent.putExtra("cart_data", mOrderViewModel.getCartData());
            mCartLauncher.launch(intent);
        });

        setupLaunchers();
    }

    private void subscribeToModel() {
        mOrderViewModel.getDrinkList().observe(
                getViewLifecycleOwner(),
                drinks -> mDrinkListAdapter.setDrinkList(drinks)
        );

        mOrderViewModel.getUserInfo().observe(
                getViewLifecycleOwner(),
                user -> mTvAddress.setText(user.getAddress())
        );

        mOrderViewModel.getCartInfo().observe(
                getViewLifecycleOwner(),
                cart -> mBtnOrder.setData(cart.getReservedDrinkList().size(), cart.getTotalPrice())
        );
    }

    private void setupLaunchers() {
        mDrinkDetailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        ReservedDrink reservedDrink = (ReservedDrink) intent.getSerializableExtra("reserved_drink");
                        mOrderViewModel.addItemToCart(reservedDrink);
                    }
                });

        mCartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                    }
                });
    }

}