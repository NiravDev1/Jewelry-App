package com.example.loginsql.SellerDashbord;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsql.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class SellerproductAdpter extends FirebaseRecyclerAdapter<SellerProductModel, SellerproductAdpter.sellerproductviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SellerproductAdpter(@NonNull FirebaseRecyclerOptions<SellerProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull sellerproductviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull SellerProductModel model) {

        holder.spname.setText(model.getProductName());
        holder.spdetail.setText(model.getProductDetails());
        holder.spprice.setText(model.getProductPrice());
        holder.spdisprice.setText(model.getProductDiscoutPrice());
        holder.spquantity.setText(model.getProductQuantity());
        Glide.with(holder.spimage).load(model.getProductImage()).into(holder.spimage);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Delete Product
        holder.spdeletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete Product! ");
                builder.setMessage("Are you sure to Delete Product ");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("SellerProduct").child(user.getUid()).child(getRef(position).getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Product").child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });

        //Product Update
        holder.spupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 24-12-2022
                Toast.makeText(v.getContext(), "update panel", Toast.LENGTH_SHORT).show();
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.spimage.getContext())
                        .setContentHolder(new ViewHolder(R.layout.editeproductdialog))
                        .setExpanded(true, 1380)
                        .create();
                /* private String ProductName, ProductPrice, ProductDiscout, ProductDetails, ProductImage, ProductQuantity,ProductDiscoutPrice;*/

                View dilogview = dialogPlus.getHolderView();
                TextInputLayout ProductImage = dilogview.findViewById(R.id.update_product_image_url_id);
                TextInputLayout ProductName = dilogview.findViewById(R.id.update_product_name_sd_id);
                TextInputLayout ProductPrice = dilogview.findViewById(R.id.update_product_price_sd_id);
                TextInputLayout ProductDiscoutPrice = dilogview.findViewById(R.id.update_product_discoutnprice_sd_id);
                TextInputLayout ProductDetails = dilogview.findViewById(R.id.update_product_details_sd_id);
                TextInputLayout ProductQuantity = dilogview.findViewById(R.id.update_product_quantity_sd_id);
                Button update_btn = dilogview.findViewById(R.id.update_product_update_sd_btn_id);

                ProductImage.getEditText().setText(model.getProductImage());
                ProductName.getEditText().setText(model.getProductName());
                ProductDiscoutPrice.getEditText().setText(model.getProductDiscoutPrice());
                ProductDetails.getEditText().setText(model.getProductDetails());
                ProductQuantity.getEditText().setText(model.getProductQuantity());
                ProductPrice.getEditText().setText(model.getProductPrice());

                dialogPlus.show();
                update_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // TODO: 25-12-2022
                        Map<String, Object> map = new HashMap<>();
                        map.put("productImage", ProductImage.getEditText().getText().toString());
                        map.put("productName", ProductName.getEditText().getText().toString());
                        map.put("productDiscoutPrice", ProductDiscoutPrice.getEditText().getText().toString());
                        map.put("productDetails", ProductDetails.getEditText().getText().toString());
                        map.put("productPrice", ProductPrice.getEditText().getText().toString());
                        map.put("productQuantity", ProductQuantity.getEditText().getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("SellerProduct").child(user.getUid()).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        dialogPlus.dismiss();
                                        Toast.makeText(v.getContext(), "Successful update Sellerproduct", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        e.printStackTrace();
                                        Toast.makeText(v.getContext(), "fail to update", Toast.LENGTH_SHORT).show();


                                    }
                                });

                        FirebaseDatabase.getInstance().getReference("Products").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        dialogPlus.dismiss();
                                        Toast.makeText(v.getContext(), "Successful update product", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(v.getContext(), "fail to update", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                });


                    }
                });


            }
        });


    }

    @NonNull
    @Override
    public sellerproductviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_product_singlecard, parent, false);

        return new sellerproductviewholder(view);
    }


    public class sellerproductviewholder extends RecyclerView.ViewHolder {
        ImageView spimage;
        TextView spname, spprice, spdisprice, spdetail, spquantity;
        Button spupdatebtn, spdeletebtn;

        public sellerproductviewholder(@NonNull View itemView) {
            super(itemView);

            spimage = itemView.findViewById(R.id.product_image_c_S_id);
            spname = itemView.findViewById(R.id.product_name_c_S_id);
            spprice = itemView.findViewById(R.id.product_oprice_c_S_id);
            spdisprice = itemView.findViewById(R.id.product_dprice_c_S_id);
            spdetail = itemView.findViewById(R.id.product_details_c_S_id);
            spupdatebtn = itemView.findViewById(R.id.product_update_c_s_id);
            spdeletebtn = itemView.findViewById(R.id.product_delete_c_s_id);
            spquantity = itemView.findViewById(R.id.product_quantity_c_s_id);

        }
    }


}
