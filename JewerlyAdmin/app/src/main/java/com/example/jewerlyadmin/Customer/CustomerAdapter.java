package com.example.jewerlyadmin.Customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerAdapter extends FirebaseRecyclerAdapter<CustomerListModel, CustomerAdapter.customerlistViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustomerAdapter(@NonNull FirebaseRecyclerOptions<CustomerListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull customerlistViewholder holder, int position, @NonNull CustomerListModel model) {
        holder.customerName.setText(model.getCustomerName());
        holder.customerID.setText(model.getCustomerId());
        holder.customerMobile.setText(model.getCustomerMobile());
        holder.customerEmail.setText(model.getCustomerEmail());

        holder.maincard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete item");
                builder.setMessage("are you sure delete");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth auth;
                        auth = FirebaseAuth.getInstance();
                        FirebaseDatabase.getInstance().getReference("User").child("Customer").child(holder.customerID.getText().toString()).removeValue();
                        FirebaseDatabase.getInstance().getReference("Cart").child(holder.customerID.getText().toString()).removeValue();

                        auth.signInWithEmailAndPassword(holder.customerEmail.getText().toString(), model.getCustomerPassword().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        auth.getCurrentUser().delete();
                                        Toast.makeText(v.getContext(), "Successfully  delete Customer", Toast.LENGTH_SHORT).show();
                                    }
                                });

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
    }

    @NonNull
    @Override
    public customerlistViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_single_card, parent, false);
        return new customerlistViewholder(view);
    }

    public static class customerlistViewholder extends RecyclerView.ViewHolder {
        TextView customerEmail, customerMobile, customerID, customerName;
        CardView maincard;

        public customerlistViewholder(@NonNull View itemView) {
            super(itemView);
            customerEmail = itemView.findViewById(R.id.customer_email_c_id);
            customerName = itemView.findViewById(R.id.customer_name_c_id);
            customerMobile = itemView.findViewById(R.id.customer_mobile_c_id);
            customerID = itemView.findViewById(R.id.customer_Id_c_id);
            maincard = itemView.findViewById(R.id.customerlist_card_id);

        }
    }
}
