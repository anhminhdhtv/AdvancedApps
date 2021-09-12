package com.example.mincoffee.data.enums;
import androidx.annotation.NonNull;

public enum DrinkSize {
    SMALL, LARGER;

    @NonNull
    @Override
    public String toString() {
        if (this == SMALL) {
            return "Small";
        } else if (this == LARGER) {
            return "Larger";
        }
        return super.toString();
    }
}