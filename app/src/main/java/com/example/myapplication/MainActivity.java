package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    ArrayList<IdleData> dataList;

    int tasks = 0;
    int tasksComplete = 0;
    TextView tasksCompleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksCompleteText = findViewById(R.id.tasksComplete);

        dataList = new ArrayList<IdleData>();

        RecyclerView recyclerObj = findViewById(R.id.recycler);
        recyclerObj.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, dataList);
        recyclerObj.setAdapter(adapter);

        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    mainThreadHandler.post(() -> {
                        for (int i=dataList.size()-1;i>=0;i--) {
                            IdleData data = dataList.get(i);
                            int oldDisplayedTime = (int) data.elapsed;
                            data.elapsed += 0.1;
                            if (data.elapsed > data.duration) {
                                dataList.remove(i);
                                tasksComplete++;
                                if (!recyclerObj.isComputingLayout())
                                    adapter.notifyItemRemoved(i);
                            }
                            else if ((int) data.elapsed != oldDisplayedTime)
                                if (!recyclerObj.isComputingLayout())
                                    adapter.notifyItemChanged(i);
                        }
                        tasksCompleteText.setText(String.valueOf(tasksComplete));
                    });
                }
            }, 0, 100);
    }

    public void addBtn(View view)
    {
        tasks++;
        dataList.add(dataList.size(), new IdleData("Task " + tasks, "", (int) (Math.random()*15)+5, 0));
        this.adapter.notifyItemInserted(this.adapter.getItemCount());
    }

}