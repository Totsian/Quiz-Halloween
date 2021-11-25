package com.example.quizhalloween;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<UserInformation> userInf;

    UserAdapter(Context context, ArrayList<UserInformation> userInf) {
        this.userInf = userInf;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_rating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInformation userInformation = userInf.get(position);
        holder.userName.setText(userInformation.getUserName());
        holder.userSurName.setText(userInformation.getUserSurName());
        String result = Integer.toString(userInformation.getUserResult());
        holder.userRating.setText(result);
    }

    @Override
    public int getItemCount() {
        return userInf == null ? 0 : userInf.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //        final ImageView userImage;
        final TextView userRating;
        final TextView userName;
        final TextView userSurName;

        public ViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.nameRating);
            userSurName = view.findViewById(R.id.surNameRating);
            userRating = view.findViewById(R.id.resultRating);
        }
    }
}
