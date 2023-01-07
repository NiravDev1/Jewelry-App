package com.example.loginsql.customer.Fragments.product;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsql.R;
import com.google.android.material.textview.MaterialTextView;

public class ProductHolder extends RecyclerView.ViewHolder{

        ImageView p_img;
        TextView p_dprice,p_name,p_detail,p_discount,p_quantity;
        MaterialTextView p_oprice;
        CardView productcard;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        p_img=itemView.findViewById(R.id.product_img_id);
        p_name=itemView.findViewById(R.id.product_name_id);
        p_detail=itemView.findViewById(R.id.product_detail_id);
        p_dprice=itemView.findViewById(R.id.product_d_price_id);
        p_oprice=itemView.findViewById(R.id.product_o_price_id);
        p_oprice.setPaintFlags(p_oprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        p_discount=itemView.findViewById(R.id.product_discount_id);
        productcard=itemView.findViewById(R.id.product_card_layout_id);
        p_quantity=itemView.findViewById(R.id.product_quanity_id);
    }
}
