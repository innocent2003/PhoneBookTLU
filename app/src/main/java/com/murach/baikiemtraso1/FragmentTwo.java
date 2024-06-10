package com.murach.baikiemtraso1;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentTwo extends Fragment {
    Button addEmployeeButton;
    private ArrayAdapter<Employee> adapter;
    private ListView listViewEmployee;
    private List<Admin> adminList;

    private AdminHelper adminHelper;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        // Initialize the ListView
//        listViewAdmin = view.findViewById(R.id.listViewAdmin);

        // Initialize the list and adapter
        adminList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, adminList);
//        listViewAdmin.setAdapter(adapter);

        // Initialize AdminHelper
        adminHelper = new AdminHelper();


        // Load the admins from Firebase
//        loadAdmins();
        addEmployeeButton = view.findViewById(R.id.circularButton);

        // Set click listener to navigate to another activity
        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEmployeeActivity.class); // Replace AddAdminActivity with your target activity
                startActivity(intent);
            }
        });

        return view;
    }
}


