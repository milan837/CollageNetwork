package com.group.milan.collage.collagenetworking.UpdateDetailsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.SignUpActivity.SignUpActivity;
import com.group.milan.collage.collagenetworking.SignUpActivity.SignUpPresenter;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.pojo.UpdateDetailsPojo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateDetailsActivity extends AppCompatActivity implements UpdateDetailsContract.Views{
    UpdateDetailsPresenter presenter;
    ProgressDialog progressDialog;

    @BindView(R.id.update_edittext_username)
    EditText username;

    @BindView(R.id.update_edittext_branch)
    EditText branch;

    @BindView(R.id.update_edittext_usn)
    EditText usn;

    @BindView(R.id.update_button)
    Button saveBtn;

    String userId,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        ButterKnife.bind(this);
        presenter=new UpdateDetailsPresenter(this,UpdateDetailsActivity.this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);

        userId=getIntent().getStringExtra("user_id");
        email=getIntent().getStringExtra("email");

        initViews();
    }

    public void initViews(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsn,getUsername,getBranch;
                getBranch=branch.getText().toString().trim();
                getUsername=username.getText().toString().trim();
                getUsn=usn.getText().toString().trim();

                if(getUsername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the name",Toast.LENGTH_LONG).show();
                }else if(getBranch.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the branch",Toast.LENGTH_LONG).show();
                }else if(getUsn.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the usn",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(userId,getUsername,getBranch,getUsn);
                }
            }
        });
    }

    public void sendDataToApi(String userId,String username,String branch,String usn){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",userId);
        jsonObject.addProperty("username",username);
        jsonObject.addProperty("branch",branch);
        jsonObject.addProperty("usn",usn);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(UpdateDetailsPojo updateDetailsPojo) {
        progressDialog.hide();
        progressDialog.dismiss();

        if(updateDetailsPojo.getResponse().getStatus().equals("ok")){
            Intent intent=new Intent(UpdateDetailsActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),updateDetailsPojo.getResponse().getStatus(),Toast.LENGTH_LONG).show();
        }
    }
}
