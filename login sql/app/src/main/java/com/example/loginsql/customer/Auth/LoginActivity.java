package com.example.loginsql.customer.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.example.loginsql.SellerDashbord.SellerloginActivity;
import com.example.loginsql.customer.Auth.BecomeSeller.BecomeSellerActivity;
import com.example.loginsql.customer.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class LoginActivity extends AppCompatActivity {

    MaterialButton login,becomesellerbtn;
    TextInputLayout lemail, lpassword;

    private FirebaseAuth auth;
    String Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_btn_id);
        lemail = findViewById(R.id.login_email_id);
        auth = FirebaseAuth.getInstance();
        lpassword = findViewById(R.id.login_password_id);
        becomesellerbtn=findViewById(R.id.login_become_seller_btn_id);
        becomesellerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, BecomeSellerActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = lemail.getEditText().getText().toString().toLowerCase().trim();
                Password = lpassword.getEditText().getText().toString().trim();

                if (Email.isEmpty() || Password.isEmpty()) {
                    lemail.setError("Fill the fileds");
                    lpassword.setError("Fill the fileds");

                } else {

                    lemail.setError(null);
                    lpassword.setError(null);
                    lpassword.setErrorEnabled(false);
                    lemail.setErrorEnabled(false);
                    CheackCustomer();
                }


            }

            private void CheackCustomer() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference = FirebaseDatabase.getInstance().getReference("User").child("Customer");
                System.out.println(reference);
                Query query = reference.orderByChild("customerEmail").equalTo(Email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            CustomerAuth();
                            Toast.makeText(LoginActivity.this, "customer exists", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "customer not exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            private void CustomerAuth() {
                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("auth", "auth signin successfull");
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "Email and Password Wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });

            }


        });


    }


    public void newregiser(View view) {

        startActivity(new Intent(LoginActivity.this, SignupActivity.class));

    }



    public void seller(View view) {
        startActivity(new Intent(LoginActivity.this, SellerloginActivity.class));
    }
}