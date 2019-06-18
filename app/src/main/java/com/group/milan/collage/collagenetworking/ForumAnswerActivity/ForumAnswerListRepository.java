package com.group.milan.collage.collagenetworking.ForumAnswerActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.ForumAnswerListResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumAnswerListRepository {
    ForumAnswerListContract.Presenter presenter;
    Context context;

    public ForumAnswerListRepository(ForumAnswerListContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ForumAnswerListResponse> apiInterface=ProjectApiInstance.getForumApiInstance().getForumAnswerListResponse(jsonObject);
        apiInterface.enqueue(new Callback<ForumAnswerListResponse>() {
            @Override
            public void onResponse(Call<ForumAnswerListResponse> call, Response<ForumAnswerListResponse> response) {
                if(response.code() == 200){
                    ForumAnswerListResponse forumAnswerListResponse=response.body();
                    presenter.getDataFromApi(forumAnswerListResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForumAnswerListResponse> call, Throwable t) {
                Toast.makeText(context,"faield",Toast.LENGTH_LONG).show();
            }
        });
    }
}
