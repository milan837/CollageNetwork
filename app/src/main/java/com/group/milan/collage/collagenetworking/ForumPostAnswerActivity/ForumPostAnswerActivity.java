package com.group.milan.collage.collagenetworking.ForumPostAnswerActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.pojo.ForumPostAnswerResponse;
import com.group.milan.collage.collagenetworking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumPostAnswerActivity extends AppCompatActivity implements ForumPostAnswerContract.Views{
    ForumPostAnswerPresenter presenter;

    @BindView(R.id.forum_post_answer)
    EditText ansertTxt;

    @BindView(R.id.forum_post_answer_button)
    Button buttonPost;

    @BindView(R.id.forum_post_question)
    TextView questionTxt;

    @BindView(R.id.back_icon)
    ImageView backBtn;

    ProgressDialog progressDialog;

    String fQId,question,userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post_answer);
        ButterKnife.bind(this);

        fQId=getIntent().getStringExtra("f_q_id");
        question=getIntent().getStringExtra("question");

        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Answering.....");
        progressDialog.setCancelable(false);

        presenter=new ForumPostAnswerPresenter(this,ForumPostAnswerActivity.this);
        initViews();

    }

    public void initViews(){
        questionTxt.setText(question);
//        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
//        userId=sharedPreferences.getString("user_id",null);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getAnswer=ansertTxt.getText().toString().trim();

                if(getAnswer.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the answer",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(fQId,userId,getAnswer);
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

    public void sendDataToApi(String fQId,String userId,String answer){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("f_q_id",fQId);
        jsonObject.addProperty("user_id",userId);
        jsonObject.addProperty("answer",answer);
        Log.i("aaa",fQId+"=>"+userId+""+answer);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(ForumPostAnswerResponse forumPostAnswerResponse) {
        progressDialog.hide();
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),forumPostAnswerResponse.getStatus(),Toast.LENGTH_LONG).show();
        if(forumPostAnswerResponse.getStatus().equals("ok")){
            ansertTxt.setText("");
        }
    }
}
