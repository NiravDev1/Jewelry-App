package com.example.loginsql.SellerDashbord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginsql.R;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelllerDashboardActivity extends AppCompatActivity {

    CardView addproductcard,listproductcard;
    MaterialTextView SellerName;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selller_dashboard);
        setTitle("Seller Dashboard");
        user= FirebaseAuth.getInstance().getCurrentUser();
        addproductcard=findViewById(R.id.add_product_id);
        listproductcard=findViewById(R.id.list_product_id);
        SellerName=findViewById(R.id.seller_dashboard_SellerNAme_id);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User").child("Seller").child(user.getUid()).child("sellerName");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               String v=snapshot.getValue(String.class);
                SellerName.setText(v);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });




        addproductcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelllerDashboardActivity.this,AddProductActivity.class));
            }
        });

        listproductcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelllerDashboardActivity.this,ListofproductSellerActivity.class));
            }
        });


    }
}