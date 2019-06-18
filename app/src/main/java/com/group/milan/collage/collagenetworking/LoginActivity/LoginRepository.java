package com.group.milan.collage.collagenetworking.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.LoginActivity.pojo.LoginResponse;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    LoginContract.Presenter presenter;
    Context context;

    public LoginRepository(LoginContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(final JsonObject jsonObject){
        Call<LoginResponse> apiInterface= ProjectApiInstance.getAuthApiInstance().getLoginResponse(jsonObject);
        apiInterface.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 200){
                    LoginResponse loginResponse=response.body();
                    presenter.getDataFromApi(loginResponse);
                    SharedPreferences sharedPreferences=context.getSharedPreferences("user_login",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit=sharedPreferences.edit();
                    edit.putString("username",loginResponse.getResponse().getUsername());
                    edit.putString("user_id",loginResponse.getResponse().getUserId());
                    edit.putString("usn",loginResponse.getResponse().getUsn());
                    edit.putString("branch",loginResponse.getResponse().getBranch());
                    edit.commit();
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(context,"faield to hit api",Toast.LENGTH_LONG).show();
                Log.i("milan_rep", t.getMessage());
            }
        });
    }
}
