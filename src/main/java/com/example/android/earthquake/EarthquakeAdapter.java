package com.example.android.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    //Constructs a new EarthquakeAdapter

    public EarthquakeAdapter (Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    //Return the formatted date string from a Date object
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    //Return the formatted date string from a Date Object
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    //Return a formatted magnitude string showing 1 decimal place
    //from a decimal magnitude value
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    //Define a static final String constant
    //Because will use split method at the position where the text "of" occurs
    private static final String LOCATION_SEPARATOR = "of";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if there is an existing list item view (ie. convertView) that we can reuse,
        //Otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        //Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        //Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        //Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);


        //Get the original location String from the Earthquake Object
        String originalLocation = currentEarthquake.getLocation();
        //If the original location string contains a
        //primary location (eg. Cairo, Egypt) and location offset (eg. 5km N of that city)
        //then store the primary location separately from the location offset in 2 Strings
        //so they can be displayed in 2 TextViews
        String primaryLocation;
        String locationOffset;

        //Check if the original location String contains the "of" text
        //If there is, split it
        if (originalLocation.contains(LOCATION_SEPARATOR)){
            String[]parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else {
            //if not, use "Near the" text as the location offset
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //Find 2 separate Strings in the 2 TextViews in the list item layout and display them.
        TextView primaryLocationView = (TextView)listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        //Create a new Date object from the unix time.
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        //Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        //Format the date String
        String formattedDate = formatDate(dateObject);
        //Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        //Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        //Format the time String
        String formattedTime = formatTime(dateObject);
        //Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        //Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}



