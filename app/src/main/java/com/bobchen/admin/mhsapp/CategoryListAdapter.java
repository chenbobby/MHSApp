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
public class CategoryListAdapter extends BaseAdapter {

    private Context context;
    private List<Category> listCategories;

    public CategoryListAdapter(Context context, List<Category> listCategories){
        this.context = context;
        this.listCategories = listCategories;
    }

    //Getters to return info about the list
    public int getCount(){
        return listCategories.size();
    }

    public Object getItem(int position){
        return listCategories.get(position);
    }

    public long getItemId(int position){
        return position;
    }



    public View getView(int position, View convertView, ViewGroup viewGroup){
        Category category = listCategories.get(position);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_list_layout, null);
        }

        //Puts category string in the text views
        TextView tvCategory = (TextView)convertView.findViewById(R.id.tvCategory);
        tvCategory.setText(category.categoryName);

        return convertView;
    }

}

