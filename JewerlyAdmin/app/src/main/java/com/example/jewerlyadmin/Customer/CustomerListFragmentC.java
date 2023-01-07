package com.example.jewerlyadmin.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewerlyadmin.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragmentC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragmentC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerListFragmentC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerListFragmentC newInstance(String param1, String param2) {
        CustomerListFragmentC fragment = new CustomerListFragmentC();
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

    RecyclerView customerlist;
    CustomerAdapter customerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_list_c, container, false);

        customerlist = view.findViewById(R.id.customer_list_recycler_view_id);
        customerlist.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<CustomerListModel> options = new FirebaseRecyclerOptions.Builder<CustomerListModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("User").child("Customer"), CustomerListModel.class)
                .build();

        customerAdapter = new CustomerAdapter(options);
        System.out.println(options);
        customerlist.setAdapter(customerAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        customerAdapter.stopListening();
    }
}