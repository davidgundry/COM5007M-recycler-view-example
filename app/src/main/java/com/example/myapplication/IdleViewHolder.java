package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class IdleViewHolder extends RecyclerView.ViewHolder
{
    TextView nameText;
    TextView timeText;

    IdleViewHolder(View v) {
        super(v);
        nameText = itemView.findViewById(R.id.nameText);
        timeText = itemView.findViewById(R.id.timeText);
    }

    void setData(IdleData data)
    {
        nameText.setText(data.name);
        timeText.setText((int) data.elapsed  + "/" + (int)  data.duration);
    }

}