package com.bobchen.admin.mhsapp;

import com.parse.Parse;

/**
 * Created by admin on 12/31/15.
 * This Class initializes Parse only once in the app's lifecycle.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Parse.initialize(this,
                "todrzejb8TJEbYGssO5XxBypKRtD0RlPsXisqIlY",
                "yz7JHkzPaqqn5FKWdfZ6BQ0qbqHEZIqrbTuHORum");
    }

}
