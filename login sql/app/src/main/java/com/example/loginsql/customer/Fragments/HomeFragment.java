package com.example.loginsql.customer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsql.R;
import com.example.loginsql.customer.GroupAdapter;
import com.example.loginsql.customer.model;
import com.example.loginsql.customer.Fragments.product.ProductAdapter;
import com.example.loginsql.customer.Fragments.product.ProductModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HomeFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


    RecyclerView grecyclerView, pro_recycleView;
    FirebaseAuth firebaseAuth;
    ProductAdapter productAdapter;
        ImageView notificationBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        grecyclerView = view.findViewById(R.id.group_recycle_id);
        pro_recycleView = view.findViewById(R.id.product_recyclerview_id);


        GroupAdapter groupAdapter = new GroupAdapter(grupdata());
        LinearLayoutManager Horizontal = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        grecyclerView.setAdapter(groupAdapter);
        grecyclerView.setLayoutManager(Horizontal);

        pro_recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        notificationBtn=view.findViewById(R.id.notification_btn_id);

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();

            NotificationFragment fragment=new NotificationFragment();
            getFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
            }
        });


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.keepSynced(true);

        FirebaseRecyclerOptions<ProductModel> options =
                new FirebaseRecyclerOptions.Builder<ProductModel>()
                        .setQuery(databaseReference, ProductModel.class)
                        .build();


        productAdapter = new ProductAdapter(options);

        pro_recycleView.setAdapter(productAdapter);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        productAdapter.stopListening();

    }

    public ArrayList<model> grupdata() {
        ArrayList<model> dlist = new ArrayList<>();

        model g1 = new model();
        g1.setG_tag("Gold");
        g1.setG_img(R.drawable.g1);
        dlist.add(g1);

        model g2 = new model();
        g2.setG_tag("Silver");
        g2.setG_img(R.drawable.g2);
        dlist.add(g2);

        model g3 = new model();
        g3.setG_tag("Ring");
        g3.setG_img(R.drawable.ring_img);
        dlist.add(g3);

        model g4 = new model();
        g4.setG_tag("Necklace");
        g4.setG_img(R.drawable.necklace);
        dlist.add(g4);

        model g5 = new model();
        g5.setG_tag("Earring");
        g5.setG_img(R.drawable.earring);
        dlist.add(g5);

        return dlist;

    }


}