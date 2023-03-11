package com.example.loginsql.SellerDashbord;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AddProductActivity extends AppCompatActivity {

    EditText productname, productdetail, productprice, productdiscout, productquantity;
    Button uploadbtn_btn;
    ImageView imagepicker;
    Uri filepath;
    Bitmap bitmap;
    TextView addproduct_seller_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setTitle("Add Product");


        productdetail = findViewById(R.id.seller_add_product_details_id);
        productname = findViewById(R.id.seller_add_product_name_id);
        productprice = findViewById(R.id.seller_add_product_price_id);
        productdiscout = findViewById(R.id.seller_add_product_discount_id);
        productquantity = findViewById(R.id.seller_add_product_quantity_id);
        uploadbtn_btn = findViewById(R.id.seller_add_upload_btn_id);
        imagepicker = findViewById(R.id.image_picker_id);
        addproduct_seller_name = findViewById(R.id.add_product_seller_name_id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference sn = FirebaseDatabase.getInstance().getReference("User").child("Seller").child(user.getUid()).child("sellerName");

        sn.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);

                addproduct_seller_name.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imagepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withContext(AddProductActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent, 1);


                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();


                            }
                        }).check();


            }
        });

        uploadbtn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadFirebase();
            }
        });

    }

    private void UploadFirebase() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload Details");
        progressDialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("image1" + new Random().nextInt(50));


        uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference root = firebaseDatabase.getReference("Products");

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        long price = Long.parseLong(productprice.getText().toString());
                        long discout = Long.parseLong(productdiscout.getText().toString());
                        long total = 100 - discout;
                        long discoutprice = (total * price) / 100;

                        String pdprice = String.valueOf("\u20B9" + discoutprice);
                        String pprice = productprice.getText().toString();
                        String pname = productname.getText().toString();
                        String pdisocut = productdiscout.getText().toString() + "%";
                        String pdetails = productdetail.getText().toString();
                        String pqunatity = productquantity.getText().toString();
                        String sellername = addproduct_seller_name.getText().toString();


                        //DataTime add
                        String datetime = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDateTime localDateTime = LocalDateTime.now();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            datetime = localDateTime.format(dateTimeFormatter);

                        }


                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SellerProduct");


                        SellerProductAddModel sellerProductAddModel = new SellerProductAddModel(pname, pprice, pdisocut, pdetails, uri.toString(), pqunatity, pdprice, sellername, datetime);

                        reference.child(user.getUid()).push().setValue(sellerProductAddModel);
                        //seller check

                        root.push().setValue(sellerProductAddModel);
                        //customer see
                        Toast.makeText(AddProductActivity.this, "successfully upload", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        productname.setText(null);
                        productprice.setText(null);
                        productdiscout.setText(null);
                        productquantity.setText(null);
                        productdetail.setText(null);
                        imagepicker.setImageResource(R.drawable.ic_baseline_camera_alt_24);


                    }


                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded :" + (int) percent + "%");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream stream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(stream);
                imagepicker.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}