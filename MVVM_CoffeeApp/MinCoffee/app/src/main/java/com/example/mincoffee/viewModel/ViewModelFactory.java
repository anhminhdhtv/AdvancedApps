package com.example.mincoffee.viewModel;

import androidx.annotation.NonNull;
import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ViewModelFactory<T extends ViewModel> extends ViewModelProvider.NewInstanceFactory {

    private final Class<T> viewModelClass;
    private final Supplier<T> viewModelSupplier;

    public ViewModelFactory(Class<T> viewModelClass, Supplier<T> viewModelSupplier) {
        this.viewModelClass = viewModelClass;
        this.viewModelSupplier = viewModelSupplier;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(viewModelClass)) {
            return (T) viewModelSupplier.get();
        } else {
            throw new IllegalArgumentException("Unknown Class name " + viewModelClass.getName());
        }
    }
}
