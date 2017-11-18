package com.android.zen.agrovita;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Lenovo on 18.11.2017.
 */

public class OnBoardActivity extends TutorialActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the shared preferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Check if onboarding_complete is false
        if (!preferences.getBoolean("onboarding_complete", false)) {
            // Start the onboarding Activity

            addFragment(new Step.Builder().setTitle(getString(R.string.automatic_data)).setContent(getString(R.string.gm_finds_photos)).setBackgroundColor(Color.parseColor("#FF0957")).setDrawable(R.drawable.images1).setSummary(getString(R.string.continue_and_learn)).build());
            addFragment(new Step.Builder().setTitle(getString(R.string.choose_the_song)).setContent(getString(R.string.swap_to_the_tab)).setBackgroundColor(Color.parseColor("#00D4BA")).setDrawable(R.drawable.images2).setSummary(getString(R.string.continue_and_update)).build());
            addFragment(new Step.Builder().setTitle(getString(R.string.edit_data)).setContent(getString(R.string.update_easily)).setBackgroundColor(Color.parseColor("#1098FE")).setDrawable(R.drawable.images3).setSummary(getString(R.string.continue_and_result)).build());
            addFragment(new Step.Builder().setTitle(getString(R.string.result_awesome)).setContent(getString(R.string.after_updating)).setBackgroundColor(Color.parseColor("#CA70F3")).setDrawable(R.drawable.images4).setSummary(getString(R.string.thank_you)).build());

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.next) {
                        if (next.getText().toString().equalsIgnoreCase("finish")) {
                            //Toast.makeText(OnBoardActivity.this,currentItem,Toast.LENGTH_LONG).show();
                            // Get the shared preferences
                            SharedPreferences preferences =
                                    getSharedPreferences("my_preferences", MODE_PRIVATE);

                            // Set onboarding_complete to true
                            preferences.edit()
                                    .putBoolean("onboarding_complete", true).apply();

                            startActivity(new Intent(OnBoardActivity.this, HomePage.class));
                        }

                        changeFragment(true);

                    } else if (v.getId() == R.id.prev) {
                        if (prev.getText().toString().equalsIgnoreCase("cancel")) {
                            //Toast.makeText(OnBoardActivity.this,currentItem,Toast.LENGTH_LONG).show();
                            // Get the shared preferences
                            SharedPreferences preferences =
                                    getSharedPreferences("my_preferences", MODE_PRIVATE);

                            // Set onboarding_complete to true
                            preferences.edit()
                                    .putBoolean("onboarding_complete", true).apply();

                            startActivity(new Intent(OnBoardActivity.this, HomePage.class));
                        }

                        changeFragment(false);
                    }
                }
            });
        }
        else startActivity(new Intent(this, HomePage.class));
    }
}
