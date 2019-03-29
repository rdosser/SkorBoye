package com.ralphdosser.skorboye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NewGameFragment.StartNewGameClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewGameFragment newGameFragment = new NewGameFragment();
        showFragment(newGameFragment, false);

        // TODO - add a hamburger menu
        // - new game
        // - save game

    }

    private void showFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    @Override
    public void startNewGameClicked(int numPlayers) {
        PlayFragment playFragment = PlayFragment.newInstance(numPlayers);
        showFragment(playFragment, false);
    }
}
