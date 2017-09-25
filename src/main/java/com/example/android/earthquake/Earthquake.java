package com.example.android.earthquake;

//An object contains information of a single earthquake.

public class Earthquake{
    //Magnitude of the earthquake.
    private double mMagnitude;

    //Location of the earthquake
    private String mLocation;

    //Time of the earthquake
    private long mTimeInMilliseconds;

    //Construct a new Earthquake object

    public Earthquake(double magnitude, String location, long timeInMilliseconds){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    //Get the magnitude of the earthquake
    public double getMagnitude(){return mMagnitude;}

    //Get the location of the earthquake
    public String getLocation(){return mLocation;}

    //Get the time of the earthquake
    public long getTimeInMilliseconds (){return mTimeInMilliseconds;}
}