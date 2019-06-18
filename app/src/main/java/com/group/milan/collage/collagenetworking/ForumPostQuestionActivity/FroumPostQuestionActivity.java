package com.group.milan.collage.collagenetworking.ForumPostQuestionActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FroumPostQuestionActivity extends AppCompatActivity implements ForumPostQuestionContract.Views{

    ForumPostQuestionPresenter presenter;

    @BindView(R.id.forum_post_question)
    EditText questionTxt;

    @BindView(R.id.forum_post_question_button)
    Button buttonPost;

    @BindView(R.id.back_icon)
    ImageView backBtn;

    ProgressDialog progressDialog;

    String fQId,question,userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_froum_post_question);
        ButterKnife.bind(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Answering.....");
        progressDialog.setCancelable(false);

        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);

        presenter=new ForumPostQuestionPresenter(this,FroumPostQuestionActivity.this);
        initViews();

    }

    public void initViews(){
//        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
//        userId=sharedPreferences.getString("user_id",null);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getQuestion=questionTxt.getText().toString().trim();

                if(getQuestion.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the Question",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(userId,getQuestion);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void sendDataToApi(String userId,String question){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",userId);
        jsonObject.addProperty("question",question);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(ForumPostAnswerResponse forumPostAnswerResponse) {
        progressDialog.hide();
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),forumPostAnswerResponse.getStatus(),Toast.LENGTH_LONG).show();
        if(forumPostAnswerResponse.getStatus().equals("ok")){
            questionTxt.setText("");
        }
    }
}
