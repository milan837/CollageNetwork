package com.group.milan.collage.collagenetworking.ForumAnswerActivity;

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
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.ForumAnswerListResponse;
import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.ForumQuestionListActivity;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.ForumQuestionListPresenter;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.ForumQuestionListRecyclerViewAdapter;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.ForumQuestionListResponse;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumAnswerActivity extends AppCompatActivity implements ForumAnswerListContract.Views {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    ForumAnswerListPresenter presenter;
    List<Response> responseList;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.forum_answer_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.forum_answer_username)
    TextView usernameTxt;

    @BindView(R.id.forum_answer_date)
    TextView dateTxt;

    @BindView(R.id.forum_answer_question)
            TextView questionTxt;

    ForumAnswerListRecyclerViewAdapter forumAnswerListRecyclerViewAdapter;
    int getTotalcount,lastVisibleItem;
    boolean loadMoreStatus=true;

    String fQId,username,question,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_answer);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        responseList=new ArrayList<>();
        presenter=new ForumAnswerListPresenter(this,ForumAnswerActivity.this);
        fQId=getIntent().getStringExtra("f_q_id");
        username=getIntent().getStringExtra("username");
        question=getIntent().getStringExtra("question");
        date=getIntent().getStringExtra("date");

        initViews();
    }

    public void initViews() {

        usernameTxt.setText(username);
        questionTxt.setText(question);
        dateTxt.setText(date);

        sendDataToApi("0",fQId);

        forumAnswerListRecyclerViewAdapter = new ForumAnswerListRecyclerViewAdapter(ForumAnswerActivity.this, responseList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(forumAnswerListRecyclerViewAdapter);

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
                            Log.i("milan_last", response.getFQId());
                            //hiting the api from view
                            sendDataToApi(response.getFAId(),fQId);
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

    public void sendDataToApi(String paginationId,String fQId){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("pagination_id",paginationId);
        jsonObject.addProperty("f_q_id",fQId);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponseData(ForumAnswerListResponse forumAnswerListResponse) {
        progressBar.setVisibility(View.GONE);
        for(int i=0;i<forumAnswerListResponse.getResponse().size();i++){
            responseList.add(forumAnswerListResponse.getResponse().get(i));
            forumAnswerListRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
