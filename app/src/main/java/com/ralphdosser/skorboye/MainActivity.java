package com.ralphdosser.skorboye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ralphdosser.skorboye.views.RowView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    int currentScore = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RowView rowView = findViewById(R.id.rowview);
        if (rowView != null) {
            rowView.setScore(String.valueOf(currentScore));
        } else {
            Log.e(TAG, "RowView is null");
        }
    }
}
