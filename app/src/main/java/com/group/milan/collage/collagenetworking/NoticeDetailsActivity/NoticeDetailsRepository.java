package com.group.milan.collage.collagenetworking.NoticeDetailsActivity;

import android.content.Context;
import android.widget.Toast;

import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.pojo.NoticeDetailsResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailsRepository {
    NoticeDetailsContract.Presenter presenter;
    Context context;

    public NoticeDetailsRepository(NoticeDetailsContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(String noticeId){
       Call<NoticeDetailsResponse> apiInterface=ProjectApiInstance.getNoticeApiInstance().getNoticeDetailsResponse(noticeId);
       apiInterface.enqueue(new Callback<NoticeDetailsResponse>() {
           @Override
           public void onResponse(Call<NoticeDetailsResponse> call, Response<NoticeDetailsResponse> response) {
               if(response.code()==200){
                   NoticeDetailsResponse noticeDetailsResponse=response.body();
                   presenter.getDataFromApi(noticeDetailsResponse);
               }else{
                   Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onFailure(Call<NoticeDetailsResponse> call, Throwable t) {
               Toast.makeText(context,"faield",Toast.LENGTH_LONG).show();
           }
       });
    }
}
