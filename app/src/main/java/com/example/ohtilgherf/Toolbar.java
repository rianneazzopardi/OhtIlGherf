package com.example.ohtilgherf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ohtilgherf.backend.DbHelper;

public class Toolbar extends Fragment {

    private ImageView back;
    private ImageView profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);

        back =  (ImageView) view.findViewById(R.id.back_button);
        profile = (ImageView) view.findViewById(R.id.profile_button);
        CardView card = (CardView) view.findViewById(R.id.profile_container);
        DbHelper helper = new DbHelper(view.getContext());

        //converting the profile picture from base64 to an image
        String base = helper.getProfilePicture();
        if(base == null){
            //if a profile picture wasn't chosen, a default image is shown
            profile.setImageResource(R.drawable.brain);
            card.setCardBackgroundColor(Color.TRANSPARENT);
        }
        else {
            byte[] decodedString = Base64.decode(base, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profile.setImageBitmap(bitmap);
            card.setCardBackgroundColor(Color.WHITE);

        }

        //if the back icon is pressed, the previous screen is shown
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity a  = (Activity) v.getContext();
                a.onBackPressed();
            }
        });

        //if the profile icon is pressed, an intent is used to go to the Profile Activity
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) v.getContext(), ProfileActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }
}