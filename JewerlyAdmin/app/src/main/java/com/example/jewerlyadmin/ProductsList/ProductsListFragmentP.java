package com.example.jewerlyadmin.ProductsList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsListFragmentP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsListFragmentP extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductsListFragmentP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsListFragmentP newInstance(String param1, String param2) {
        ProductsListFragmentP fragment = new ProductsListFragmentP();
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

    RecyclerView productreccylerview;
    ProductListAdapter productListAdapter;
    TextView productCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products_list_p, container, false);
        productreccylerview = view.findViewById(R.id.product_list_recycler_view);
        productCount = view.findViewById(R.id.product_totalproduct_count_id);
        productreccylerview.setLayoutManager(new LinearLayoutManager(getContext()));





        FirebaseRecyclerOptions<ProductModel> options = new FirebaseRecyclerOptions.Builder<ProductModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), ProductModel.class)
                .build();

        productListAdapter = new ProductListAdapter(options);

            try {

                productreccylerview.setAdapter(productListAdapter);
            }
            catch (Exception e)
            {
                Toast.makeText(getContext(), "Addeding products", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        productListAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productListAdapter.stopListening();
    }
}