package com.ralphdosser.skorboye.views;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.alexzaitsev.meternumberpicker.MeterView;
import com.ralphdosser.skorboye.R;

public class TallyView extends ConstraintLayout {

    public TallyView(Context context) {
        super(context);
    }

    private MeterView meterView;
    private ImageButton plusButton;
    private ImageButton minusButton;

    public TallyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_tally, this, true);

        meterView = view.findViewById(R.id.meter_view);
        plusButton = view.findViewById(R.id.plus_button);
        minusButton = view.findViewById(R.id.minus_button);

    }

    public MeterView getMeterView() {
        return meterView;
    }

    public void setMeterView(MeterView meterView) {
        this.meterView = meterView;
    }

    public ImageButton getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(ImageButton plusButton) {
        this.plusButton = plusButton;
    }

    public ImageButton getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(ImageButton minusButton) {
        this.minusButton = minusButton;
    }
}
