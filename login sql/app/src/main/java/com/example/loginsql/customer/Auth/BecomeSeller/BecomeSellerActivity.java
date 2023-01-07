package com.example.loginsql.customer.Auth.BecomeSeller;

import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class BecomeSellerActivity extends AppCompatActivity {
    TextInputLayout selleremail, sellename, sellermobile, sellerbusinesname, sellleraddress;
    RadioGroup sellergender;
    MaterialRadioButton male, female;
    MaterialButton seller_request_btn;

    String SellerGender = null;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_seller);

        sellename = findViewById(R.id.seller_name_b_id);
        selleremail = findViewById(R.id.seller_email_b_id);
        sellermobile = findViewById(R.id.seller_mobile_b_id);
        sellerbusinesname = findViewById(R.id.seller_business_name_b_id);
        sellleraddress = findViewById(R.id.seller_address_name_b_id);
        sellergender = findViewById(R.id.seller_gender_b_id);
        male = findViewById(R.id.seller_gender_male_b_id);
        female = findViewById(R.id.seller_gender_female_b_id);

        sellergender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //check gender
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.seller_gender_male_b_id:
                        Toast.makeText(BecomeSellerActivity.this, male.getText().toString(), Toast.LENGTH_SHORT).show();
                        SellerGender = male.getText().toString();
                        break;
                    case R.id.seller_gender_female_b_id:
                        Toast.makeText(BecomeSellerActivity.this, "female", Toast.LENGTH_SHORT).show();
                        SellerGender = female.getText().toString();
                        break;
                }
            }
        });


        seller_request_btn = findViewById(R.id.seller_request_btn_b_id);
        seller_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SellerName = sellename.getEditText().getText().toString();
                String SellerEmail = selleremail.getEditText().getText().toString();
                String SellerMobile = sellermobile.getEditText().getText().toString();
                String SellerBusinessname = sellerbusinesname.getEditText().getText().toString();
                String SellerAddress = sellleraddress.getEditText().getText().toString();


                if (SellerName.isEmpty() || SellerEmail.isEmpty() || SellerMobile.isEmpty() || SellerBusinessname.isEmpty() || SellerAddress.isEmpty()) {
                    Toast.makeText(BecomeSellerActivity.this, "fill the fields", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(SellerEmail).matches()) {
                    Toast.makeText(BecomeSellerActivity.this, "check email", Toast.LENGTH_SHORT).show();
                    selleremail.setError("Enter vaild Email");
                } else if (sellergender.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(BecomeSellerActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                } else {



                        Query query=databaseReference.child("Admin").child("SellerRequest").orderByChild("SellerEmail").equalTo(SellerEmail);
                               query.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                                       if (snapshot.exists())
                                       {
                                           selleremail.setError("This Email is already Taken ,Try Another Email");
                                       }
                                       else
                                       {
                                           SendRequest();
                                       }
                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError error) {
                                       Toast.makeText(BecomeSellerActivity.this, "Error::"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               });



                }

            }

            private void SendRequest() {

                selleremail.setError(null);
                selleremail.setErrorEnabled(false);
                String datetime = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    datetime = localDateTime.format(dateTimeFormatter);

                }

                String sellerRequId = databaseReference.child("Admin").child("SEllerRequest").push().getKey();
                HashMap<String, Object> map = new HashMap<>();
                map.put("SellerName", sellename.getEditText().getText().toString());
                map.put("SellerEmail", selleremail.getEditText().getText().toString());
                map.put("SellerMobileNo", sellermobile.getEditText().getText().toString());
                map.put("SellerGender", SellerGender);
                map.put("SellerBusinessName", sellerbusinesname.getEditText().getText().toString());
                map.put("SellerAddress", sellleraddress.getEditText().getText().toString());
                map.put("SellerAddDate", datetime);
                map.put("SellerRequestId", sellerRequId);


                databaseReference.child("Admin").child("SellerRequest").child(sellerRequId).setValue(map);

                AlertDialog.Builder builder = new AlertDialog.Builder(BecomeSellerActivity.this);
                builder.setTitle("Successfully");
                builder.setIcon(R.drawable.success);
                builder.setMessage("\nYour Request ID " + sellerRequId + "\n\n\nYour request has been received and \nwe will verify your data and send the password link to your email within 24 hours");
                builder.setCancelable(true);
                builder.show();

                selleremail.getEditText().setText(null);
                sellename.getEditText().setText(null);
                sellermobile.getEditText().setText(null);
                sellerbusinesname.getEditText().setText(null);
                sellleraddress.getEditText().setText(null);


            }
        });


    }
}