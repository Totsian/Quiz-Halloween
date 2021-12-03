package com.example.quizhalloween;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAdapter {

    private DatabaseReference mDatabase;

    public void writeUser(String userId, String name, String surName, int result) {
        UserInformation user = new UserInformation(name, surName, result);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userId).setValue(user);
    }
}
