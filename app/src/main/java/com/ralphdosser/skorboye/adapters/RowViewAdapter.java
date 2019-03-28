package com.ralphdosser.skorboye.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ralphdosser.skorboye.R;

import java.util.List;

public class RowViewAdapter extends RecyclerView.Adapter<RowViewAdapter.RowViewHolder> {

    private List<String> playerNames;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class RowViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        EditText nameTextView;
        TextView scoreTextView;
        ImageButton plusButton;
        ImageButton minusButton;

        int currentScore;

        RowViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            nameTextView = itemView.findViewById(R.id.name_textview);
            scoreTextView = itemView.findViewById(R.id.score_textview);
            plusButton = itemView.findViewById(R.id.score_plus_button);
            minusButton = itemView.findViewById(R.id.score_minus_button);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RowViewAdapter(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RowViewAdapter() {
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_row, parent, false);
        RowViewHolder vh = new RowViewHolder(constraintLayout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RowViewHolder holder, int position) {
        holder.nameTextView.setText(playerNames.get(position));
        holder.currentScore = 50;
        holder.scoreTextView.setText(String.valueOf(holder.currentScore));

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.currentScore++;
                holder.scoreTextView.setText(String.valueOf(holder.currentScore));
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.currentScore > 0) {
                    holder.currentScore--;
                    holder.scoreTextView.setText(String.valueOf(holder.currentScore));
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return playerNames.size();
    }
}
