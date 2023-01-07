package com.example.loginsql.SellerDashbord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SellerloginActivity extends AppCompatActivity {

    TextInputLayout email, password;
    MaterialButton sellerbtn;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerlogin);

        email = findViewById(R.id.seller_email_id);
        password = findViewById(R.id.seller_password_id);
        sellerbtn = findViewById(R.id.seller_login_btn_id);

        auth = FirebaseAuth.getInstance();

        sellerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getEditText().getText().toString().isEmpty() || password.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(SellerloginActivity.this, "fill the fileds", Toast.LENGTH_SHORT).show();
                    email.setError("Fill the filds");
                    password.setError("fill the filds");
                } else {
                    email.setError(null);
                    password.setError(null);
                    email.setErrorEnabled(false);
                    password.setErrorEnabled(false);
                    SellerLogin();
                }


            }
        });

    }

    private void SellerLogin() {
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("User").child("Seller");

        Query check_seller = reference.orderByChild("sellerEmail").equalTo(email.getEditText().getText().toString());

        check_seller.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    auth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SellerloginActivity.this, "login successfull ", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SellerloginActivity.this, SelllerDashboardActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(SellerloginActivity.this, "Email and Pasword has been Wrong ", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                } else {
                    Toast.makeText(SellerloginActivity.this, "Seller does  not exists ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}