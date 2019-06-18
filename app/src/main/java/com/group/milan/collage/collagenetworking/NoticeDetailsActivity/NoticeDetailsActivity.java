package com.group.milan.collage.collagenetworking.NoticeDetailsActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.group.milan.collage.collagenetworking.DepartmentNoticeActivity.DepartmentNoticeActivity;
import com.group.milan.collage.collagenetworking.DepartmentNoticeActivity.DepartmentNoticePresenter;
import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.pojo.NoticeDetailsResponse;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeDetailsActivity extends AppCompatActivity implements NoticeDetailsContract.Views{

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    NoticeDetailsPresenter presenter;
    NoticeDetailsResponse response;

    @BindView(R.id.notice_body)
    TextView noticeBody;

    @BindView(R.id.notice_date)
    TextView publishDate;

    @BindView(R.id.notice_subject)
    TextView noticeSubject;

    @BindView(R.id.notice_details_image)
    ImageView noticeImageUrl;

    @BindView(R.id.backi_icon)
            ImageView backIcon;

    String noticeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        ButterKnife.bind(this);
        noticeId=getIntent().getStringExtra("notice_id");

        progressBar.setVisibility(View.GONE);
        presenter=new NoticeDetailsPresenter(this,NoticeDetailsActivity.this);
        initViews();
    }

    public void initViews(){
        sendDataToApi(noticeId);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void sendDataToApi(String noticeId){
        presenter.sendDataToApi(noticeId);
    }

    @Override
    public void displayResponseData(NoticeDetailsResponse noticeDetailsResponse) {
        progressBar.setVisibility(View.GONE);
        this.response=noticeDetailsResponse;
        publishDate.setText(noticeDetailsResponse.getResponse().getPublishOn());
        noticeBody.setText(noticeDetailsResponse.getResponse().getBody());
        noticeSubject.setText(noticeDetailsResponse.getResponse().getSubject());

        if(noticeDetailsResponse.getResponse().getImageUrl().trim().isEmpty()){
            noticeImageUrl.setVisibility(View.GONE);
        }else{
            noticeImageUrl.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(noticeDetailsResponse.getResponse().getImageUrl())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(noticeImageUrl);
        }
    }
}
