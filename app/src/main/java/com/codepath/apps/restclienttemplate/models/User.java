package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amusipatla on 6/26/17.
 */

public class User {

    //all attributes of user
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    //deserialize json
    public static User fromJSON(JSONObject json) throws JSONException{
        User user = new User();
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");
        return user;
    }
}
