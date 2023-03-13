package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<IdleViewHolder> {

    private List<IdleData> mData;
    private LayoutInflater mInflater;

    MyAdapter(Context context, List<IdleData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public IdleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.idle_item, parent, false);
        return new IdleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdleViewHolder holder, int position) {
        IdleData data = mData.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
