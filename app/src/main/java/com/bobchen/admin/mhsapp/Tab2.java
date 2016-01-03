package com.bobchen.admin.mhsapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Tab2 extends TabActivity {

    MHSGroup pickedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        //Receive Parcel
        Bundle data = getIntent().getExtras();
        if (data != null){
            pickedGroup = (MHSGroup) data.getParcelable("Picked Group");
        }


        TextView TeamName = (TextView)findViewById(R.id.tvTeamName);
        TeamName.setText(pickedGroup.groupName);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec JVTab = tabHost.newTabSpec("JVTab");
        TabHost.TabSpec varsityTab = tabHost.newTabSpec("varsityTab");


        JVTab.setIndicator("JV");
        JVTab.setContent(new Intent(this, JVTab.class));

        varsityTab.setIndicator("Varsity");
        varsityTab.setContent(new Intent(this, VarsityTab.class));

        tabHost.addTab(JVTab);
        tabHost.addTab(varsityTab);

    }

    public String getTeamName(){
        return pickedGroup.groupName;
    }
}
