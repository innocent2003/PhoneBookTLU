package com.murach.baikiemtraso1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailAdminActivity extends AppCompatActivity {
    private EditText adminName, adminEmail, adminWebsite, adminAddress, adminPhone, adminChildId;
    private Button btnUpdate, btnDelete;
    private DatabaseReference databaseReference;
    private String adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

        adminId = getIntent().getStringExtra("adminId");
        adminName = findViewById(R.id.adminName);
        adminEmail = findViewById(R.id.adminEmail);
        adminWebsite = findViewById(R.id.adminWebsite);
        adminAddress = findViewById(R.id.adminAddress);
        adminPhone = findViewById(R.id.adminPhone);
        adminChildId = findViewById(R.id.adminChildId);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Populate the fields with the passed data
        adminName.setText(getIntent().getStringExtra("adminName"));
        adminEmail.setText(getIntent().getStringExtra("adminEmail"));
        adminWebsite.setText(getIntent().getStringExtra("adminWebsite"));
        adminAddress.setText(getIntent().getStringExtra("adminAddress"));
        adminPhone.setText(getIntent().getStringExtra("adminPhone"));
        adminChildId.setText(getIntent().getStringExtra("adminChildId"));

        databaseReference = FirebaseDatabase.getInstance().getReference("admins").child(adminId);

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
    }

    private void updateAdmin() {
        String name = adminName.getText().toString();
        String email = adminEmail.getText().toString();
        String website = adminWebsite.getText().toString();
        String address = adminAddress.getText().toString();
        String phone = adminPhone.getText().toString();
        String childId = adminChildId.getText().toString();

        Admin admin = new Admin(adminId, name, email, website, address, phone, childId);
        databaseReference.setValue(admin);
        Toast.makeText(this, "Admin updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void deleteAdmin() {
        databaseReference.removeValue();
        Toast.makeText(this, "Admin deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
