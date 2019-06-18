package com.group.milan.collage.collagenetworking.ChatActivity.pojo;

public class ChatPojo {
    public String username;
    public String message;
    public String user_id;
    public long time;
    public String color;

    public ChatPojo() {
        //inititljing
    }

    public ChatPojo(String username, String message, String user_id, long time,String color) {
        this.username = username;
        this.message = message;
        this.user_id = user_id;
        this.time = time;
        this.color=color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getUser_id() {
        return user_id;
    }

    public long getTime() {
        return time;
    }
}
