package com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("total_members")
    @Expose
    private Integer totalMembers;
    @SerializedName("publish_on")
    @Expose
    private String publishOn;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public void setPublishOn(String publishOn) {
        this.publishOn = publishOn;
    }

}