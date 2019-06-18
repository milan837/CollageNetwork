package com.group.milan.collage.collagenetworking.PublishProjectListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.ParticipateButtonResponse;
import com.group.milan.collage.collagenetworking.PublishProjectListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.Retrofit.ProjectApiInstance;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class PublishProjectListRecyclerViewAdapter extends RecyclerView.Adapter<PublishProjectListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Response> list;
    String userId;

    public PublishProjectListRecyclerViewAdapter(Context context, List<Response> list,String userId) {
        this.context = context;
        this.list = list;
        this.userId=userId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_publish_project_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.date.setText(list.get(i).getPublishOn());
        myViewHolder.title.setText(list.get(i).getSummary());
        myViewHolder.totalMembers.setText(list.get(i).getTotalMembers()+" Members");


        myViewHolder.participateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.participateBtn.setVisibility(View.GONE);
                notifyDataSetChanged();

                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("user_id",userId);
                jsonObject.addProperty("project_id",list.get(i).getProjectId());
                hitParticipateBtnApi(jsonObject);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_project_publish_button_date)
        TextView date;

        @BindView(R.id.adapter_project_publish_question)
        TextView title;

        @BindView(R.id.adapter_project_publish_total_member)
        TextView totalMembers;

        @BindView(R.id.adapter_project_publish_button)
        RelativeLayout participateBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public String hitParticipateBtnApi(JsonObject jsonObject){
        Call<ParticipateButtonResponse> apiInterface= ProjectApiInstance.getProjectApiInstance().getParticipateButtonResponse(jsonObject);
        apiInterface.enqueue(new Callback<ParticipateButtonResponse>() {
            @Override
            public void onResponse(Call<ParticipateButtonResponse> call, retrofit2.Response<ParticipateButtonResponse> response) {
                if(response.code()==200){
                   Toast.makeText(context,"ok",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ParticipateButtonResponse> call, Throwable t) {
                Toast.makeText(context,"faield",Toast.LENGTH_LONG).show();
            }
        });

        return null;
    }
}
