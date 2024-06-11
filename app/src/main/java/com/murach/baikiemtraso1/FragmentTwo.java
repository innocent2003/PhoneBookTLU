package com.murach.baikiemtraso1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentTwo extends Fragment {
    Button addEmployeeButton;
    private ArrayAdapter<Employee> adapter;
    private ListView listViewEmployee;
    private List<Employee> employeeList;
    private EmployeeHelper employeeHelper;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        // Initialize the ListView
        listViewEmployee = view.findViewById(R.id.listViewEmployee);

        // Initialize the list and adapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, employeeList);
        listViewEmployee.setAdapter(adapter);

        // Initialize EmployeeHelper
        employeeHelper = new EmployeeHelper();

        // Load the employees from Firebase
        loadEmployees();

        addEmployeeButton = view.findViewById(R.id.circularButton);

        // Set click listener to navigate to another activity
        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void loadEmployees() {
        employeeHelper.getmReferencesEmployee().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employeeList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Employee employee = snapshot.getValue(Employee.class);
                    employeeList.add(employee);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }
}
