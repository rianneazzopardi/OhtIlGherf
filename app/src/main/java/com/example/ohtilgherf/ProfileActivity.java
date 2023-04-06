package com.example.ohtilgherf;

import android.app.Activity;
import android.content.Intent;
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

        // Hiding the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_profile);
        ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
        TextView highScore = (TextView) findViewById(R.id.high_scoring);
        CardView card = (CardView) findViewById(R.id.picture_container);
        helper = new DbHelper(this);

        highScore.setText("High Score: " + helper.getHighScore() + "/5");

        // Converting the profile picture from Base64 to an image
        String base = helper.getProfilePicture();
        if(base == null){
            // If the profile picture isn't set, a default image is shown
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
        // An advanced intent which allows the user to pick a profile picture from gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // A handler for the advanced intent
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Chosen image is converted to Bse64 and saved in the db
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                helper.updateProfilePicture(encoded);

                // Activity is reloaded to reflect new profile picture
                Intent intent = new Intent(this, ProfileActivity.class);
                finish();
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void goToCategoryScores(View v){
//        Intent intent = new Intent(this, CategoryScores.class);
//        startActivity(intent);
//    }
//
//    public void goToDifficulyScores(View v){
//        Intent intent = new Intent(this, DifficultyScores.class);
//        startActivity(intent);
//    }

}