package com.group.milan.collage.collagenetworking.DepartmentNoticeActivity;

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

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.AdminNoticeActivity;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.AdminNoticePresenter;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.AdminNoticeRecyclerViewAdapter;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.AdminNoticeResponse;
import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartmentNoticeActivity extends AppCompatActivity implements DepartmentNoticeContract.Views {

    DepartmentNoticePresenter presenter;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.department_notice_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.back_icon)
    ImageView backIcon;

    List<Response> responseList;
    DepartmentNoticeRecyclerViewAdapter departmentNoticeRecyclerViewAdapter;

    int getTotalcount,lastVisibleItem;
    boolean loadMoreStatus=true;

    String type="department";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_notice);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        responseList=new ArrayList<>();
        presenter=new DepartmentNoticePresenter(this,DepartmentNoticeActivity.this);
        initViews();
    }


    public void initViews() {

        sendDataToApi("0",type);

        departmentNoticeRecyclerViewAdapter = new DepartmentNoticeRecyclerViewAdapter(DepartmentNoticeActivity.this, responseList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(departmentNoticeRecyclerViewAdapter);

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
                            Log.i("milan_last", response.getNoticeBoardId());
                            //hiting the api from view
                            sendDataToApi(response.getNoticeBoardId(),type);
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


    public void sendDataToApi(String paginationId,String type){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("pagination_id",paginationId);
        jsonObject.addProperty("type",type);
        presenter.sendDataToApi(jsonObject);
    }


    @Override
    public void displayResponseData(AdminNoticeResponse adminNoticeResponse) {
        progressBar.setVisibility(View.GONE);
            for(int i=0;i<adminNoticeResponse.getResponse().size();i++){
                responseList.add(adminNoticeResponse.getResponse().get(i));
                departmentNoticeRecyclerViewAdapter.notifyDataSetChanged();
            }
    }

}
