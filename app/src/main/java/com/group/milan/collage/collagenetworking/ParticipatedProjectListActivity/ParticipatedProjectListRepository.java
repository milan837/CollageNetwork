package com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticipatedProjectListRepository {
    ParticipatedProjectListContract.Presenter presenter;
    Context context;

    public ParticipatedProjectListRepository(ParticipatedProjectListContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ParticipatedProjectResponse> apiInterface= ProjectApiInstance.getProjectApiInstance().getParticipatedProjectListResponse(jsonObject);
        apiInterface.enqueue(new Callback<ParticipatedProjectResponse>() {
            @Override
            public void onResponse(Call<ParticipatedProjectResponse> call, Response<ParticipatedProjectResponse> response) {
                if(response.code()==200){
                    ParticipatedProjectResponse participatedProjectResponse=response.body();
                    presenter.getDataFromApi(participatedProjectResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ParticipatedProjectResponse> call, Throwable t) {
                Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
            }

        });
    }
}
