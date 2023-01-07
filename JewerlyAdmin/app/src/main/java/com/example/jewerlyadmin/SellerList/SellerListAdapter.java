package com.example.jewerlyadmin.SellerList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SellerListAdapter extends FirebaseRecyclerAdapter<SellerListModel,SellerListAdapter.SellerListViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SellerListAdapter(@NonNull FirebaseRecyclerOptions<SellerListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SellerListViewHolder holder, int position, @NonNull SellerListModel model) {

        holder.selleremail.setText(model.getSellerEmail());
        holder.sellergender.setText(model.getSellerGender());
        holder.sellerbname.setText(model.getSellerBusinessName());
        holder.sellername.setText(model.getSellerName());
        holder.selleraddress.setText(model.getSellerAddress());
        holder.sellermobile.setText(model.getSellerMobileNo());
        holder.sellerid.setText(model.getSellerId());
        holder.selleradddate.setText(model.getSellerAddDate());



        holder.down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sellerexpadable_layout.setVisibility(View.VISIBLE);
                holder.down_arrow.setVisibility(View.GONE);

            }
        });
        holder.up_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.sellerexpadable_layout.setVisibility(View.GONE);
               holder.down_arrow.setVisibility(View.VISIBLE);
            }
        });


    }

    @NonNull
    @Override
    public SellerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_seller_list,parent,false);
        return new SellerListViewHolder(view);
    }

    public class SellerListViewHolder extends RecyclerView.ViewHolder //viewholder
    {
            TextView selleradddate,selleraddress,sellerbname,sellergender,selleremail,sellermobile,sellername,sellerid;
            ImageView up_arrow,down_arrow;
            LinearLayout sellerview_layout,sellerexpadable_layout;
        public SellerListViewHolder(@NonNull View itemView) {
            super(itemView);
            sellername=itemView.findViewById(R.id.seller_list_sellerName_c_id);
            selleradddate=itemView.findViewById(R.id.seller_list_adddattime_c_id);
            selleraddress=itemView.findViewById(R.id.seller_list_address_c_id);
            sellerbname=itemView.findViewById(R.id.seller_list_bname_c_id);
            sellerid=itemView.findViewById(R.id.seller_list_sId_c_id);
            selleremail=itemView.findViewById(R.id.seller_list_sellerEamil_c_id);
            sellergender=itemView.findViewById(R.id.seller_list_gender_c_id);
            sellermobile=itemView.findViewById(R.id.seller_list_conteact_c_id);
            up_arrow=itemView.findViewById(R.id.up_arrow_sellerlist_single_card_id);
            down_arrow=itemView.findViewById(R.id.down_arrow_SellerList_single_card_id);
            sellerview_layout=itemView.findViewById(R.id.view_layout_sellerlist_C_id);
            sellerexpadable_layout=itemView.findViewById(R.id.expadable_layout_sellerlist_id);

        }
    }





}
