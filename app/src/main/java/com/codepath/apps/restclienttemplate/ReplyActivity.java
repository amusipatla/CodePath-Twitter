package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReplyActivity extends AppCompatActivity {

    EditText etReplyTweet;
    String tweetText;
    TwitterClient client;
    Tweet repliedTweet;
    String screenName;
    String uid;
    private TextView tvReplyCounter;

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvReplyCounter.setText(String.valueOf(140- s.length()) + "/140");
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        client = TwitterApp.getRestClient();
        etReplyTweet = (EditText) findViewById(R.id.etReplyTweet);
        tvReplyCounter = (TextView) findViewById(R.id.tvReplyCounter);

        repliedTweet = getIntent().getParcelableExtra("tweet");
        screenName = repliedTweet.user.screenName;
        uid = repliedTweet.sUid;

        etReplyTweet.setText("@" + screenName);


        etReplyTweet.addTextChangedListener(mTextEditorWatcher);

        getSupportActionBar().setTitle("New Tweet");
    }


    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        tweetText = etReplyTweet.getText().toString();

        client.replyTweet(tweetText, uid, new JsonHttpResponseHandler() {
            @Override

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());

                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("tweet", tweet);
                    setResult(1, i);
                    finish();
//                    startActivity(i); // brings up the second activity
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("TwitterClient", responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();            }
        });

    }
}
