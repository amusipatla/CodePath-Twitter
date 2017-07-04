package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by amusipatla on 7/3/17.
 */

public class TweetsListFragment extends Fragment {

    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;

    //inflation happens inside onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

        //find recycler view
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);

        // init arraylist (data source)
        tweets = new ArrayList<>();

        //construct the adapter from this data source
        tweetAdapter = new TweetAdapter(tweets);

        //RecyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));

        //set adapter
        rvTweets.setAdapter(tweetAdapter);
        return v;
    }

    public void addItems(JSONArray response) {
        try {
            for(int i = 0; i < response.length(); i++) {
                //convert each object to tweet model
                //add that Tweet model to data source
                //notify adapter that we've added an item
                Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(i - 1);

        }} catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
