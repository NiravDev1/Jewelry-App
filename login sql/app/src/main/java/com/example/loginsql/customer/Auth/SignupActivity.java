package com.example.loginsql.customer.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.example.loginsql.customer.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    MaterialButton sign_up;
    private FirebaseAuth mAuth;
    TextInputLayout sname, semail, smobile, spassword;
    ImageView googlesignupBTN;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_up = findViewById(R.id.sign_btn_id);
        sname = findViewById(R.id.sign_name_id);
        semail = findViewById(R.id.sign_email_id);
        smobile = findViewById(R.id.sign_mobile_id);
        spassword = findViewById(R.id.sign_password_id);

        mAuth = FirebaseAuth.getInstance();



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signupfirebase();
            }


        });



    }





    private void signupfirebase() {

        String Name, Email, Mobile, Password;
        String error = "filed the  fill ";
        Name = sname.getEditText().getText().toString().trim();
        Email = semail.getEditText().getText().toString().trim().toLowerCase();
        Mobile = smobile.getEditText().getText().toString().trim();
        Password = spassword.getEditText().getText().toString().trim();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        if (!Name.isEmpty()) {

            sname.setError(null);
            sname.setErrorEnabled(false);
        } else {
            sname.setError("Please Enter name");

        }
        if (!Email.isEmpty()) {
            semail.setError(null);
            semail.setErrorEnabled(false);

        } else {

            semail.setError("Please Enter Email");
        }
        if (!Mobile.isEmpty()) {
            smobile.setError(null);
            smobile.setErrorEnabled(false);

        } else {

            smobile.setError("Please Enter Mobile");

        }
        if (!Password.isEmpty()) {
            spassword.setError(null);
            spassword.setErrorEnabled(false);

        } else {
            spassword.setError("Please Enter Pasword");
        }

        if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(SignupActivity.this, "m", Toast.LENGTH_SHORT).show();
            try {
                mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            DatabaseReference reference = firebaseDatabase.getReference("User");
                            String customerId = user.getUid();
                            CustomersModel model = new CustomersModel(customerId, Name, Mobile, Email, Password);
                            reference.child("Customer").child(customerId).setValue(model);
                            Toast.makeText(SignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(SignupActivity.this, "sign up fail", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this, "Error" + e, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {

            semail.setError("Entet Vaild Email");
        }

    }


}