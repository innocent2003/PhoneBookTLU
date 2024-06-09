package com.murach.baikiemtraso1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHelper {
private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencesAdmin;

    public AdminHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferencesAdmin = mDatabase.getReference("admins");
    }

    public FirebaseDatabase getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(FirebaseDatabase mDatabase) {
        this.mDatabase = mDatabase;
    }

    public DatabaseReference getmReferencesAdmin() {
        return mReferencesAdmin;
    }

    public void setmReferencesAdmin(DatabaseReference mReferencesAdmin) {
        this.mReferencesAdmin = mReferencesAdmin;
    }

    public void addAdmin(Admin admin) {
        String id = mReferencesAdmin.push().getKey();
        admin.setId(id);
        mReferencesAdmin.child(id).setValue(admin);
    }

    public void updateAdmin(String id, Admin admin) {
        mReferencesAdmin.child(id).setValue(admin);
    }

    public void deleteAdmin(String id) {
        mReferencesAdmin.child(id).removeValue();
    }



}
