package com.murach.baikiemtraso1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {
    Button addAdminButton;
    private ListView listViewAdmin;
    private List<Admin> adminList;
    private ArrayAdapter<Admin> adapter;
    private AdminHelper adminHelper;
    private Admin selectedAdmin;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        // Initialize the ListView
        listViewAdmin = view.findViewById(R.id.listViewAdmin);

        // Initialize the list and adapter
        adminList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, adminList);
        listViewAdmin.setAdapter(adapter);

        // Initialize AdminHelper
        adminHelper = new AdminHelper();


        // Load the admins from Firebase
        loadAdmins();
        addAdminButton = view.findViewById(R.id.circularButton);

        // Set click listener to navigate to another activity
        addAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAdminActivity.class); // Replace AddAdminActivity with your target activity
                startActivity(intent);
            }
        });
        listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getActivity(), DetailAdminActivity.class);
                startActivity(intent1);
            }
        });

        return view;
    }
    private void loadAdmins() {
        adminHelper.getmReferencesAdmin().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                adminList.clear();
                for (DataSnapshot adminSnapshot : snapshot.getChildren()) {
                    Admin admin = adminSnapshot.getValue(Admin.class);
                    if (admin != null) {
                        adminList.add(admin);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}

