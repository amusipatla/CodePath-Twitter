package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetails extends AppCompatActivity {

    Tweet tweet;
    ImageView ivPic;
    TextView tvUsername;
    TextView tvHandle;
    TextView tvTweet;
    ImageButton ibReply;
    ImageButton ibFavorite;
    ImageButton ibRetweet;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        client = TwitterApp.getRestClient();

        tweet = getIntent().getParcelableExtra("tweet");

        ivPic = (ImageView) findViewById(R.id.ivDetailPic);
        tvHandle = (TextView) findViewById(R.id.tvDetailHandle);
        tvUsername =  (TextView) findViewById(R.id.tvDetailName);
        tvTweet = (TextView) findViewById(R.id.tvDetailTweet);
        ibReply = (ImageButton) findViewById(R.id.ibDetailReply);
        ibFavorite = (ImageButton) findViewById(R.id.ibDetailFavorite);
        ibRetweet = (ImageButton) findViewById(R.id.ibDetailRetweet);

        if(tweet.favorited) {
            ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart);
        }
        else {
            ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart_stroke);
        }

        if(tweet.retweeted) {
            ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet);
        }
        else {
            ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
        }

        tvHandle.setText(tweet.user.screenName);
        tvUsername.setText(tweet.user.name);
        tvTweet.setText(tweet.body);


        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, 3, 5))
                .into(ivPic);

        ibReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TweetDetails.this, ReplyActivity.class);
                i.putExtra("tweet", tweet);
                startActivity(i);
            }
        });

        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tweet.favorited) {
                    client.unfavoriteTweet(tweet, new JsonHttpResponseHandler() {
                        @Override

                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            Toast.makeText(TweetDetails.this, "Favorited", Toast.LENGTH_SHORT);
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
                    tweet.favorited = false;
                    ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart_stroke);
                }

                else {
                    client.favoriteTweet(tweet, new JsonHttpResponseHandler() {
                        @Override

                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            Toast.makeText(TweetDetails.this, "Unfavorited", Toast.LENGTH_SHORT);
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
                    tweet.favorited = true;
                    ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart);
                }

            }
        });

        ibRetweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tweet.retweeted) {
                    client.unretweet(tweet, new JsonHttpResponseHandler() {
                        @Override

                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            Toast.makeText(TweetDetails.this, "Favorited", Toast.LENGTH_SHORT);
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
                    tweet.retweeted = false;
                    ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
                }

                else {
                    client.retweet(tweet, new JsonHttpResponseHandler() {
                        @Override

                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            Toast.makeText(TweetDetails.this, "Unfavorited", Toast.LENGTH_SHORT);
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
                    tweet.retweeted = true;
                    ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet);
                }

            }
        });

    }
}
