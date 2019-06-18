package com.group.milan.collage.collagenetworking.PublishProjectListActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.ParticipatedProjectListPresenter;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.ParticipateButtonResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.PublishProjectResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishProjectListActivity extends AppCompatActivity implements PublishProjectListContract.Views {

    PublishProjectListPresenter presenter;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.publish_project_recyclerview)
    RecyclerView recyclerView;
    List<Response> responseList;

    int getTotalcount,lastVisibleItem;
    boolean loadMoreStatus=true;
    PublishProjectListRecyclerViewAdapter adapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_project_list);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);

        responseList=new ArrayList<>();
        presenter=new PublishProjectListPresenter(this,PublishProjectListActivity.this);
        initViews();
    }

    public void initViews(){
        sendDataToApi("0");

        adapter = new PublishProjectListRecyclerViewAdapter(PublishProjectListActivity.this, responseList,userId);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getTotalcount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (responseList.size() > 0) {
                    if (getTotalcount - 1 == lastVisibleItem) {
                        Response response = (Response) responseList.get(recyclerView.getAdapter().getItemCount() - 1);
                        if (!loadMoreStatus) {
                            Log.i("milan_last1", response.getProjectId());
                            //hiting the api from view
                            sendDataToApi(response.getProjectId());
                            loadMoreStatus = true;
                        }
                    } else {
                        loadMoreStatus = false;
                    }
                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void sendDataToApi(String paginationId){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("pagination_id",paginationId);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(PublishProjectResponse publishProjectResponse) {
        progressBar.setVisibility(View.GONE);
        for (int i=0;i<publishProjectResponse.getResponse().size();i++){
            responseList.add(publishProjectResponse.getResponse().get(i));
            adapter.notifyDataSetChanged();
        }
    }



}
