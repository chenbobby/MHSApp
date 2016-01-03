package com.bobchen.admin.mhsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Connect and Initialize Parse
        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this);

        final List<Category> listOfCategories = new ArrayList<Category>();


        //Retrieves all data from Categories class
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Categories");
        query.orderByAscending("Rank");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> receivedList, ParseException e) {

                //if there are no errors, then all the retrieved data will be save in listOfCategories
                if (e == null) {
                    Log.d("Categories", "Retrieved " + receivedList.size() + " items");

                    for (ParseObject category : receivedList) {
                        String categoryName = category.getString("Name");
                        String nameNS = category.getString("nameNS");
                        int rank = category.getInt("Rank");

                        Category newcategory = new Category(categoryName, nameNS, rank);
                        listOfCategories.add(newcategory);
                        Log.d("Retrieved Category", categoryName);
                    }
                } else {
                    Log.d("Categories", "Error:" + e.getMessage());
                }

                //Intermediate check to see if data is still saved in listOfEvents
                for (Category category : listOfCategories) {
                    Log.d("Saved Categoryt", category.categoryName);
                }


                //Find the List View, create an adapter, and assign the adapter and ClickListener to the ListView
                ListView categoryList = (ListView) findViewById(R.id.lvCategories);
                CategoryListAdapter theAdapter = new CategoryListAdapter(MainActivity.this, listOfCategories);
                categoryList.setAdapter(theAdapter);

                progressDialog.dismiss();

                categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                        String categoryName = listOfCategories.get(position).categoryName;
                        String nameNS = listOfCategories.get(position).nameNS;
                        int rank = listOfCategories.get(position).rank;

                        Category pickedCategory = new Category(categoryName, nameNS, rank);


                        Intent gotoCategoryList = new Intent();
                        gotoCategoryList.putExtra("Picked Category", pickedCategory);
                        gotoCategoryList.setClass(MainActivity.this, singleListScreen.class);
                        startActivity(gotoCategoryList);


                    }
                });


            }
        });


    }
}
