package com.example.loginsql.customer.Fragments.product;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsql.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<ProductModel, ProductHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public ProductAdapter(@NonNull FirebaseRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductHolder holder, int position, @NonNull ProductModel model) {
        Glide.with(holder.p_img.getContext()).load(model.getProductImage()).into(holder.p_img);
        holder.p_name.setText(model.getProductName());
        holder.p_detail.setText(model.getProductDetails());
        holder.p_oprice.setText(model.getProductPrice());
        holder.p_dprice.setText(model.getProductDiscoutPrice());
        holder.p_discount.setText(model.getProductDiscout());
        holder.p_quantity.setText(model.getProductQuantity());
        holder.productcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(v.getContext(), ProductShowLayoutActivity.class);
                i.putExtra("PName", model.getProductName());
                i.putExtra("PDetails", model.getProductDetails());
                i.putExtra("PPrice", model.getProductPrice());
                i.putExtra("PDiscout", model.getProductDiscout());
                i.putExtra("PQuantity", model.getProductQuantity());
                i.putExtra("PDprice",model.getProductDiscoutPrice());
                i.putExtra("PImage",model.getProductImage());
                i.putExtra("pseller",model.getSellerName());
                v.getContext().startActivity(i);


            }
        });


    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductHolder(view);


    }
}
