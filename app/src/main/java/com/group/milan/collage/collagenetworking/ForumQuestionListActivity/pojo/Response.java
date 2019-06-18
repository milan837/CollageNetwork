package com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("f_q_id")
    @Expose
    private String fQId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("publish_on")
    @Expose
    private String publishOn;

    public String getFQId() {
        return fQId;
    }

    public void setFQId(String fQId) {
        this.fQId = fQId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public void setPublishOn(String publishOn) {
        this.publishOn = publishOn;
    }

}