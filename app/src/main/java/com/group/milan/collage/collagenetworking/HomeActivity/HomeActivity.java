package com.group.milan.collage.collagenetworking.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.group.milan.collage.collagenetworking.AdminNoticeActivity.AdminNoticeActivity;
import com.group.milan.collage.collagenetworking.DepartmentNoticeActivity.DepartmentNoticeActivity;
import com.group.milan.collage.collagenetworking.DepartmentNoticeActivity.DepartmentNoticeRepository;
import com.group.milan.collage.collagenetworking.DeveloperDetailsActivity.DeveloperDetailsActivity;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.ForumQuestionListActivity;
import com.group.milan.collage.collagenetworking.MenuActivity.MenuActivity;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.ParticipatedProjectListActivity;
import com.group.milan.collage.collagenetworking.PostProjectActivity.PostProjectActivity;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.PublishProjectListActivity;
import com.group.milan.collage.collagenetworking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.menu_admin_notice)
    LinearLayout menuAdminNotice;

    @BindView(R.id.menu_department_notice)
    LinearLayout menuDepartmentNotice;

    @BindView(R.id.menu_published_project)
    LinearLayout menuPublishProject;

    @BindView(R.id.menu_participated_project)
    LinearLayout menuParticipatedProject;

    @BindView(R.id.menu_forum)
    LinearLayout menuForum;

    @BindView(R.id.menu_developer)
    LinearLayout menuDeveloper;

    @BindView(R.id.menu_icon)
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        menuAdminNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, AdminNoticeActivity.class);
                startActivity(intent);
            }
        });

        menuDepartmentNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, DepartmentNoticeActivity.class);
                startActivity(intent);

            }
        });

        menuPublishProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, PublishProjectListActivity.class);
                startActivity(intent);

            }
        });

        menuParticipatedProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ParticipatedProjectListActivity.class);
                startActivity(intent);
            }
        });

        menuForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ForumQuestionListActivity.class);
                startActivity(intent);
            }
        });

        menuDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, DeveloperDetailsActivity.class);
                startActivity(intent);
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, PostProjectActivity.class);
                startActivity(intent);
            }
        });

    }



}
