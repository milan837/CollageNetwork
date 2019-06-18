package com.group.milan.collage.collagenetworking.MenuActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.LoginActivity.LoginActivity;
import com.group.milan.collage.collagenetworking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.logout_button )
    Button logoutBtn;

    @BindView(R.id.username)
    TextView usernameTxt;

    @BindView(R.id.usn)
    TextView usnTxt;
    @BindView(R.id.branch)
    TextView branchTxt;

    SharedPreferences sharedPreferences;
    String username,branch,usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        username=sharedPreferences.getString("username",null);
        usn=sharedPreferences.getString("usn",null);
        branch=sharedPreferences.getString("branch",null);

        usernameTxt.setText(username);
        usnTxt.setText(usn);
        branchTxt.setText(branch);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent intent=new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}
