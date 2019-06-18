package com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("f_a_id")
    @Expose
    private String fAId;
    @SerializedName("f_q_id")
    @Expose
    private String fQId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("publish_on")
    @Expose
    private String publishOn;

    public String getFAId() {
        return fAId;
    }

    public void setFAId(String fAId) {
        this.fAId = fAId;
    }

    public String getFQId() {
        return fQId;
    }

    public void setFQId(String fQId) {
        this.fQId = fQId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public void setPublishOn(String publishOn) {
        this.publishOn = publishOn;
    }

}