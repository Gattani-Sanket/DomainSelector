package com.example.domainselector;

import java.sql.Timestamp;

public class PostView {
    String title,description;
    String  user_id;
    String posttime;

    public PostView(String title, String description,  String user_id, String time) {
        this.title = title;
        this.description = description;
        this.posttime= time;
        this.user_id = user_id;
    }

    public PostView()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    //public void setPosttime(Timestamp timestamp) {
       // this.timestamp = timestamp;
    //}
}
