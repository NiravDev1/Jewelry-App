package com.example.jewerlyadmin.SellerReq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellerRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellerNotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerRequestFragment newInstance(String param1, String param2) {
        SellerRequestFragment fragment = new SellerRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView sellerRequestlist;
    SellerRequestAdapter sellerRequestAdapter;
    DatabaseReference databaseReference;
    TextView rcont;
    long a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_notification, container, false);
        sellerRequestlist = view.findViewById(R.id.recycler_view_sellerRequest_id);
        rcont=view.findViewById(R.id.total_request_count_id);

        sellerRequestlist.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions<SellerRequestModel> options = new FirebaseRecyclerOptions.Builder<SellerRequestModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Admin").child("SellerRequest"), SellerRequestModel.class)
                .build();
        databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child("SellerRequest");
        Query query=databaseReference;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a=snapshot.getChildrenCount();
                rcont.setText(String.valueOf(a));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sellerRequestAdapter = new SellerRequestAdapter(options,getActivity());

        sellerRequestlist.setAdapter(sellerRequestAdapter);

        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
        sellerRequestAdapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        sellerRequestAdapter.startListening();
    }
}