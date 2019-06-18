package com.group.milan.collage.collagenetworking.SignUpActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.LoginActivity.LoginActivity;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.SignUpActivity.pojo.SignUpPojo;
import com.group.milan.collage.collagenetworking.VerificationActivity.VerificationActivity;
import com.group.milan.collage.collagenetworking.VerificationActivity.pojo.VerificationResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.Views{

    SignUpPresenter presenter;

    @BindView(R.id.signup_edittext_email)
    EditText email;

    @BindView(R.id.signup_edittext_password)
    EditText password;

    @BindView(R.id.signup_re_edittext_password)
    EditText repassword;

    @BindView(R.id.login_button)
    Button loginBtn;

    @BindView(R.id.signup_button)
    Button signupBtn;

    String getEmail,getPassword,getRePassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        ButterKnife.bind(this);
        presenter=new SignUpPresenter(this,SignUpActivity.this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Signing up....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        initViews();
    }

    public void initViews(){
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail=email.getText().toString().trim();
                getPassword=password.getText().toString().trim();
                getRePassword=repassword.getText().toString().trim();

                if(getEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the email",Toast.LENGTH_LONG).show();
                }else if(getPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the password",Toast.LENGTH_LONG).show();
                }else if(getRePassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter the re-password",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDatatToApi(getEmail,getPassword);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void sendDatatToApi(String email,String password){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("email",email);
        jsonObject.addProperty("password",password);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(SignUpPojo signUpPojo) {
        progressDialog.hide();
        progressDialog.dismiss();

        if(signUpPojo.getResponse().getStatus().equals("ok")){
            Toast.makeText(getApplicationContext(),signUpPojo.getResponse().getStatus(),Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SignUpActivity.this, VerificationActivity.class);
            intent.putExtra("user_id",signUpPojo.getResponse().getUserId());
            intent.putExtra("email",signUpPojo.getResponse().getEmail());
            intent.putExtra("code",signUpPojo.getResponse().getCode());
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),signUpPojo.getResponse().getStatus(),Toast.LENGTH_LONG).show();
        }
    }

}
