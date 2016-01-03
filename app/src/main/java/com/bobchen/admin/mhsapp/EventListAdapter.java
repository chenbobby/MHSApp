package com.bobchen.admin.mhsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 12/30/15.
 */
public class EventListAdapter extends BaseAdapter{

    private Context context;
    private List<Event> listEvents;

    public EventListAdapter(Context context, List<Event> listEvents){
        this.context = context;
        this.listEvents = listEvents;
    }

    //Getters to return info about the list
    public int getCount(){
        return listEvents.size();
    }

    public Object getItem(int position){
        return listEvents.get(position);
    }

    public long getItemId(int position){
        return position;
    }



    public View getView(int position, View convertView, ViewGroup viewGroup){
        Event event = listEvents.get(position);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_list_layout, null);
        }

        //Puts eventName and eventDate in corresponding text views of layout
        TextView tvEventName = (TextView)convertView.findViewById(R.id.tvEventName);
        tvEventName.setText(event.eventName);

        TextView tvEventDate = (TextView)convertView.findViewById(R.id.tvEventDate);
        tvEventDate.setText(event.eventDate);


        return convertView;
    }
}

