package com.ralphdosser.skorboye;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.alexzaitsev.meternumberpicker.MeterView;

public class NewGameFragment extends Fragment {

    public static final String TAG = "NewGameFragment";

    Button startGameButton;
    MeterView numPlayersMeterView;
    MeterView defaultScoreMeterView;
    ImageButton numPlayersPlusButton;
    ImageButton numPlayersMinusButton;
    ImageButton defaultScorePlusButton;
    ImageButton defaultScoreMinusButton;

    private MediaPlayer mediaPlayerNumPlayersClickMinus;
    private MediaPlayer mediaPlayerNumPlayersClickPlus;
    private MediaPlayer mediaPlayerdefaultScoreClickMinus;
    private MediaPlayer mediaPlayerdefaultScoreClickPlus;

    StartNewGameClickListener startNewGameClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        startGameButton = view.findViewById(R.id.start_game_button);
        numPlayersMeterView = view.findViewById(R.id.num_players_meter_view);
        numPlayersMeterView.setValue(1);
        defaultScoreMeterView = view.findViewById(R.id.default_score_meter_view);
        defaultScoreMeterView.setValue(50);

        numPlayersPlusButton = view.findViewById(R.id.num_players_plus_button);
        //numPlayersPlusButton.setSoundEffectsEnabled(false);
        numPlayersMinusButton = view.findViewById(R.id.num_players_minus_button);
        //numPlayersMinusButton.setSoundEffectsEnabled(false);

        mediaPlayerNumPlayersClickMinus = MediaPlayer.create(getContext(), R.raw.click_minus);
        mediaPlayerNumPlayersClickPlus = MediaPlayer.create(getContext(), R.raw.click_plus);
        mediaPlayerNumPlayersClickMinus.setVolume(1.0f,1.0f);
        mediaPlayerNumPlayersClickPlus.setVolume(1.0f,1.0f);

        defaultScorePlusButton = view.findViewById(R.id.default_score_plus_button);
        //defaultScorePlusButton.setSoundEffectsEnabled(false);
        defaultScoreMinusButton = view.findViewById(R.id.default_score_minus_button);
        //defaultScoreMinusButton.setSoundEffectsEnabled(false);

        mediaPlayerdefaultScoreClickMinus = MediaPlayer.create(getContext(), R.raw.click_minus);
        mediaPlayerdefaultScoreClickPlus = MediaPlayer.create(getContext(), R.raw.click_plus);
        mediaPlayerdefaultScoreClickMinus.setVolume(1.0f,1.0f);
        mediaPlayerdefaultScoreClickPlus.setVolume(1.0f,1.0f);

        numPlayersPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numPlayers = numPlayersMeterView.getValue();
                if (numPlayers < 6) {
                    numPlayersMeterView.setValue(numPlayers + 1);
                    mediaPlayerNumPlayersClickPlus.start();
                }
            }
        });

        numPlayersMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numPlayers = numPlayersMeterView.getValue();
                if (numPlayers > 1) {
                    numPlayersMeterView.setValue(numPlayers - 1);
                    mediaPlayerNumPlayersClickMinus.start();
                }
            }
        });

        defaultScorePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int defaultScore = defaultScoreMeterView.getValue();
                defaultScoreMeterView.setValue(defaultScore + 1);
                mediaPlayerNumPlayersClickPlus.start();
            }
        });

        defaultScoreMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int defaultScore = defaultScoreMeterView.getValue();
                if (defaultScore > 0) {
                    defaultScoreMeterView.setValue(defaultScore - 1);
                    mediaPlayerNumPlayersClickMinus.start();
                }
            }
        });

        startNewGameClickListener = (StartNewGameClickListener) getActivity();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGameClickListener.startNewGameClicked(numPlayersMeterView.getValue(), defaultScoreMeterView.getValue());
            }
        });

        return view;
    }

    public interface StartNewGameClickListener {
        void startNewGameClicked(int numPlayers, int defaultScore);
    }
}
