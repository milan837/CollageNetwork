package com.group.milan.collage.collagenetworking.PublishProjectListActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.PublishProjectResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishProjectListRepository {
    PublishProjectListContract.Presenter presenter;
    Context context;

    public PublishProjectListRepository(PublishProjectListContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<PublishProjectResponse> apiInterface= ProjectApiInstance.getProjectApiInstance().getPublishProjectListResponse(jsonObject);
        apiInterface.enqueue(new Callback<PublishProjectResponse>() {
            @Override
            public void onResponse(Call<PublishProjectResponse> call, Response<PublishProjectResponse> response) {
                if(response.code()==200){
                    PublishProjectResponse participatedProjectResponse=response.body();
                    presenter.getDataFromApi(participatedProjectResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PublishProjectResponse> call, Throwable t) {
                Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
            }

        });
    }
}
