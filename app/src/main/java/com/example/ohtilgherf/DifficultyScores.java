package com.example.ohtilgherf;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ohtilgherf.Difficulty_Scores.easy.EasyFragment;
import com.example.ohtilgherf.Difficulty_Scores.hard.HardFragment;
import com.example.ohtilgherf.Difficulty_Scores.medium.MediumFragment;

import com.example.ohtilgherf.databinding.ActivityDifficultyScoresBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DifficultyScores extends AppCompatActivity {

    private ActivityDifficultyScoresBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDifficultyScoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //passing each menu id as a set of ids because each menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_easy, R.id.nav_medium, R.id.nav_hard)
                .build();
        NavController nav_controller = Navigation.findNavController(this, R.id.nav_host_fragment_activity_difficulty_scores);
        NavigationUI.setupActionBarWithNavController(this, nav_controller, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, nav_controller);

        //removing the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        MenuItem item = bottomNavigationView.getMenu().getItem(0);
        SpannableString spannableString = new SpannableString(item.getTitle());
        item.setTitle(spannableString);

        //setting a listener to detect when a menu item is selected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //handler for when a menu option is clicked on
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //looping through all menu items to reset the color to black
                int size = bottomNavigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
                    SpannableString spannableString = new SpannableString(menuItem.getTitle());
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(DifficultyScores.this, R.color.black)), 0, spannableString.length(), 0);
                    menuItem.setTitle(spannableString);
                }


                SpannableString spannableString = new SpannableString(item.getTitle());
                item.setTitle(spannableString);

                //switching the fragment according to the one chosen by the user
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_easy:
                        selectedFragment = new EasyFragment();
                        break;
                    case R.id.navigation_medium:
                        selectedFragment = new MediumFragment();
                        break;
                    case R.id.navigation_hard:
                        selectedFragment = new HardFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_difficulty_scores, selectedFragment).commit();

                return true;
            }
        });
    }



}