package com.group.milan.collage.collagenetworking.DepartmentNoticeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.AdminNoticeActivity.pojo.Response;
import com.group.milan.collage.collagenetworking.NoticeDetailsActivity.NoticeDetailsActivity;
import com.group.milan.collage.collagenetworking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartmentNoticeRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentNoticeRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Response> list;

    public DepartmentNoticeRecyclerViewAdapter(Context context, List<Response> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_admin_notice_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.date.setText(list.get(i).getPublishOn());
        myViewHolder.subject.setText(list.get(i).getSubject());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, NoticeDetailsActivity.class);
                intent.putExtra("notice_id",list.get(i).getNoticeBoardId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_admin_notice_date)
        TextView date;

        @BindView(R.id.adapter_admin_notice_subject)
        TextView subject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
