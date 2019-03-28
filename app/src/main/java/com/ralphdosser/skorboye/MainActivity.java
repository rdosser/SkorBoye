package com.ralphdosser.skorboye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ralphdosser.skorboye.adapters.RowViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO - add a hamburger menu
        // - new game
        // - save game

        // TODO - move current game viewer into a fragment

        // TODO - Allow add/remove of players

        // TODO - allow tracking of current player (tint background)

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> playerNames = new ArrayList<>();
        playerNames.add("Player 1");
        playerNames.add("Player 2");
        playerNames.add("Player 3");
        playerNames.add("Player 4");
        playerNames.add("Player 5");
        playerNames.add("Player 6");

        mAdapter = new RowViewAdapter(playerNames);
        recyclerView.setAdapter(mAdapter);
    }
}
