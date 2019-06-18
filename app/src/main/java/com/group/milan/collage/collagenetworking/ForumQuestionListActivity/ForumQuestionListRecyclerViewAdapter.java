package com.group.milan.collage.collagenetworking.ForumQuestionListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.ForumAnswerActivity.ForumAnswerActivity;
import com.group.milan.collage.collagenetworking.ForumPostAnswerActivity.ForumPostAnswerActivity;
import com.group.milan.collage.collagenetworking.ForumQuestionListActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumQuestionListRecyclerViewAdapter extends RecyclerView.Adapter<ForumQuestionListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Response> list;

    public ForumQuestionListRecyclerViewAdapter(Context context, List<Response> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_forum_question_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.date.setText(list.get(i).getPublishOn());
        myViewHolder.subject.setText(list.get(i).getQuestion());
        myViewHolder.totalAnswer.setText(list.get(i).getFQId()+" Answers");
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ForumAnswerActivity.class);
                intent.putExtra("f_q_id",list.get(i).getFQId());
                intent.putExtra("username",list.get(i).getUsername());
                intent.putExtra("question",list.get(i).getQuestion());
                intent.putExtra("date",list.get(i).getPublishOn());
                context.startActivity(intent);
            }
        });

        myViewHolder.replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ForumPostAnswerActivity.class);
                intent.putExtra("f_q_id",list.get(i).getFQId());
                intent.putExtra("question",list.get(i).getQuestion());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_forum_question_date)
        TextView date;

        @BindView(R.id.adapter_forum_question_question)
        TextView subject;

        @BindView(R.id.adapter_forum_question_total_answer)
        TextView totalAnswer;

        @BindView(R.id.adapter_forum_question_replay)
        Button replayBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
