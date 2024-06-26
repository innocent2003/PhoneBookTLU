// FragmentTwo.java
package com.murach.baikiemtraso1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private EmployeeAdapter adapter;
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
        adapter = new EmployeeAdapter(getActivity(), R.layout.list_item_employee, employeeList);
        listViewEmployee.setAdapter(adapter);

        // Initialize EmployeeHelper
        employeeHelper = new EmployeeHelper();

        // Load the employees from Firebase
        loadEmployees();

        addEmployeeButton = view.findViewById(R.id.circularButton);
        // Set item click listener to navigate to DetailEmployeeActivity
        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee selectedEmployee = employeeList.get(position);
                Intent intent = new Intent(getActivity(), DetailEmployeeActivity.class);
                intent.putExtra("employeeId", selectedEmployee.getId());
                intent.putExtra("employeeName", selectedEmployee.getEmployeeName());
                intent.putExtra("employeeEmail", selectedEmployee.getEmployeeEmail());
                intent.putExtra("employeePhone", selectedEmployee.getEmployeePhone());
                intent.putExtra("employeeRole", selectedEmployee.getEmployeeRole());
                intent.putExtra("adminId", selectedEmployee.getAdminId());
                intent.putExtra("employeeImageUrl", selectedEmployee.getEmployeeAvatar());
                startActivity(intent);
            }
        });

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
