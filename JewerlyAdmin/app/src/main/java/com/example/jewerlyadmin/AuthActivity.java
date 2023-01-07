package com.example.jewerlyadmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AuthActivity extends AppCompatActivity {

    TextInputLayout adminemail, adminpassword;
    MaterialButton login_btn;
    private FirebaseAuth auth;
    DatabaseReference checkadminReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private String VaildEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        adminemail = findViewById(R.id.adminEmail_id);
        adminpassword = findViewById(R.id.adminPassword_id);
        login_btn = findViewById(R.id.admin_login_btn_id);
        auth = FirebaseAuth.getInstance();
        preference = getSharedPreferences("Auth", MODE_PRIVATE);
        boolean check = preference.getBoolean("flag", false);
        if (check) {
                startActivity(new Intent(AuthActivity.this,AdminDashBoardActivity.class));
                finish();
        } else {
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Email = adminemail.getEditText().getText().toString().trim();
                    String Password = adminpassword.getEditText().getText().toString().trim();
                    if (Email.isEmpty() || Password.isEmpty()) {
                        adminemail.setError("Fill the fields");
                        adminpassword.setError("Fill the  fields");
                    } else if (!Email.matches(VaildEmail)) {
                        adminemail.setError("Enter Vaild Email");
                    } else if (Password.length() < 8) {
                        adminpassword.setError("User 8 character  or more for your Password");

                    } else {
                        adminpassword.setError(null);
                        adminemail.setError(null);
                        adminemail.setErrorEnabled(false);
                        adminpassword.setErrorEnabled(false);
                        CheckEmailInFirebase(); //check Email in FireBase authentication
                    }
                }


                private void CheckAdmin() {
                    checkadminReference = database.getReference("Admin").child("AdminAuth");
                    Query checkadmin = checkadminReference.orderByChild("AdminEmail").equalTo(adminemail.getEditText().getText().toString());
                    checkadmin.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(AuthActivity.this, "vaild admin", Toast.LENGTH_SHORT).show();
                                AdminAuthentication(); /// admin authentication in Firebase Authentication
                            } else {
                                Toast.makeText(AuthActivity.this, "not vaild", Toast.LENGTH_SHORT).show();
                            }
                        }

                        private void AdminAuthentication() {
                            auth.signInWithEmailAndPassword(adminemail.getEditText().getText().toString().trim(), adminpassword.getEditText().getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                SharedPreferences.Editor editor = preference.edit();
                                                editor.putBoolean("flag", true);
                                                editor.apply();
                                                Toast.makeText(AuthActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(AuthActivity.this,AdminDashBoardActivity.class));
                                                    finish();
                                            } else {
                                                Toast.makeText(AuthActivity.this, "Email and Password are incorrect ", Toast.LENGTH_SHORT).show();
                                                adminpassword.setError("Password   incorrect");
                                                adminemail.setError("Email incorrect");
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

                private void CheckEmailInFirebase() {
                    auth = FirebaseAuth.getInstance();
                    auth.fetchSignInMethodsForEmail(adminemail.getEditText().getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                    boolean checkemail = task.getResult().getSignInMethods().isEmpty();
                                    if (checkemail) {
                                        adminemail.setError("This Email Accout is not Admin ");
                                    } else {

                                        CheckAdmin();  ///Check Admin in Firebase Realtime Database

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

    }
}