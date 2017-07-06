package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by amusipatla on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweet> mTweets;
    Context context;
    //pass in Tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    //for each row inflate the layout and cache references into ViewHolder

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    //bind values based on position of element

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get data according to position
        Tweet tweet = mTweets.get(position);

        //populate views accordingly
        holder.tvUserName.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvScreenName.setText(" @" + tweet.user.screenName);
        holder.tvRelativeTime.setText("•" + tweet.relativeTime);



        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 3, 5))
                .into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //create ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUserName;
        public TextView tvBody;
        public TextView tvScreenName;
        public TextView tvRelativeTime;
        public ImageButton ibReply;
        public RelativeLayout rlTweet;
        public ImageButton ibFavorite;
        public ImageButton ibRetweet;
        TwitterClient client;
        Tweet tweet;

        public ViewHolder(View itemView) {
            super(itemView);

            client = TwitterApp.getRestClient();

            //perform findViewById lookups
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvRelativeTime = (TextView) itemView.findViewById(R.id.tvRelativeTime);
            ibReply = (ImageButton) itemView.findViewById(R.id.ibReply);
            rlTweet = (RelativeLayout) itemView.findViewById(R.id.rlTweet);
            ibFavorite = (ImageButton) itemView.findViewById(R.id.ibFavorite);
            ibRetweet = (ImageButton) itemView.findViewById(R.id.ibRetweet);


//            if(tweet.favorited) {
//                ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart);
//            }
//            else {
//                ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart_stroke);
//            }
//
//            if(tweet.retweeted) {
//                ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet);
//            }
//            else {
//                ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
//            }

            ibReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, ReplyActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });

            rlTweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, TweetDetails.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });

            ivProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, ProfileActivity.class);
                    i.putExtra("other_user", true);
                    i.putExtra("screen_name", mTweets.get(pos).user.screenName);
                    context.startActivity(i);
                }
            });

//            ibFavorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(tweet.favorited) {
//                        client.unfavoriteTweet(tweet, new JsonHttpResponseHandler() {
//                            @Override
//
//                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                                Log.d("TwitterClient", response.toString());
//                                Toast.makeText(context, "Favorited", Toast.LENGTH_SHORT);
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                                Log.d("TwitterClient", response.toString());
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                                Log.d("TwitterClient", responseString);
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                Log.d("TwitterClient", responseString);
//                                throwable.printStackTrace();
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//                        });
//                        tweet.favorited = false;
//                        ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart_stroke);
//                    }
//
//                    else {
//                        client.favoriteTweet(tweet, new JsonHttpResponseHandler() {
//                            @Override
//
//                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                                Log.d("TwitterClient", response.toString());
//                                Toast.makeText(context, "Unfavorited", Toast.LENGTH_SHORT);
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                                Log.d("TwitterClient", response.toString());
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                                Log.d("TwitterClient", responseString);
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                Log.d("TwitterClient", responseString);
//                                throwable.printStackTrace();
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//                        });
//                        tweet.favorited = true;
//                        ibFavorite.setBackgroundResource(R.drawable.ic_vector_heart);
//                    }
//
//                }
//            });
//
//            ibRetweet.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(tweet.retweeted) {
//                        client.unretweet(tweet, new JsonHttpResponseHandler() {
//                            @Override
//
//                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                                Log.d("TwitterClient", response.toString());
//                                Toast.makeText(context, "Favorited", Toast.LENGTH_SHORT);
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                                Log.d("TwitterClient", response.toString());
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                                Log.d("TwitterClient", responseString);
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                Log.d("TwitterClient", responseString);
//                                throwable.printStackTrace();
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//                        });
//                        tweet.retweeted = false;
//                        ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
//                    }
//
//                    else {
//                        client.retweet(tweet, new JsonHttpResponseHandler() {
//                            @Override
//
//                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                                Log.d("TwitterClient", response.toString());
//                                Toast.makeText(context, "Unfavorited", Toast.LENGTH_SHORT);
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                                Log.d("TwitterClient", response.toString());
//                            }
//
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                                Log.d("TwitterClient", responseString);
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                Log.d("TwitterClient", responseString);
//                                throwable.printStackTrace();
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                                Log.d("TwitterClient", errorResponse.toString());
//                                throwable.printStackTrace();            }
//                        });
//                        tweet.retweeted = true;
//                        ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet);
//                    }
//
//                }
//            });

        }

    }
}
