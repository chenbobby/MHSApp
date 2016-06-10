package com.bobchen.admin.mhsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class singleListScreen extends AppCompatActivity {

    MHSGroup pickedMHSGroup;
    Category pickedCategory;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list_screen);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Receive Parcel
        Bundle data = getIntent().getExtras();
        if (data != null){
            pickedMHSGroup = (MHSGroup) data.getParcelable("Picked Group");
            pickedCategory = (Category) data.getParcelable("Picked Category");
        }




        if (pickedMHSGroup != null && pickedCategory == null) {

            setTitle(pickedMHSGroup.groupName);
            final List<Event> listOfEvents = new ArrayList<Event>();

            //Retrieves all data from chosen group
            ParseQuery<ParseObject> query = ParseQuery.getQuery(pickedMHSGroup.groupName.replaceAll("\\s", ""));
            query.orderByAscending("eventDate");

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> receivedList, ParseException e) {

                    //if there are no errors, then all the retrieved data will be saved in listOfEvents
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
                    ListView singleList = (ListView) findViewById(R.id.lvSingleList);
                    EventListAdapter theAdapter = new EventListAdapter(singleListScreen.this, listOfEvents);
                    singleList.setAdapter(theAdapter);

                    progressDialog.dismiss();

                    singleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            String eventPicked = "You selected " + listOfEvents.get(position).eventName;

                            Toast.makeText(singleListScreen.this, eventPicked, Toast.LENGTH_SHORT).show();

                            String MHSTeam = listOfEvents.get(position).MHSTeam;
                            String teamRank = listOfEvents.get(position).teamRank;
                            String eventName = listOfEvents.get(position).eventName;
                            String eventDate = listOfEvents.get(position).eventDate;
                            String eventTime = listOfEvents.get(position).eventTime;
                            String eventLocation = listOfEvents.get(position).eventLocation;
                            String eventAdmission = listOfEvents.get(position).eventAdmission;

                            Event pickedEvent = new Event(MHSTeam, teamRank, eventName, eventDate, eventTime, eventLocation, eventAdmission);

                            Intent gotoEventInfo = new Intent();
                            gotoEventInfo.putExtra("Picked Event", pickedEvent);
                            gotoEventInfo.setClass(singleListScreen.this, art_event_info_screen.class);
                            startActivity(gotoEventInfo);

                        }
                    });

                }
            });

        } else if (pickedMHSGroup == null && pickedCategory != null){


            setTitle(pickedCategory.categoryName);

            final List<MHSGroup> listOfGroups = new ArrayList<MHSGroup>();

            //Retrieves all data from chosen group
            ParseQuery<ParseObject> query = ParseQuery.getQuery(pickedCategory.categoryName.replaceAll("\\s", ""));
            query.orderByAscending("eventDate"); //TODO: Check this line; it should be deleted.

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> receivedList, ParseException e) {

                    //if there are no errors, then all the retrieved data will be saved in listOfGroups
                    if (e == null) {
                        Log.d("Groups", "Retrieved " + receivedList.size() + " group(s)");

                        for (ParseObject groups : receivedList) {
                            String groupName = groups.getString("Name");
                            int teams = groups.getInt("Teams");

                            MHSGroup group = new MHSGroup(groupName, teams);
                            listOfGroups.add(group);
                            Log.d("Retrieved Group", group.groupName);
                        }
                    } else {
                        Log.d("Groups", "Error:" + e.getMessage());
                    }

                    //Intermediate check to see if data is still saved in listOfEvents
                    for (MHSGroup group : listOfGroups) {
                        Log.d("Saved Group", group.groupName);
                    }

                    //Find the List View, create an adapter, and assign the adapter and ClickListener to the ListView
                    ListView singleList = (ListView) findViewById(R.id.lvSingleList);
                    GroupListAdapter theAdapter = new GroupListAdapter(singleListScreen.this, listOfGroups);
                    singleList.setAdapter(theAdapter);

                    progressDialog.dismiss();

                    singleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            String groupPicked = "You selected " + listOfGroups.get(position).groupName;

                            Toast.makeText(singleListScreen.this, groupPicked, Toast.LENGTH_SHORT).show();

                            String groupName = listOfGroups.get(position).groupName;
                            int teams = listOfGroups.get(position).teams;

                            MHSGroup pickedGroup = new MHSGroup(groupName, teams);

                            switch (pickedGroup.teams) {
                                case 1:
                                    Intent gotoGroupList = new Intent();
                                    gotoGroupList.putExtra("Picked Group", pickedGroup);
                                    gotoGroupList.setClass(singleListScreen.this, singleListScreen.class);
                                    startActivity(gotoGroupList);
                                    break;

                                case 2:
                                    Intent gotoGroup2Tabs = new Intent();
                                    gotoGroup2Tabs.putExtra("Picked Group", pickedGroup);
                                    gotoGroup2Tabs.setClass(singleListScreen.this, Tab2.class);
                                    startActivity(gotoGroup2Tabs);
                                    break;

                                case 3:
                                    Intent gotoGroup3Tabs = new Intent();
                                    gotoGroup3Tabs.putExtra("Picked Group", pickedGroup);
                                    gotoGroup3Tabs.setClass(singleListScreen.this, Tab3.class);
                                    startActivity(gotoGroup3Tabs);
                                    break;
                            }
                        }
                    });

                }
            });


        } else{
            Log.d("Single List Screen", "Did not receive a Category or MHSGroup in parcel");
        }
    }
}
