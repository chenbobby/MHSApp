package com.bobchen.admin.mhsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FreshmenTab extends Activity {


    String pickedGroupName;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_tab);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        pickedGroupName = ((Tab3)getParent()).getTeamName();

        final List<Event> listOfEvents = new ArrayList<Event>();


        //Retrieves all data from BoysBasketballFreshmen
        ParseQuery<ParseObject> query = ParseQuery.getQuery(pickedGroupName.replaceAll("\\s", ""));
        query.whereEqualTo("teamRank", "Freshmen");
        query.orderByAscending("eventDate");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> receivedList, ParseException e) {

                //if there are no errors, then all the retrieved data will be save in listOfEvents
                if (e == null) {
                    Log.d("Events", "Retrieved " + receivedList.size() + " event(s)");

                    for (ParseObject events : receivedList) {
                        String MHSTeam = events.getString("MHSTeam");
                        String teamRank = events.getString("teamRank");
                        String eventName = events.getString("eventName");
                        String eventDate = events.getString("eventDate");
                        String eventTime = events.getString("eventTime");
                        String eventLocation = events.getString("eventLocation");
                        String eventAdmission = events.getString("eventAdmission");
                        Event event = new Event(MHSTeam, teamRank, eventName, eventDate, eventTime, eventLocation, eventAdmission);
                        listOfEvents.add(event);
                        Log.d("Retrieved Event", eventName);
                    }
                } else {
                    Log.d("Events", "Error:" + e.getMessage());
                }

                //Intermediate check to see if data is still saved in listOfEvents
                for (Event event : listOfEvents) {
                    Log.d("Saved Event", event.eventName);
                }


                //Find the List View, create an adapter, and assign the adapter and ClickListener to the ListView
                ListView eventListFreshmen = (ListView) findViewById(R.id.lvFreshmen);
                EventListAdapter theAdapter = new EventListAdapter(FreshmenTab.this, listOfEvents);
                eventListFreshmen.setAdapter(theAdapter);

                progressDialog.dismiss();

                eventListFreshmen.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        String tvEventPicked = "You selected " + listOfEvents.get(position).eventName;

                        Toast.makeText(FreshmenTab.this, tvEventPicked, Toast.LENGTH_SHORT).show();

                        String MHSTeam = listOfEvents.get(position).MHSTeam;
                        String teamRank = listOfEvents.get(position).teamRank;
                        String eventName = listOfEvents.get(position).eventName;
                        String eventDate= listOfEvents.get(position).eventDate;
                        String eventTime = listOfEvents.get(position).eventTime;
                        String eventLocation = listOfEvents.get(position).eventLocation;
                        String eventAdmission = listOfEvents.get(position).eventAdmission;

                        Event pickedEvent = new Event(MHSTeam, teamRank, eventName, eventDate, eventTime, eventLocation, eventAdmission);

                        Intent gotoEventInfo = new Intent();
                        gotoEventInfo.putExtra("Picked Event", pickedEvent);
                        gotoEventInfo.setClass(FreshmenTab.this, EventInfoScreen.class);
                        startActivity(gotoEventInfo);

                    }
                });


            }
        });
    }
}
