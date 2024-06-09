package com.murach.baikiemtraso1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencesEmployee;

    public EmployeeHelper(FirebaseDatabase mDatabase, DatabaseReference mReferencesEmployee) {
        this.mDatabase = mDatabase;
        this.mReferencesEmployee = mReferencesEmployee;
    }

    public EmployeeHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferencesEmployee = mDatabase.getReference("employees");
    }

    public DatabaseReference getmReferencesEmployee() {
        return mReferencesEmployee;
    }
}
