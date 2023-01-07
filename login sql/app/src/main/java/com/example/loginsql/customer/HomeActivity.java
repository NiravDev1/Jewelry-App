package com.example.loginsql.customer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.loginsql.R;
import com.example.loginsql.customer.Fragments.CartFragment;
import com.example.loginsql.customer.Fragments.HomeFragment;
import com.example.loginsql.customer.Fragments.LiveFragment;
import com.example.loginsql.customer.Fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences=getSharedPreferences("Auth",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("flag",true);
        editor.apply();
        frameLayout = findViewById(R.id.fragment_view_id);
        bottomNavigationView = findViewById(R.id.botton_navigaation_id);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view_id, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home_id:
                        fragment = new HomeFragment();
                        break;
                    case R.id.live_id:
                        fragment = new LiveFragment();
                        break;
                    case R.id.cart_id:
                        fragment = new CartFragment();
                        break;
                    case R.id.profile_id:
                        fragment = new SettingFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view_id, fragment).commit();
                return true;
            }
        });


    }
}