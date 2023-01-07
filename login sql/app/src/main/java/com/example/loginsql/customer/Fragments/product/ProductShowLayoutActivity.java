package com.example.loginsql.customer.Fragments.product;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginsql.R;
import com.example.loginsql.customer.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProductShowLayoutActivity extends AppCompatActivity {

    TextView PSName, PSDetails, PSPrice, PSDiscout, PSDisPrice, PSQutity,pseller;
    ImageView PsImage;
    Button addtocartBTN_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show_layout);
        PSDetails = findViewById(R.id.product_show_details_id);
        PSName = findViewById(R.id.product_show_name_id);
        PSDiscout = findViewById(R.id.product_show_discout_id);
        PSPrice = findViewById(R.id.product_show_price_id);
        PSDisPrice = findViewById(R.id.product_show_discout_price_id);
        PSQutity = findViewById(R.id.product_show_qutitity_id);
        PsImage = findViewById(R.id.product_show_image_id);
        addtocartBTN_id = findViewById(R.id.add_to_cart_btn_id);
        pseller=findViewById(R.id.seller_show_product_sellername_id);

        String ProductImage = getIntent().getStringExtra("PImage").toString();

        PSPrice.setPaintFlags(PSPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        PSName.setText(getIntent().getStringExtra("PName"));
        PSDetails.setText(getIntent().getStringExtra("PDetails"));
        PSDiscout.setText(getIntent().getStringExtra("PDiscout"));
        PSPrice.setText(getIntent().getStringExtra("PPrice"));
        PSDisPrice.setText(getIntent().getStringExtra("PDprice"));
        PSQutity.setText(getIntent().getStringExtra("PQuantity"));
        pseller.setText(getIntent().getStringExtra("pseller"));

        Glide.with(PsImage).load(ProductImage).into(PsImage);

        addtocartBTN_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pimage = getIntent().getStringExtra("PImage");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cart");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String CustomerId=user.getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("CustomerId",CustomerId);
                map.put("ProductName", PSName.getText().toString());
                map.put("ProductImage", pimage);
                map.put("productDiscoutPrice", PSDisPrice.getText().toString());

                DatabaseReference pro = databaseReference.child(CustomerId);


                Query chck = pro.orderByChild("ProductName").equalTo(PSName.getText().toString());

                chck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            Toast.makeText(ProductShowLayoutActivity.this, "this product already in cart ", Toast.LENGTH_SHORT).show();
                            addtocartBTN_id.setText("gotocart");
                        } else {

                            databaseReference.child(CustomerId).push().setValue(map);
                            Toast.makeText(ProductShowLayoutActivity.this, "Successfull add Product", Toast.LENGTH_SHORT).show();
                            addtocartBTN_id.setText("gotocart");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProductShowLayoutActivity.this, "Error::" + error, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProductShowLayoutActivity.this, HomeActivity.class));
        finish();
    }
}