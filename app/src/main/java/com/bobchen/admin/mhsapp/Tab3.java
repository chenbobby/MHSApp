package com.bobchen.admin.mhsapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Tab3 extends TabActivity {

    MHSGroup pickedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        //Receive Parcel
        Bundle data = getIntent().getExtras();
        if (data != null){
            pickedGroup = (MHSGroup) data.getParcelable("Picked Group");
        }


        TextView TeamName = (TextView)findViewById(R.id.tvTeamName);
        TeamName.setText(pickedGroup.groupName);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec freshmenTab = tabHost.newTabSpec("freshmenTab");
        TabHost.TabSpec JVTab = tabHost.newTabSpec("JVTab");
        TabHost.TabSpec varsityTab = tabHost.newTabSpec("varsityTab");

        freshmenTab.setIndicator("Freshmen");
        freshmenTab.setContent(new Intent(this, FreshmenTab.class));

        JVTab.setIndicator("JV");
        JVTab.setContent(new Intent(this, JVTab.class));

        varsityTab.setIndicator("Varsity");
        varsityTab.setContent(new Intent(this, VarsityTab.class));

        tabHost.addTab(freshmenTab);
        tabHost.addTab(JVTab);
        tabHost.addTab(varsityTab);

    }

    public String getTeamName(){
        return pickedGroup.groupName;
    }
}
