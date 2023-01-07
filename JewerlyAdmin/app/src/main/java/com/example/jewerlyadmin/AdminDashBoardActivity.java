package com.example.jewerlyadmin;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.jewerlyadmin.Customer.CustomerListFragmentC;
import com.example.jewerlyadmin.ProductsList.ProductsListFragmentP;
import com.example.jewerlyadmin.SellerReq.SellerRequestFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminDashBoardActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView adminname;
    SharedPreferences preferences;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
        preferences = getSharedPreferences("Auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("flag", true);
        editor.apply();
        auth = FirebaseAuth.getInstance();
        frameLayout = findViewById(R.id.fragment_layout_id);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.Drawerlayout_id);
        navigationView = findViewById(R.id.navigation_view_id);
        View header = navigationView.getHeaderView(0);
        adminname = header.findViewById(R.id.admin_name_id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        try {
            Query query = reference.child("Admin").child("AdminAuth").child(user.getUid()).child("AdminName");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String name = snapshot.getValue(String.class);
                    System.out.println(name);
                    adminname.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            adminname.setText("Admin Name");
        }

        drawerLayout.setDrawerListener(toggle);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);

        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_id, new ProductsListFragmentP()).commit();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.product_list_product_id:
                        toolbar.setTitle("Products");
                        fragment = new ProductsListFragmentP();
                        break;
                    case R.id.customer_list_customer_m_id:
                        toolbar.setTitle("Customers");
                        fragment = new CustomerListFragmentC();
                        break;
                    case R.id.customer_buy_product_m_id:
                        toolbar.setTitle("Buy Products");
                        fragment = new ProductBuyFragmentC();
                        break;
                    case R.id.seller_list_selllers_m_id:
                        toolbar.setTitle("Sellers");
                        fragment = new SellerListFragmentS();
                        break;
                    case R.id.seller_new_seller_m_id:
                        toolbar.setTitle("Add Seller");
                        fragment = new NewSellerFragmentS();
                        break;
                    case R.id.seller_notification_m_id:
                        toolbar.setTitle("Request Sellers");
                        fragment = new SellerRequestFragment();
                        break;

                    case R.id.Log_out_m_id:
                        Toast.makeText(AdminDashBoardActivity.this, "log out", Toast.LENGTH_SHORT).show();
//                        SharedPreferences.Editor editor1 = preferences.edit();
//                        editor1.putBoolean("flag", false);
//                        editor1.apply();
//                        auth.signOut();
//                        startActivity(new Intent(AdminDashBoardActivity.this,AuthActivity.class));
//                        finish();
//                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_id, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });


    }


}