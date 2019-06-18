package com.group.milan.collage.collagenetworking.LoginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.LoginActivity.pojo.LoginResponse;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.SignUpActivity.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.Views{

    LoginPresenter loginPresenter;
    @BindView(R.id.login_edittext_email)
    EditText email;

    @BindView(R.id.login_edittext_password)
    EditText password;

    @BindView(R.id.login_textview_forget_password)
    TextView forgetPassword;

    @BindView(R.id.login_button)
    Button loginBtn;

    @BindView(R.id.signup_button)
    Button signupBtn;

    String getEmail,getPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        ButterKnife.bind(this);

        loginPresenter=new LoginPresenter(this,LoginActivity.this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Login....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        initViews();
    }

    public void initViews(){

        //login check
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail=email.getText().toString().trim();
                getPassword=password.getText().toString().trim();

                if(getEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the email",Toast.LENGTH_LONG).show();
                }else if(getPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the password",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    sendDataToApi(getEmail,getPassword);
                }
            }
        });

        //sign up check
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //forget passsword
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"forget password",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void displayResponseData(LoginResponse loginResponse) {
        progressDialog.hide();
        progressDialog.dismiss();

        if(loginResponse.getResponse().getStatus().equals("ok")){
            finish();
            Toast.makeText(getApplicationContext(),loginResponse.getResponse().getStatus(),Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),loginResponse.getResponse().getStatus(),Toast.LENGTH_LONG).show();
        }
    }

    public void sendDataToApi(String email,String password){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("email",email);
        jsonObject.addProperty("password",password);
        loginPresenter.sendDataToApi(jsonObject);
    }
}
