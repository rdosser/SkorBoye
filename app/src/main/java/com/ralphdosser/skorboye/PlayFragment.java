package com.ralphdosser.skorboye;

import android.media.MediaPlayer;
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

public class PlayFragment extends Fragment implements RowViewAdapter.ClickListener {

    public static final String TAG = "PlayFragment";
    public static final int DEFAULT_SCORE = 50;
    public static final int DEFAULT_NUM_PLAYERS = 4;
    public static final String ARG_NUM_PLAYERS = "num_players";

    private MediaPlayer mediaPlayerClickMinus;
    private MediaPlayer mediaPlayerClickPlus;
    private MediaPlayer mediaPlayerFail;

    private int numPlayers = 0;

    public static PlayFragment newInstance(int numPlayers) {
        Bundle args = new Bundle();
        args.putInt(ARG_NUM_PLAYERS, numPlayers);
        PlayFragment playFragment = new PlayFragment();
        playFragment.setArguments(args);
        return playFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        if (getArguments() != null) {
            numPlayers = getArguments().getInt(ARG_NUM_PLAYERS);
        }
        if (numPlayers == 0) {
            numPlayers = DEFAULT_NUM_PLAYERS;
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<PlayerScore> playerScores = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            String ordinal = String.valueOf(i + 1);
            playerScores.add(new PlayerScore("Player " + ordinal, DEFAULT_SCORE));
        }

        RecyclerView.Adapter mAdapter = new RowViewAdapter(playerScores, this);
        recyclerView.setAdapter(mAdapter);

        mediaPlayerClickMinus = MediaPlayer.create(getContext(), R.raw.click_minus);
        mediaPlayerClickPlus = MediaPlayer.create(getContext(), R.raw.click_plus);
        mediaPlayerFail = MediaPlayer.create(getContext(), R.raw.sad_trumpet);

        mediaPlayerClickMinus.setVolume(1.0f,1.0f);
        mediaPlayerClickPlus.setVolume(1.0f,1.0f);
        mediaPlayerFail.setVolume(1.0f,1.0f);

        return view;
    }

    @Override
    public void onPlusClick() {
        mediaPlayerClickPlus.start();
    }

    @Override
    public void onMinusClick() {
        mediaPlayerClickMinus.start();
    }

    @Override
    public void onFail() {
        mediaPlayerFail.start();
    }
}
