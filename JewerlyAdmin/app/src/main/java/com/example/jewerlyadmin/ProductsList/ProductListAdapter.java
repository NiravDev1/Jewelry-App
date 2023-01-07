package com.example.jewerlyadmin.ProductsList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductListAdapter extends FirebaseRecyclerAdapter<ProductModel, ProductListAdapter.productsView> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductListAdapter(@NonNull FirebaseRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull productsView holder, int position, @NonNull ProductModel model) {
        holder.mainview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expadableview.setVisibility(View.VISIBLE);
            }
        });

        holder.expadableview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expadableview.setVisibility(View.GONE);
            }
        });

        holder.productName.setText(model.getProductName());
        holder.sellername.setText(model.getSellerName());
        holder.productdiscout.setText(model.getProductDiscout());
        holder.productprice.setText(model.getProductPrice());
        holder.productdiscoutprice.setText(model.getProductDiscoutPrice());
        holder.productquantity.setText(model.getProductQuantity());
        holder.addtime.setText(model.getProductAddDatetime());
        holder.productdetails.setText(model.getProductDetails());
        Glide.with(holder.productimage).load(model.getProductImage()).into(holder.productimage);

    }

    @NonNull
    @Override
    public productsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_card, parent, false);
        return new productsView(view);

    }

    public static class productsView extends RecyclerView.ViewHolder {

        ImageView productimage;
        TextView productName, sellername, productprice, productdetails, productquantity, productdiscoutprice, productdiscout, addtime;
        RelativeLayout mainview;
        LinearLayout expadableview;


        public productsView(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.product_image_product_card_id);
            productName = itemView.findViewById(R.id.product_name_product_cart_id);
            sellername = itemView.findViewById(R.id.product_Seller_name_product_cart_id);
            productprice = itemView.findViewById(R.id.product_price_product_cart_id);
            productdetails = itemView.findViewById(R.id.product_details_product_cart_id);
            productquantity = itemView.findViewById(R.id.product_quntity_product_cart_id);
            productdiscoutprice = itemView.findViewById(R.id.product_discoutprice_product_card_id);
            productdiscout = itemView.findViewById(R.id.product_discout_product_card_id);
            addtime = itemView.findViewById(R.id.product_datetime_product_cart_id);

            mainview = itemView.findViewById(R.id.main_viewlayout_id);
            expadableview = itemView.findViewById(R.id.expadable_layout_view_id);


        }


    }


}
