package com.example.quizhalloween;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentRating extends Fragment implements View.OnClickListener {
    private RecyclerView rating;
    private Button toMenu;
    private UserAdapter userAdapter;
    private LinearLayoutManager lm;
    private ArrayList<UserInformation> userInformation = new ArrayList<>();
    DatabaseReference database;

    OnSelectedButtonListener onSelectedButtonListener;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            onSelectedButtonListener = (OnSelectedButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ratingView = inflater.inflate(R.layout.rating_user, container, false);

        rating = ratingView.findViewById(R.id.rating);

        toMenu = ratingView.findViewById(R.id.toMune);
        toMenu.setOnClickListener(this);

        lm = new LinearLayoutManager(this.getActivity());
        lm.setReverseLayout(true);
        lm.setStackFromEnd(true);
        rating.setLayoutManager(lm);
        userAdapter = new UserAdapter(this.getActivity(), userInformation);
        database = FirebaseDatabase.getInstance().getReference().child("users");
        rating.setAdapter(userAdapter);
        getData();

        return ratingView;
    }

    private void getData() {
        database.orderByChild("userResult").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserInformation user = snapshot.getValue(UserInformation.class);
                userInformation.add(new UserInformation(user.getUserName(), user.getUserSurName(), user.getUserResult()));
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toMune:
                onSelectedButtonListener.onButtonSelected(4); // меню
                break;
        }
    }
}
