package com.murach.baikiemtraso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    private Button btnNewPage2, btnAdd, btnUpdate, btnDelete;
    private EditText employeeName, employeeEmail,employeePhone,employeeRole, employeeAvatar, adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnNewPage2 = findViewById(R.id.btnNewPage);
        employeeName = findViewById(R.id.employeeName);
        employeeEmail = findViewById(R.id.employeeEmail);
        employeePhone = findViewById(R.id.employeePhone);
        employeeRole = findViewById(R.id.employeeRole);
        employeeAvatar = findViewById(R.id.employeeAvatar);
        adminId = findViewById(R.id.adminId);

        btnNewPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}