package com.ralphdosser.skorboye;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alexzaitsev.meternumberpicker.MeterView;

public class NewGameFragment extends Fragment {

    public static final String TAG = "NewGameFragment";

    Button startGameButton;
    MeterView numPlayersMeterView;

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

        startNewGameClickListener = (StartNewGameClickListener) getActivity();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGameClickListener.startNewGameClicked(numPlayersMeterView.getValue());
            }
        });

        return view;
    }

    public interface StartNewGameClickListener {
        void startNewGameClicked(int numPlayers);
    }
}
