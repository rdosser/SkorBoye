package com.ralphdosser.skorboye.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ralphdosser.skorboye.R;
import com.ralphdosser.skorboye.models.PlayerScore;

import java.util.List;

public class RowViewAdapter extends RecyclerView.Adapter<RowViewAdapter.RowViewHolder> {

    private List<PlayerScore> playerScores;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class RowViewHolder extends RecyclerView.ViewHolder {
        PlayerScore playerScore;
        View itemView;
        EditText nameTextView;
        TextView scoreTextView;
        ImageButton plusButton;
        ImageButton minusButton;
        ConstraintLayout parentLayout;
        Context context;
        int deadColorResource;
        int aliveColorResource;

        private MediaPlayer mediaPlayerClickMinus;
        private MediaPlayer mediaPlayerClickPlus;
        private MediaPlayer mediaPlayerFail;

        RowViewHolder(View itemView, Context context) {
            super(itemView);
            this.itemView = itemView;

            nameTextView = itemView.findViewById(R.id.name_textview);
            scoreTextView = itemView.findViewById(R.id.score_textview);
            plusButton = itemView.findViewById(R.id.score_plus_button);
            minusButton = itemView.findViewById(R.id.score_minus_button);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            this.context = context;

            mediaPlayerClickMinus = MediaPlayer.create(context, R.raw.click_minus);
            mediaPlayerClickPlus = MediaPlayer.create(context, R.raw.click_plus);
            mediaPlayerFail = MediaPlayer.create(context, R.raw.sad_trumpet);

            deadColorResource = context.getColor(R.color.transparentBlack);
            aliveColorResource = context.getColor(R.color.white);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RowViewAdapter(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_row, parent, false);
        RowViewHolder rowViewHolder = new RowViewHolder(constraintLayout, parent.getContext());
        return rowViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RowViewHolder holder, int position) {
        holder.playerScore = playerScores.get(position);
        holder.nameTextView.setText(holder.playerScore.getName());
        holder.scoreTextView.setText(String.valueOf(holder.playerScore.getScore()));

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.playerScore.getScore() == 0) {
                    holder.parentLayout.setBackgroundColor(holder.aliveColorResource);
                }
                holder.mediaPlayerClickPlus.start();
                holder.playerScore.setScore(holder.playerScore.getScore() + 1);
                holder.scoreTextView.setText(String.valueOf(holder.playerScore.getScore()));
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.playerScore.getScore() > 0) {
                    holder.mediaPlayerClickMinus.start();
                    holder.playerScore.setScore(holder.playerScore.getScore() - 1);
                    holder.scoreTextView.setText(String.valueOf(holder.playerScore.getScore()));
                    if (holder.playerScore.getScore() == 0) {
                        holder.mediaPlayerFail.start();
                        holder.parentLayout.setBackgroundColor(holder.deadColorResource);
                    }
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return playerScores.size();
    }
}
