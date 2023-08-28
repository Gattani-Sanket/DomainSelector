package com.example.domainselector;

import java.sql.Timestamp;

public class AddAnswer {
    String answer,user_id;
    String commenttime;



    public AddAnswer()
    {

    }

    public AddAnswer(String answer, String user_id,String commenttime) {
        this.answer = answer;
        this.user_id = user_id;
        this.commenttime=commenttime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }
}
