package com.group.milan.collage.collagenetworking.PostProjectActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.PostProjectActivity.pojo.PostProjectResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostProjectRepository {
    PostProjectContract.Presenter presenter;
    Context context;

    public PostProjectRepository(PostProjectContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<PostProjectResponse> apiInterface= ProjectApiInstance.getProjectApiInstance().getPostProjectResponse(jsonObject);
        apiInterface.enqueue(new Callback<PostProjectResponse>() {
            @Override
            public void onResponse(Call<PostProjectResponse> call, Response<PostProjectResponse> response) {
                if(response.code() == 200){
                    PostProjectResponse projectResponse=response.body();
                    presenter.getDataFromApi(projectResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostProjectResponse> call, Throwable t) {
                Toast.makeText(context,"faield hit api",Toast.LENGTH_LONG).show();
            }
        });
    }
}
