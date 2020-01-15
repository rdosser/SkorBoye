package com.ralphdosser.skorboye;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alexzaitsev.meternumberpicker.MeterView;
import com.ralphdosser.skorboye.Providers.ResourceProvider;
import com.ralphdosser.skorboye.views.TallyView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewGameFragment extends Fragment {

    public static final String TAG = "NewGameFragment";

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 99;
    private static final int DEFAULT_SCORE = 50;
    private static final int MIN_NUM_PLAYERS = 1;
    private static final int MAX_NUM_PLAYERS = 6;
    private static final int COUNTER_DELAY_MILLIS = 50;

    private TallyView numPlayersTallyView;
    private TallyView defaultScoreTallyView;
    private Button startGameButton;

    private boolean autoIncrement = false;
    private boolean autoDecrement = false;

    private StartNewGameClickListener startNewGameClickListener;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        startGameButton = view.findViewById(R.id.start_game_button);

        numPlayersTallyView = view.findViewById(R.id.num_players_tally_view);
        numPlayersTallyView.getMeterView().setValue(MIN_NUM_PLAYERS);
        numPlayersTallyView.getPlusButton().setSoundEffectsEnabled(false);
        numPlayersTallyView.getMinusButton().setSoundEffectsEnabled(false);

        defaultScoreTallyView = view.findViewById(R.id.default_score_tally_view);
        defaultScoreTallyView.getMeterView().setValue(DEFAULT_SCORE);
        defaultScoreTallyView.getPlusButton().setSoundEffectsEnabled(false);
        defaultScoreTallyView.getMinusButton().setSoundEffectsEnabled(false);

        numPlayersTallyView.getPlusButton().setOnTouchListener((view1, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                incrementMeterView(numPlayersTallyView.getMeterView(), MAX_NUM_PLAYERS);
            }
            return false;
        });

        numPlayersTallyView.getMinusButton().setOnTouchListener((view12, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                decrementMeterViewViewer(numPlayersTallyView.getMeterView(), MIN_NUM_PLAYERS);
            }
            return false;
        });

        defaultScoreTallyView.getPlusButton().setOnLongClickListener(new View.OnLongClickListener() {

            private final Handler handler = new Handler();
            private int counterDelay = COUNTER_DELAY_MILLIS;

            private Runnable counterRunnable = new Runnable() {
                @Override
                public void run() {
                    if (autoIncrement) {
                        incrementMeterView(defaultScoreTallyView.getMeterView(), MAX_SCORE);
                        handler.postDelayed(this, counterDelay);
                    }
                }

            };

            @Override
            public boolean onLongClick(View view) {
                autoIncrement = true;
                counterRunnable.run();
                return false;
            }
        });

        defaultScoreTallyView.getPlusButton().setOnTouchListener((view13, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP && autoIncrement) {
                autoIncrement = false;
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                incrementMeterView(defaultScoreTallyView.getMeterView(), MAX_SCORE);
            }
            return false;
        });

        defaultScoreTallyView.getMinusButton().setOnLongClickListener(new View.OnLongClickListener() {
            private final Handler handler = new Handler();
            private int counterDelay = 50; //millis
            private Runnable counterRunnable = new Runnable() {
                @Override
                public void run() {
                    if (autoDecrement) {
                        decrementMeterViewViewer(defaultScoreTallyView.getMeterView(), MIN_SCORE);
                        handler.postDelayed(this, counterDelay);
                    }
                }

            };
            @Override
            public boolean onLongClick(View view) {
                autoDecrement = true;
                counterRunnable.run();
                return false;
            }
        });

        defaultScoreTallyView.getMinusButton().setOnTouchListener((view14, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP && autoDecrement) {
                autoDecrement = false;
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                decrementMeterViewViewer(defaultScoreTallyView.getMeterView(), MIN_SCORE);
            }
            return false;
        });

        startNewGameClickListener = (StartNewGameClickListener) getActivity();

        startGameButton.setOnClickListener(view15 -> startNewGameClickListener.startNewGameClicked(
                numPlayersTallyView.getMeterView().getValue(),
                defaultScoreTallyView.getMeterView().getValue()));

        return view;
    }

    private void decrementMeterViewViewer(MeterView meterView, int floorValue) {
        int defaultScore = meterView.getValue();
        if (defaultScore > floorValue) {
            meterView.setValue(defaultScore - 1);
            ResourceProvider.getInstance().playClickMinus();
        }
    }

    private void incrementMeterView(MeterView meterView, int ceilValue) {
        int defaultScore = meterView.getValue();
        if (defaultScore < ceilValue) {
            meterView.setValue(defaultScore + 1);
            ResourceProvider.getInstance().playClickPlus();
        }
    }

    public interface StartNewGameClickListener {
        void startNewGameClicked(int numPlayers, int defaultScore);
    }
}
