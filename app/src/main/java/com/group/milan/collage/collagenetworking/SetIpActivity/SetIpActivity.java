package com.group.milan.collage.collagenetworking.SetIpActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.SplashScreenActivity.SplashScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetIpActivity extends AppCompatActivity {

    @BindView(R.id.ip_text)
    EditText ipAddress;

    @BindView(R.id.set_ip_btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);

        ButterKnife.bind(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getIp=ipAddress.getText().toString().trim();
                if(getIp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"enter ip",Toast.LENGTH_LONG).show();
                }else {

                    SharedPreferences sharedPreferences = getSharedPreferences("ip", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ip_address", getIp);
                    editor.apply();
                    editor.commit();

                    Intent intent=new Intent(SetIpActivity.this, SplashScreenActivity.class);
                    startActivity(intent);
                }
            }
        });



    }

}
