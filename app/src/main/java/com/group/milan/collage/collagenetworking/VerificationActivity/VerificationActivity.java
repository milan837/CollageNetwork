package com.group.milan.collage.collagenetworking.VerificationActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.UpdateDetailsActivity.UpdateDetailsActivity;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationActivity extends AppCompatActivity implements VerificationContract.Views{

    VerificationPresenter presenter;
    ProgressDialog progressDialog;

    @BindView(R.id.verification_edittext_code)
    EditText code;

    @BindView(R.id.verification_button)
    Button button;
    String userId,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_activity);
        ButterKnife.bind(this);

        presenter=new VerificationPresenter(this,VerificationActivity.this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Verifiying....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);

        userId=getIntent().getStringExtra("user_id");
        email=getIntent().getStringExtra("email");

        initViews();
    }

    public void initViews(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getCode=code.getText().toString().trim();
                if(getCode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the code",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(userId,getCode,email);
                }
            }
        });
    }

    public void sendDataToApi(String userId,String code,String email){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",userId);
        jsonObject.addProperty("email",email);
        jsonObject.addProperty("code",code);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(VerificationResponse verificationResponse) {
        if(verificationResponse.getResponse().getStatus().equals("ok")){
            Toast.makeText(getApplicationContext(),"Verified sucessfully",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(VerificationActivity.this, UpdateDetailsActivity.class);
            intent.putExtra("user_id",userId);
            intent.putExtra("email",email);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Verification Unsucessfull",Toast.LENGTH_LONG).show();
        }
    }

}
