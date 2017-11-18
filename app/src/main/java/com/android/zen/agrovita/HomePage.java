package com.android.zen.agrovita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Lenovo on 17.11.2017.
 */

public class HomePage extends Activity {

    private static int SHOW_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                finish();
                Intent i = new Intent(HomePage.this,MainActivity.class);
                startActivity(i);
            }
        }, SHOW_TIME);

    }
}