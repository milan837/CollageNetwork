package com.group.milan.collage.collagenetworking.ChatActivity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.milan.collage.collagenetworking.ChatActivity.pojo.ChatPojo;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.Utils.UtilsClass;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.MyViewHolder>{
    Context context;
    List<ChatPojo> list;

    public ChatRecyclerViewAdapter(Context context, List<ChatPojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.adapter_chat_box_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.date.setText(UtilsClass.getDayFromTimestamp(list.get(i).getTime()));
        myViewHolder.message.setText(list.get(i).getMessage());
        myViewHolder.username.setText(list.get(i).getUsername());
        myViewHolder.username.setTextColor(Color.parseColor(list.get(i).getColor()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_chat_username)
        TextView username;

        @BindView(R.id.adapter_chat_date)
        TextView date;

        @BindView(R.id.adapter_chat_message)
        TextView message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
