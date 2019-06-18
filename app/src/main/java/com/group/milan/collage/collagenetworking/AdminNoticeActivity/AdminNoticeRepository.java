package com.group.milan.collage.collagenetworking.AdminNoticeActivity;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNoticeRepository {
    AdminNoticeContract.Presenter presenter;
    Context context;

    public AdminNoticeRepository(AdminNoticeContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<AdminNoticeResponse> apiInterface= ProjectApiInstance.getNoticeApiInstance().getNoticeListResponse(jsonObject);
        apiInterface.enqueue(new Callback<AdminNoticeResponse>() {
            @Override
            public void onResponse(Call<AdminNoticeResponse> call, Response<AdminNoticeResponse> response) {
                if(response.code()==200){
                    AdminNoticeResponse adminNoticeResponse=response.body();
                    presenter.getDataFromApi(adminNoticeResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AdminNoticeResponse> call, Throwable t) {
                Toast.makeText(context,"failure hit",Toast.LENGTH_LONG).show();
            }
        });
    }
}
