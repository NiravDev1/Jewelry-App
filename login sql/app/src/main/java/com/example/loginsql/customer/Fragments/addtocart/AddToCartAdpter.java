package com.example.loginsql.customer.Fragments.addtocart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsql.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddToCartAdpter extends FirebaseRecyclerAdapter<AddToCartModel_Calss, AddToCartAdpter.addtocartviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AddToCartAdpter(@NonNull FirebaseRecyclerOptions<AddToCartModel_Calss> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull addtocartviewholder holder, int position, @NonNull AddToCartModel_Calss model) {

        holder.productprice.setText(model.getProductDiscoutPrice());
        Glide.with(holder.productimage).load(model.getProductImage()).into(holder.productimage);
        holder.productname.setText(model.getProductName());


    }

    @NonNull
    @Override
    public addtocartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_tocart_single_cart,parent,false);

        return new addtocartviewholder(view);
    }

    public class addtocartviewholder extends RecyclerView.ViewHolder {

        ImageView productimage;
        TextView productname,productprice;
        public addtocartviewholder(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.add_to_cart_image_c_id);
            productname=itemView.findViewById(R.id.add_to_cart_name_C_id);
            productprice=itemView.findViewById(R.id.add_to_cart_price_C_id);

        }
    }
}
