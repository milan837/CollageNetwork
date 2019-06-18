package com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.ChatActivity.ChatActivity;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.ParticipatedProjectListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipatedProjectListRecyclerViewAdapter extends RecyclerView.Adapter<ParticipatedProjectListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Response> list;

    public ParticipatedProjectListRecyclerViewAdapter(Context context, List<Response> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_participated_project_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.date.setText(list.get(i).getPublishOn());
        myViewHolder.title.setText(list.get(i).getSummary());
        myViewHolder.totalMembers.setText(list.get(i).getTotalMembers()+" Members");


        myViewHolder.chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("project_id",list.get(i).getProjectId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_project_participate_button_date)
        TextView date;

        @BindView(R.id.adapter_project_participate_question)
        TextView title;

        @BindView(R.id.adapter_project_participate_total_members)
        TextView totalMembers;

        @BindView(R.id.adapter_project_participate_button)
        Button chatBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
