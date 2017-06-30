package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.models.Tweet;

public class tweetDetails extends AppCompatActivity {

    Tweet tweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tweet = getIntent().getParcelableExtra("tweet");
    }
}
