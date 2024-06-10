package com.murach.baikiemtraso1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddEmployeeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText employeeName, employeeEmail, employeePhone, employeeRole, adminId;
    private Button btnAddEmployee, btnSelectImage, btnUploadImage;
    private ImageView imageView;
    private Uri imageUri;

    private EmployeeHelper employeeHelper;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Initialize views
        employeeName = findViewById(R.id.employeeName);
        employeeEmail = findViewById(R.id.employeeEmail);
        employeePhone = findViewById(R.id.employeePhone);
        employeeRole = findViewById(R.id.employeeRole);
        adminId = findViewById(R.id.adminId);

        imageView = findViewById(R.id.imageView);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);

        // Initialize EmployeeHelper and StorageReference
        employeeHelper = new EmployeeHelper();
        storageReference = FirebaseStorage.getInstance().getReference("employee_avatars");

        // Set onClickListeners
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        Toast.makeText(AddEmployeeActivity.this, "Image Upload Successful", Toast.LENGTH_SHORT).show();
                                        imageView.setTag(downloadUri.toString()); // Store the URL in the ImageView tag for later use
                                    }
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEmployeeActivity.this, "Image Upload Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void addEmployee() {
        String name = employeeName.getText().toString().trim();
        String email = employeeEmail.getText().toString().trim();
        String phone = employeePhone.getText().toString().trim();
        String role = employeeRole.getText().toString().trim();
        String adminIdStr = adminId.getText().toString().trim();
        String avatarUrl = (String) imageView.getTag();

//        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || adminIdStr.isEmpty() || avatarUrl == null) {
//            Toast.makeText(this, "Please fill in all required fields and upload an image", Toast.LENGTH_SHORT).show();
//            return;
//        }

        String id = employeeHelper.getmReferencesEmployee().push().getKey();
        Employee employee = new Employee(id, name, role, email, phone, avatarUrl, adminIdStr);
        employeeHelper.getmReferencesEmployee().child(id).setValue(employee);

        Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        employeeName.setText("");
        employeeEmail.setText("");
        employeePhone.setText("");
        employeeRole.setText("");
        adminId.setText("");
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        imageView.setTag(null);
    }
}
