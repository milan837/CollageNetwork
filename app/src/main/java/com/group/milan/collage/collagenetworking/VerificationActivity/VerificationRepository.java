package com.group.milan.collage.collagenetworking.VerificationActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationRepository {
    VerificationContract.Presenter presenter;
    Context context;

    public VerificationRepository(VerificationContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<VerificationResponse> apiInterface= ProjectApiInstance.getAuthApiInstance().getVerificationResponse(jsonObject);
        apiInterface.enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                if(response.code()==200){
                    VerificationResponse verificationResponse=response.body();
                    presenter.getDataFromApi(verificationResponse);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                Toast.makeText(context,"faield to hit api",Toast.LENGTH_LONG).show();
                Log.i("milan_rep", t.getMessage());
            }
        });
    }
}
