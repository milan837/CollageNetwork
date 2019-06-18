package com.group.milan.collage.collagenetworking.SignUpActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;
import com.group.milan.collage.collagenetworking.SignUpActivity.pojo.SignUpPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {
    SignUpContract.Presenter presenter;
    Context context;

    public SignUpRepository(SignUpContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(JsonObject jsonObject){
        Call<SignUpPojo> apiInterface= ProjectApiInstance.getAuthApiInstance().getSignUpResponse(jsonObject);
        apiInterface.enqueue(new Callback<SignUpPojo>() {
            @Override
            public void onResponse(Call<SignUpPojo> call, Response<SignUpPojo> response) {
                if(response.code() == 200){
                    SignUpPojo signUpPojo=response.body();
                    presenter.getDataFromApi(signUpPojo);
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpPojo> call, Throwable t) {
                Toast.makeText(context,"faield to hit api",Toast.LENGTH_LONG).show();
                Log.i("milan_rep", t.getMessage());
            }
        });
    }
}
