package com.example.loginsql.SellerDashbord;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsql.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ListofproductSellerActivity extends AppCompatActivity {

    RecyclerView listofproductrecyclerview;
    SellerproductAdpter sellerproductAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofproduct_seller);
        setTitle("List of Products");
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listofproductrecyclerview = findViewById(R.id.sellerproduct_recyclerview_id);
        listofproductrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SellerProductModel> options =
                new FirebaseRecyclerOptions.Builder<SellerProductModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SellerProduct").child(user.getUid()), SellerProductModel.class)
                        .build();


        sellerproductAdpter = new SellerproductAdpter(options);
        listofproductrecyclerview.setAdapter(sellerproductAdpter);


    }

    @Override
    public void onStart() {
        super.onStart();
        sellerproductAdpter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        sellerproductAdpter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.serach_id);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                ProcessSearch(s);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                ProcessSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void ProcessSearch(String s) {

        FirebaseRecyclerOptions<SellerProductModel> options =
                new FirebaseRecyclerOptions.Builder<SellerProductModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").orderByChild("psname").startAt(s).endAt(s + "\uf8ff"), SellerProductModel.class)
                        .build();

        sellerproductAdpter = new SellerproductAdpter(options);
        sellerproductAdpter.startListening();
        listofproductrecyclerview.setAdapter(sellerproductAdpter);

    }
}