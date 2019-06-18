package com.group.milan.collage.collagenetworking.ForumPostQuestionActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumPostQuestionRepository {
    ForumPostQuestionContract.Presenter presenter;
    Context context;

    public ForumPostQuestionRepository(ForumPostQuestionContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ForumPostAnswerResponse> apiInterface= ProjectApiInstance.getForumApiInstance().getForumPostQuestionResponse(jsonObject);
        apiInterface.enqueue(new Callback<ForumPostAnswerResponse>() {
            @Override
            public void onResponse(Call<ForumPostAnswerResponse> call, Response<ForumPostAnswerResponse> response) {
                if(response.code() == 200){
                    ForumPostAnswerResponse forumPostAnswerResponse=response.body();
                    presenter.getDataFromApi(forumPostAnswerResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForumPostAnswerResponse> call, Throwable t) {
                Toast.makeText(context,"faield hit api",Toast.LENGTH_LONG).show();
            }
        });
    }
}
