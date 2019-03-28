package com.ralphdosser.skorboye.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ralphdosser.skorboye.R;

public class RowView extends ConstraintLayout {

    public static final String TAG = "RowView";

    TextView scoreTextView;

    public RowView(Context context) {
        super(context);
        init(context);
    }

    public RowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_row, this, true);

        scoreTextView = view.findViewById(R.id.score_textview);
    }

    public void setScore(String score) {
        if (scoreTextView != null) {
            scoreTextView.setText(score);
        } else {
            Log.e(TAG, "scoreTextView is null");
        }
    }
}
