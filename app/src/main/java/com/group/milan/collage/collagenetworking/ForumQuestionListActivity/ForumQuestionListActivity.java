package com.group.milan.collage.collagenetworking.ForumQuestionListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumPostQuestionActivity.FroumPostQuestionActivity;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumQuestionListActivity extends AppCompatActivity implements ForumQuestionListContract.Views {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    ForumQuestionListPresenter presenter;
    List<Response> responseList;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.ask_question_button)
    RelativeLayout askQuestionBtn;

    @BindView(R.id.forum_question_recyclerview)
    RecyclerView recyclerView;

    ForumQuestionListRecyclerViewAdapter forumQuestionListRecyclerViewAdapter;
    int getTotalcount,lastVisibleItem;
    boolean loadMoreStatus=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_question_list);

        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        responseList=new ArrayList<>();
        presenter=new ForumQuestionListPresenter(this,ForumQuestionListActivity.this);
        initViews();
    }

    public void initViews() {

        sendDataToApi("0");

        forumQuestionListRecyclerViewAdapter = new ForumQuestionListRecyclerViewAdapter(ForumQuestionListActivity.this, responseList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(forumQuestionListRecyclerViewAdapter);

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
                            Log.i("milan_last1", response.getFQId());
                            //hiting the api from view
                            sendDataToApi(response.getFQId());
                            loadMoreStatus = true;
                        }
                    } else {
                        loadMoreStatus = false;
                    }
                }
            }
        });

        askQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForumQuestionListActivity.this, FroumPostQuestionActivity.class);
                startActivity(intent);

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
    public void displayResponseData(ForumQuestionListResponse forumQuestionListResponse) {
        progressBar.setVisibility(View.GONE);
        for(int i=0;i<forumQuestionListResponse.getResponse().size();i++){
            responseList.add(forumQuestionListResponse.getResponse().get(i));
            forumQuestionListRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
