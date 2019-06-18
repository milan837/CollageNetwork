package com.group.milan.collage.collagenetworking.PostProjectActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.PostProjectActivity.pojo.PostProjectResponse;
import com.group.milan.collage.collagenetworking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostProjectActivity extends AppCompatActivity implements PostProjectContract.Views{

    ProgressDialog progressDialog;
    PostProjectPresenter presenter;

    @BindView(R.id.post_project_button)
    Button proceedBtn;

    @BindView(R.id.project_post_title)
    EditText postTitle;

    @BindView(R.id.project_post_max_users)
    EditText maxUser;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project);
        ButterKnife.bind(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Creating.....");
        progressDialog.setCancelable(false);

        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);

        presenter=new PostProjectPresenter(this,PostProjectActivity.this);
        initViews();
    }

    public void initViews(){
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle=postTitle.getText().toString().trim();
                String getMaxUser=maxUser.getText().toString().trim();

                if(getTitle.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the summary",Toast.LENGTH_LONG).show();
                }else if(getMaxUser.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the max User",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(userId,getMaxUser,getTitle);
                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void sendDataToApi(String userId,String userLimit,String summary){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",userId);
        jsonObject.addProperty("user_limit",userLimit);
        jsonObject.addProperty("summary",summary);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(PostProjectResponse postProjectResponse) {
        progressDialog.hide();
        progressDialog.dismiss();
        postTitle.setText("");
        maxUser.setText("");
        Toast.makeText(getApplicationContext(),postProjectResponse.getStatus(),Toast.LENGTH_LONG).show();
    }
}
