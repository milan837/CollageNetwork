package com.group.milan.collage.collagenetworking.ForumAnswerActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.ForumAnswerActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumAnswerListRecyclerViewAdapter extends RecyclerView.Adapter<ForumAnswerListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Response> list;

    public ForumAnswerListRecyclerViewAdapter(Context context, List<Response> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_forum_answer_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.date.setText(list.get(i).getPublishOn());
        myViewHolder.subject.setText(list.get(i).getAnswer());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_forum_answer_date)
        TextView date;

        @BindView(R.id.adapter_forum_answer_answer)
        TextView subject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
