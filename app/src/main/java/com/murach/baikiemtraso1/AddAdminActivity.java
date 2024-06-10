package com.murach.baikiemtraso1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAdminActivity extends AppCompatActivity {
    private EditText adminName, adminEmail, adminWebsite, adminLogo, adminAddress, adminPhone, adminChildId;

    private Admin selectedAdmin;
    private Button btnAdd, btnUpdate, btnDelete,btnNewPage;
    private AdminHelper adminHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        adminName = findViewById(R.id.adminName);
        adminEmail = findViewById(R.id.adminEmail);
        adminWebsite = findViewById(R.id.adminWebsite);
        adminLogo = findViewById(R.id.adminLogo);
        adminAddress = findViewById(R.id.adminAddress);
        adminPhone = findViewById(R.id.adminPhone);
        adminChildId = findViewById(R.id.adminChildId);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdmin();
            }
        });
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
//        clearFields();
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