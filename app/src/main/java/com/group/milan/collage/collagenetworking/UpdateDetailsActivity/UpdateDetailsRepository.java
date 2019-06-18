package com.group.milan.collage.collagenetworking.UpdateDetailsActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.pojo.UpdateDetailsPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsRepository {
    UpdateDetailsContract.Presenter presenter;
    Context context;

    public UpdateDetailsRepository(UpdateDetailsContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void hitApi(final JsonObject jsonObject){
        Call<UpdateDetailsPojo> apiInterface= ProjectApiInstance.getAuthApiInstance().getUpdateDetailsResponse(jsonObject);
        apiInterface.enqueue(new Callback<UpdateDetailsPojo>() {
            @Override
            public void onResponse(Call<UpdateDetailsPojo> call, Response<UpdateDetailsPojo> response) {
                if(response.code()==200){
                    UpdateDetailsPojo updateDetailsPojo=response.body();
                    presenter.getDataFromApi(updateDetailsPojo);

                    SharedPreferences sharedPreferences=context.getSharedPreferences("user_login",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("user_id",jsonObject.get("user_id").getAsString());
                    editor.putString("username",jsonObject.get("username").getAsString());
                    editor.putString("branch",jsonObject.get("branch").getAsString());
                    editor.putString("usn",jsonObject.get("usn").getAsString());
//                    Log.i("data_as",jsonObject.get("user_id").toString()+"=>"+jsonObject.get("username").toString()+"=>"+jsonObject.get("branch").toString()+"=>"+jsonObject.get("usn").toString());
                    editor.commit();

                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateDetailsPojo> call, Throwable t) {
                Toast.makeText(context,"hit faield",Toast.LENGTH_LONG).show();
                Log.i("error",t.getMessage());
            }
        });
    }
}
