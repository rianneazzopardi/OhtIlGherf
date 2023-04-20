package com.example.ohtilgherf;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.ohtilgherf.backend.DbHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting the layout according to the orientation of the screen
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_profile_landscape);
        } else{
            setContentView(R.layout.activity_profile);
        }

        //removing the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
        TextView high_score = (TextView) findViewById(R.id.high_score);
        CardView card = (CardView) findViewById(R.id.picture_container);
        helper = new DbHelper(this);

        high_score.setText("High Score: " + helper.getHighScore() + "/5");

        //converting the profile picture from base64 to an image to be displayed on the profile screen
        String base = helper.getProfilePicture();
        if(base == null){
            //if a profile picture wasn't chosen, a default image is shown
            profile_pic.setImageResource(R.drawable.brain);
            card.setCardBackgroundColor(Color.TRANSPARENT);
        }
        else {
            byte[] decodedString = Base64.decode(base, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profile_pic.setImageBitmap(bitmap);
            card.setCardBackgroundColor(Color.WHITE);
        }
    }

    public void changeProfilePicture(View v){
        //allows the user to pick a profile picture from gallery - advanced intent
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //handler for the advanced intent
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                //the chosen image is converted to base64 and saved in the database in the user table
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                helper.updateProfilePicture(encoded);

                //activity is reloaded to reflect the change and hence display the profile picture
                Intent intent = new Intent(this, ProfileActivity.class);
                finish();
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToCategoryScores(View v){
        Intent intent = new Intent(this, CategoryScores.class);
        startActivity(intent);
    }

    public void goToDifficultyScores(View v){
        Intent intent = new Intent(this, DifficultyScores.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setting the layout according to the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_profile_landscape);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_profile);
        }
    }


}