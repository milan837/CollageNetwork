package com.group.milan.collage.collagenetworking.ForumQuestionListActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumQuestionListRepository {
    ForumQuestionListContract.Presenter presenter;
    Context context;

    public ForumQuestionListRepository(ForumQuestionListContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ForumQuestionListResponse> apiInterface=ProjectApiInstance.getForumApiInstance().getForumQuestionListResponse(jsonObject);
        apiInterface.enqueue(new Callback<ForumQuestionListResponse>() {
            @Override
            public void onResponse(Call<ForumQuestionListResponse> call, Response<ForumQuestionListResponse> response) {
                if(response.code()==200){
                    ForumQuestionListResponse forumQuestionListResponse=response.body();
                    presenter.getDataFromApi(forumQuestionListResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForumQuestionListResponse> call, Throwable t) {
                Toast.makeText(context,"field hit api",Toast.LENGTH_LONG).show();
            }
        });
    }
}
