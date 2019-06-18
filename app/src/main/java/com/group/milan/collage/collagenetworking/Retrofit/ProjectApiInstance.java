package com.group.milan.collage.collagenetworking.Retrofit;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectApiInstance {

    public static String ip="192.168.43.63";
    public static String ip1="anill.xyz";


    public static ProjectApiCall getAuthApiInstance(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        ProjectApiCall retrofit=new Retrofit.Builder()
                .baseUrl("http://"+ip+"/collage/api/public/authentication.php/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProjectApiCall.class);
        return retrofit;
    }

    public static ProjectApiCall getForumApiInstance(){
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        ProjectApiCall retrofit=new Retrofit.Builder()
                .baseUrl("http://"+ip+"/collage/api/public/forum.php/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProjectApiCall.class);
        return retrofit;
    }


    public static ProjectApiCall getProjectApiInstance(){
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        ProjectApiCall retrofit=new Retrofit.Builder()
                .baseUrl("http://"+ip+"/collage/api/public/project.php/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProjectApiCall.class);
        return retrofit;
    }

    public static ProjectApiCall getNoticeApiInstance(){
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        ProjectApiCall retrofit=new Retrofit.Builder()
                .baseUrl("http://"+ip+"/collage/api/public/noticeBoard.php/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ProjectApiCall.class);
        return retrofit;
    }


}
