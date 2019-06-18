package com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity;

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
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.ParticipatedProjectResponse;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipatedProjectListActivity extends AppCompatActivity implements ParticipatedProjectListContract.Views{
    ParticipatedProjectListPresenter presenter;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.participated_project_recyclerview)
    RecyclerView recyclerView;
    List<Response> responseList;

    int getTotalcount,lastVisibleItem;
    boolean loadMoreStatus=true;
    ParticipatedProjectListRecyclerViewAdapter adapter;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participated_project_list);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);
        progressBar.setVisibility(View.VISIBLE);
        responseList=new ArrayList<>();
        presenter=new ParticipatedProjectListPresenter(this,ParticipatedProjectListActivity.this);
        initViews();
    }

    public void initViews(){
        sendDataToApi("0",userId);
        adapter = new ParticipatedProjectListRecyclerViewAdapter(ParticipatedProjectListActivity.this, responseList);
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
                            Log.i("milan_last1", response.getP_p_id());
                            //hiting the api from view
                            sendDataToApi(response.getP_p_id(),userId);
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

    public void sendDataToApi(String paginationId,String userId){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("pagination_id",paginationId);
        jsonObject.addProperty("user_id",userId);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(ParticipatedProjectResponse participatedProjectResponse) {
        progressBar.setVisibility(View.GONE);
        for (int i=0;i<participatedProjectResponse.getResponse().size();i++){
            responseList.add(participatedProjectResponse.getResponse().get(i));
            adapter.notifyDataSetChanged();
        }
    }
}
