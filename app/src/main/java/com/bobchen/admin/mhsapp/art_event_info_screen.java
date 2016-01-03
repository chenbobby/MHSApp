package com.bobchen.admin.mhsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class art_event_info_screen extends AppCompatActivity {

    TextView tvMHSTeam, tvEventName, tvEventDate, tvEventTime, tvEventLocation, tvEventAdmission;

    Event currentEvent = new Event(null, null, null, null, null, null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_event_info_screen);

        //Receive parcel Event
        Bundle data = getIntent().getExtras();
        if (data!= null){
            currentEvent = (Event) data.getParcelable("Picked Event");
        }

        //Set up UI Text Views
        tvMHSTeam = (TextView)findViewById(R.id.tvMHSTeam);
        tvEventName = (TextView)findViewById(R.id.tvEventName);
        tvEventDate = (TextView)findViewById(R.id.tvEventDate);
        tvEventTime = (TextView)findViewById(R.id.tvEventTime);
        tvEventLocation = (TextView)findViewById(R.id.tvEventLocation);
        tvEventAdmission = (TextView)findViewById(R.id.tvEventAdmission);

        displayEventInfo();
    }

    private void displayEventInfo(){
        tvMHSTeam.setText(currentEvent.MHSTeam);
        tvEventName.setText(currentEvent.eventName);
        tvEventDate.setText(currentEvent.eventDate);
        tvEventTime.setText(currentEvent.eventTime);
        tvEventLocation.setText(currentEvent.eventLocation);
        tvEventAdmission.setText(currentEvent.eventAdmission);
    }
}
