package com.ralphdosser.skorboye;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alexzaitsev.meternumberpicker.MeterView;
import com.ralphdosser.skorboye.Providers.ResourceProvider;
import com.ralphdosser.skorboye.views.TallyView;

public class NewGameFragment extends Fragment {

    public static final String TAG = "NewGameFragment";

    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 99;
    public static final int DEFAULT_SCORE = 50;
    public static final int MIN_NUM_PLAYERS = 1;
    public static final int MAX_NUM_PLAYERS = 6;
    public static final int COUNTER_DELAY_MILLIS = 50;

    TallyView numPlayersTallyView;
    TallyView defaultScoreTallyView;

    Button startGameButton;
    //MeterView numPlayersMeterView;
    //MeterView defaultScoreMeterView;
    //ImageButton numPlayersPlusButton;
    //ImageButton numPlayersMinusButton;
    //ImageView defaultScorePlusButton;
    //ImageButton defaultScoreMinusButton;

    private boolean autoIncrement = false;
    private boolean autoDecrement = false;

    StartNewGameClickListener startNewGameClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        //numPlayersMeterView = view.findViewById(R.id.num_players_meter_view);
        //numPlayersMeterView.setValue(MIN_NUM_PLAYERS);

        //numPlayersPlusButton = view.findViewById(R.id.num_players_plus_button);
        //numPlayersPlusButton.setSoundEffectsEnabled(false);
        //
        //numPlayersMinusButton = view.findViewById(R.id.num_players_minus_button);
        //numPlayersMinusButton.setSoundEffectsEnabled(false);


        //defaultScoreMeterView = view.findViewById(R.id.default_score_meter_view);
        //defaultScoreMeterView.setValue(DEFAULT_SCORE);
        //
        //defaultScorePlusButton = view.findViewById(R.id.default_score_plus_button);
        //defaultScorePlusButton.setSoundEffectsEnabled(false);
        //
        //defaultScoreMinusButton = view.findViewById(R.id.default_score_minus_button);
        //defaultScoreMinusButton.setSoundEffectsEnabled(false);

        numPlayersTallyView.getPlusButton().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    incrementMeterView(numPlayersTallyView.getMeterView(), MAX_NUM_PLAYERS);
                }
                return false;
            }
        });

        numPlayersTallyView.getMinusButton().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    decrementMeterViewViewer(numPlayersTallyView.getMeterView(), MIN_NUM_PLAYERS);
                }
                return false;
            }
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

        defaultScoreTallyView.getPlusButton().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP && autoIncrement) {
                    autoIncrement = false;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    incrementMeterView(defaultScoreTallyView.getMeterView(), MAX_SCORE);
                }
                return false;
            }
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

        defaultScoreTallyView.getMinusButton().setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP && autoDecrement) {
                    autoDecrement = false;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    decrementMeterViewViewer(defaultScoreTallyView.getMeterView(), MIN_SCORE);
                }
                return false;
            }

        });

        startNewGameClickListener = (StartNewGameClickListener) getActivity();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startNewGameClickListener.startNewGameClicked(numPlayersMeterView.getValue(), defaultScoreMeterView.getValue());
                startNewGameClickListener.startNewGameClicked(numPlayersTallyView.getMeterView().getValue(), defaultScoreTallyView.getMeterView().getValue());
            }
        });

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
