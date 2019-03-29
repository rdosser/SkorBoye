package com.ralphdosser.skorboye;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ralphdosser.skorboye.adapters.RowViewAdapter;
import com.ralphdosser.skorboye.models.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class PlayFragment extends Fragment {

    public static final String TAG = "PlayFragment";
    public static final int DEFAULT_SCORE = 5;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<PlayerScore> playerScores = new ArrayList<>();
        playerScores.add(new PlayerScore("Player 1", DEFAULT_SCORE));
        playerScores.add(new PlayerScore("Player 2", DEFAULT_SCORE));
        playerScores.add(new PlayerScore("Player 3", DEFAULT_SCORE));
        playerScores.add(new PlayerScore("Player 4", DEFAULT_SCORE));
        playerScores.add(new PlayerScore("Player 5", DEFAULT_SCORE));
        playerScores.add(new PlayerScore("Player 6", DEFAULT_SCORE));

        mAdapter = new RowViewAdapter(playerScores);
        recyclerView.setAdapter(mAdapter);

        // TODO - Allow add/remove of players

        return view;
    }
}
