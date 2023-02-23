package com.example.jewerlyadmin.SellerReq;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Random;

public class SellerRequestAdapter extends FirebaseRecyclerAdapter<SellerRequestModel, SellerRequestAdapter.SellerReqViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    SellerRequestFragment context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param activity
     */
    public SellerRequestAdapter(@NonNull FirebaseRecyclerOptions<SellerRequestModel> options, FragmentActivity activity) {
        super(options);
    }



    @NonNull
    @Override
    public SellerReqViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_seller_request, parent, false);
        return new SellerReqViewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull SellerReqViewholder holder, @SuppressLint("RecyclerView") int position, @NonNull SellerRequestModel model) {
        holder.sellerreqid.setText(model.getSellerRequestId());
        holder.sellername.setText(model.getSellerName());
        holder.selleremail.setText(model.getSellerEmail());
        holder.sellercontect.setText(model.getSellerMobileNo());
        holder.sellergender.setText(model.getSellerGender());
        holder.selleraddress.setText(model.getSellerAddress());
        holder.sellerbname.setText(model.getSellerBusinessName());
        holder.selleradddatetime.setText(model.getSellerAddDate());
        String emailmes = "Dear " + holder.sellername.getText().toString() + ",\n" +
                "\n" +
                "Thank you for your interest in becoming a seller in our app. After reviewing your application, we have decided not to approve your request at this time.\n" +
                "\n" +
                "We appreciate your interest and hope that you will consider applying again in the future. If you have any questions or concerns, please don't hesitate to contact us.\n" +
                "\n" +
                "Sincerely,\n" +
                "Jewerly App";
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FirebaseDatabase.getInstance().getReference().child("Admin").child("SellerRequest").child(getRef(position).getKey()).removeValue();
                    notifyDataSetChanged();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

//                Intent emailintent = new Intent(Intent.ACTION_SEND);
//                emailintent.setType("text/plain");
//                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{model.getSellerEmail()});
//                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Become A seller in Jewerly App");
//                emailintent.putExtra(Intent.EXTRA_TEXT, emailmes);
//
//                context.startActivity(Intent.createChooser(emailintent, "Send Email"));
//                Toast.makeText(v.getContext(), "send mail", Toast.LENGTH_SHORT).show();


            }
        });


        holder.sellerReqdown_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expadablelayout.setVisibility(View.VISIBLE);
                holder.sellerReqdown_arrow.setVisibility(View.GONE);
            }
        });
        holder.selerRequp_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expadablelayout.setVisibility(View.GONE);
                holder.sellerReqdown_arrow.setVisibility(View.VISIBLE);
            }
        });
        TextView textView;
        holder.approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth auth = FirebaseAuth.getInstance();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Passwordgenerat(6);
                String Password = Passwordgenerat(6).toString();

                auth.createUserWithEmailAndPassword(holder.selleremail.getText().toString(), Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(v.getContext(), "auth done", Toast.LENGTH_SHORT).show();

                                    String  sellerId=  auth.getCurrentUser().getUid();
                                    System.out.println(sellerId);
                                    SellerRequestModel sellerRequestModel = new SellerRequestModel( holder.selleradddatetime.getText().toString(),holder.selleraddress.getText().toString(), holder.sellerbname.getText().toString(), holder.selleremail.getText().toString(), holder.sellergender.getText().toString(), holder.sellercontect.getText().toString(), holder.sellername.getText().toString().toString(),sellerId);
                                    reference.child("User").child("Seller").child(auth.getCurrentUser().getUid()).setValue(sellerRequestModel);
                                    Toast.makeText(v.getContext(), "database done", Toast.LENGTH_SHORT).show();
                                    sendlinkEmail();




                                } else {
                                    Toast.makeText(v.getContext(), "auth not done", Toast.LENGTH_SHORT).show();
                                }
                            }

                            private void sendlinkEmail() {
                                auth.sendPasswordResetEmail(holder.selleremail.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @SuppressLint("ResourceAsColor")
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(v.getContext(), "send email ", Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(v.getContext(), "successfully create Seller", Toast.LENGTH_SHORT).show();


                                                } else {
                                                    Toast.makeText(v.getContext(), "some proble with email", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(v.getContext(), "error", Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });




            }
        });

    }


    private static char[] Passwordgenerat(int length) {

        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;

        Random random = new Random();
        char[] password = new char[length];
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }

        return password;


    }

    public static class SellerReqViewholder extends RecyclerView.ViewHolder { ///viewholder class

        TextView sellerreqid, sellername, selleremail, sellergender, sellercontect, selleraddress, sellerbname, selleradddatetime;
        ImageView selerRequp_arrow, sellerReqdown_arrow;
        LinearLayout expadablelayout;
        Button approveBtn, deleteBtn;
        CardView sellerreqcard;

        public SellerReqViewholder(@NonNull View itemView) {
            super(itemView);
            sellername = itemView.findViewById(R.id.seller_req_name_c_id);
            sellerreqid = itemView.findViewById(R.id.seller_req_requestID_c_id);
            selleremail = itemView.findViewById(R.id.seller_req_Email_c_id);
            sellergender = itemView.findViewById(R.id.seller_req_gender_c_id);
            sellercontect = itemView.findViewById(R.id.seller_req_conteact_c_id);
            selleraddress = itemView.findViewById(R.id.seller_req_address_c_id);
            sellerbname = itemView.findViewById(R.id.seller_req_bname_c_id);
            selleradddatetime = itemView.findViewById(R.id.seller_req_adddattime_c_id);
            selerRequp_arrow = itemView.findViewById(R.id.up_arrow_sellerReq_single_card_id);
            sellerReqdown_arrow = itemView.findViewById(R.id.down_arrow_SellerREq_single_card_id);

            approveBtn = itemView.findViewById(R.id.approve_Req_seller_c_id);
            deleteBtn = itemView.findViewById(R.id.delete_Req_seller_c_id);

            expadablelayout = itemView.findViewById(R.id.expadable_layout_sellerReq_id);
            sellerreqcard = itemView.findViewById(R.id.sellerREqcard_id);
        }
    }


}
