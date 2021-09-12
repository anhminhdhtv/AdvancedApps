package com.example.mincoffee.ui.main.oder.cart;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mincoffee.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class VouchersBottomSheet extends BottomSheetDialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Setup dialog
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.bottom_sheet_vouchers, null);
        LinearLayout linearLayout = view.findViewById(R.id.bottom_sheet_content);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);
        dialog.setContentView(view);
        BottomSheetBehavior.from((View) view.getParent()).setState(BottomSheetBehavior.STATE_EXPANDED);
        BottomSheetBehavior.from((View) view.getParent()).setSkipCollapsed(true);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }


    /**
     * Get screen height
     *
     * @return
     */
    private int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * Get status bar height
     *
     * @return
     */
//    private int getStatusBarHeight() {
//        Rect displayRect = new Rect();
//        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRect);
//        return displayRect.top;
//    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}