package com.example.myapplication;

public class IdleData {

    String name;
    String imageFilename;
    double duration;
    double elapsed;

    IdleData(String name, String imageFilename, double duration, double elapsed)
    {
        this.name = name;
        this.imageFilename = imageFilename;
        this.duration = duration;
        this.elapsed = elapsed;
    }

}
