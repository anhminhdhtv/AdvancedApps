package com.example.mincoffee.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mincoffee.R;
import com.example.mincoffee.data.prefs.SharedPrefs;
import com.example.mincoffee.ui.BaseActivity;
import com.example.mincoffee.ui.login.LoginActivity;
import com.example.mincoffee.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    private final String IS_USER_LOGIN = "is_user_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent;
        if (isUserLogin()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        new Handler().postDelayed(() -> startActivity(intent), 1000);
    }

    private boolean isUserLogin() {
//        return SharedPrefs.getInstance().get(IS_USER_LOGIN, Boolean.class);
        return false;
    }

}