package com.murach.baikiemtraso1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText adminName, adminEmail, adminWebsite, adminLogo, adminAddress, adminPhone, adminChildId;
    private Button btnAdd, btnUpdate, btnDelete,btnNewPage;
    private ListView listViewAdmin;
    private List<Admin> adminList;
    private ArrayAdapter<Admin> adapter;
    private AdminHelper adminHelper;
    private Admin selectedAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminName = findViewById(R.id.adminName);
        adminEmail = findViewById(R.id.adminEmail);
        adminWebsite = findViewById(R.id.adminWebsite);
        adminLogo = findViewById(R.id.adminLogo);
        adminAddress = findViewById(R.id.adminAddress);
        adminPhone = findViewById(R.id.adminPhone);
        adminChildId = findViewById(R.id.adminChildId);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnNewPage = findViewById(R.id.btnNewPage);
        btnNewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(a);
            }
        });


        listViewAdmin = findViewById(R.id.listViewAdmin);
        adminList = new ArrayList<>();
        adminHelper = new AdminHelper(); // Initialize AdminHelper

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adminList);
        listViewAdmin.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdmin();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAdmin();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAdmin();
            }
        });


        listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAdmin = adminList.get(i);
                // Populate fields with selectedAdmin data
                adminName.setText(selectedAdmin.getAdminName());
                adminEmail.setText(selectedAdmin.getAdminEmail());
                adminWebsite.setText(selectedAdmin.getWebsiteUrl());
                adminLogo.setText(selectedAdmin.getAdminLogo());
                adminAddress.setText(selectedAdmin.getAdminAddress());
                adminPhone.setText(selectedAdmin.getAdminPhone());
                adminChildId.setText(selectedAdmin.getAdminChildId());
            }
        });

        loadAdmins();
    }

    private void addAdmin() {
        String name = adminName.getText().toString().trim();
        String email = adminEmail.getText().toString().trim();
        String website = adminWebsite.getText().toString().trim();
        String logo = adminLogo.getText().toString().trim();
        String address = adminAddress.getText().toString().trim();
        String phone = adminPhone.getText().toString().trim();
        String childId = adminChildId.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Admin admin = new Admin(name, email, website, logo, address, phone, childId);
        adminHelper.addAdmin(admin);

        Toast.makeText(this, "Admin added successfully", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void updateAdmin() {
        if (selectedAdmin == null) {
            Toast.makeText(this, "Please select an admin to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = selectedAdmin.getId();
        String name = adminName.getText().toString().trim();
        String email = adminEmail.getText().toString().trim();
        String website = adminWebsite.getText().toString().trim();
        String logo = adminLogo.getText().toString().trim();
        String address = adminAddress.getText().toString().trim();
        String phone = adminPhone.getText().toString().trim();
        String childId = adminChildId.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Admin admin = new Admin(id, name, email, website, logo, address, phone, childId);
        adminHelper.updateAdmin(id, admin);

        Toast.makeText(this, "Admin updated successfully", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void deleteAdmin() {
        if (selectedAdmin == null) {
            Toast.makeText(this, "Please select an admin to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = selectedAdmin.getId();
        adminHelper.deleteAdmin(id);

        Toast.makeText(this, "Admin deleted successfully", Toast.LENGTH_SHORT).show();
        clearFields();
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

    private void clearFields() {
        adminName.setText("");
        adminEmail.setText("");
        adminWebsite.setText("");
        adminLogo.setText("");
        adminAddress.setText("");
        adminPhone.setText("");
        adminChildId.setText("");
        selectedAdmin = null;  // Clear the selectedAdmin
    }
}
