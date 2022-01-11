package com.example.quizhalloween;

import com.example.quizhalloween.quest.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseAdapter {

    private DatabaseReference mDatabase;

    public void writeUser(String userId, String name, String surName, int result) {
        UserInformation user = new UserInformation(name, surName, result);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userId).setValue(user);
    }

    public void writeQuest(ArrayList<Question> questions) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("quest").setValue(questions);
    }
}
