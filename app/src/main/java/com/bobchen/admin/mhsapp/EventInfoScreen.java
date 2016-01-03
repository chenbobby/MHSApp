package com.bobchen.admin.mhsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class EventInfoScreen extends AppCompatActivity {

    TextView tvTeamRank, tvMHSTeam, tvVS, tvOtherTeam, tvEventDate, tvEventTime, tvEventLocation, tvEventAdmission;

    Event currentEvent = new Event(null, null, null, null, null, null, null);
    String VS = null;
    String OtherTeam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info_screen);

        //Receive parcel Event
        Bundle data = getIntent().getExtras();
        if (data != null){
            currentEvent = (Event) data.getParcelable("Picked Event");

            //Extracts the VS and OtherTeam from the eventName string
            VS = currentEvent.eventName.substring(0, currentEvent.eventName.indexOf(" "));
            OtherTeam = currentEvent.eventName.substring(currentEvent.eventName.indexOf(" ") + 1);
        }


        //Set up UI Text Views
        tvTeamRank = (TextView)findViewById(R.id.tvTeamRank);
        tvMHSTeam = (TextView)findViewById(R.id.tvMHSTeam);
        tvVS = (TextView)findViewById(R.id.tvVS);
        tvOtherTeam = (TextView)findViewById(R.id.tvOtherTeam);
        tvEventDate = (TextView)findViewById(R.id.tvEventDate);
        tvEventTime = (TextView)findViewById(R.id.tvEventTime);
        tvEventLocation = (TextView)findViewById(R.id.tvEventLocation);
        tvEventAdmission = (TextView)findViewById(R.id.tvEventAdmission);

        displayEventInfo();

    }

    private void displayEventInfo(){
        tvTeamRank.setText(currentEvent.teamRank);
        tvMHSTeam.setText(currentEvent.MHSTeam);
        tvVS.setText(VS, TextView.BufferType.EDITABLE);
        tvOtherTeam.setText(OtherTeam);
        tvEventDate.setText(currentEvent.eventDate);
        tvEventTime.setText(currentEvent.eventTime);
        tvEventLocation.setText(currentEvent.eventLocation);
        tvEventAdmission.setText(currentEvent.eventAdmission);
    }
}
