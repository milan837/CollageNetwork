package com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForumAnswerListResponse {
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }
}
