package com.bobchen.admin.mhsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 12/31/15.
 */
public class GroupListAdapter extends BaseAdapter {

    private Context context;
    private List<MHSGroup> listGroups;

    public GroupListAdapter(Context context, List<MHSGroup> listGroups){
        this.context = context;
        this.listGroups = listGroups;
    }

    //Getters to return info about the list
    public int getCount(){
        return listGroups.size();
    }

    public Object getItem(int position){
        return listGroups.get(position);
    }

    public long getItemId(int position){
        return position;
    }



    public View getView(int position, View convertView, ViewGroup viewGroup){
        MHSGroup Group = listGroups.get(position);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_list_layout, null);
        }

        //Puts GroupName and GroupDate in corresponding text views of layout
        TextView tvGroupName = (TextView)convertView.findViewById(R.id.tvEventName);
        tvGroupName.setText(Group.groupName);

        return convertView;
    }
}
