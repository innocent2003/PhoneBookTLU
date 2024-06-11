package com.murach.baikiemtraso1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddAdminActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText adminName, adminEmail, adminWebsite, adminLogo, adminAddress, adminPhone, adminChildId;
    private ImageView adminLogoPreview;
    private Button btnUploadLogo, btnAdd;
    private Uri logoUri;

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
        adminLogoPreview = findViewById(R.id.adminLogoPreview);
        btnUploadLogo = findViewById(R.id.btnUploadLogo);
        btnAdd = findViewById(R.id.btnAdd);

        adminHelper = new AdminHelper();

        btnUploadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdmin();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            logoUri = data.getData();
            Picasso.get().load(logoUri).into(adminLogoPreview);
        }
    }

    private void addAdmin() {
        String name = adminName.getText().toString().trim();
        String email = adminEmail.getText().toString().trim();
        String website = adminWebsite.getText().toString().trim();
        String address = adminAddress.getText().toString().trim();
        String phone = adminPhone.getText().toString().trim();
        String childId = adminChildId.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (logoUri != null) {
            uploadLogoAndAddAdmin(name, email, website, address, phone, childId);
        } else {
            addAdminToDatabase(new Admin(name, email, website, "", address, phone, childId));
        }
    }

    private void uploadLogoAndAddAdmin(final String name, final String email, final String website, final String address, final String phone, final String childId) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("admin_logos").child(System.currentTimeMillis() + ".jpg");

        storageReference.putFile(logoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String logoUrl = uri.toString();
                                addAdminToDatabase(new Admin(name, email, website, logoUrl, address, phone, childId));
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddAdminActivity.this, "Failed to upload logo", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addAdminToDatabase(Admin admin) {
        adminHelper.addAdmin(admin);
        Toast.makeText(this, "Admin added successfully", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        adminName.setText("");
        adminEmail.setText("");
        adminWebsite.setText("");
        adminLogo.setText("");
        adminAddress.setText("");
        adminPhone.setText("");
        adminChildId.setText("");
        adminLogoPreview.setImageURI(null);
        logoUri = null;
    }
}
