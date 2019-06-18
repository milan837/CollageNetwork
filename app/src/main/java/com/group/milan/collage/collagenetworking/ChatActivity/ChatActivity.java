package com.group.milan.collage.collagenetworking.ChatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group.milan.collage.collagenetworking.ChatActivity.pojo.ChatPojo;
import com.group.milan.collage.collagenetworking.R;
import com.group.milan.collage.collagenetworking.Utils.UtilsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.back_icon)
    ImageView backImage;

    @BindView(R.id.swipe_to_refresh_chat_box_fragment)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.activity_chat_box_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.activity_chat_box_message_box)
    EditText messageText;

    @BindView(R.id.activity_chat_box_send_msg_btn)
    ImageView sendBtn;

    @BindView(R.id.activity_chat_box_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.activity_chat_box_down_arrow)
    RelativeLayout downArrorwBtn;

    List<ChatPojo> list;
    List listKey;
    DatabaseReference databaseReference,messageReference;
    ChatRecyclerViewAdapter adapter;

    int firstVisibleItem;
    boolean loadingStatus=false;
    boolean messageClickScroll=false;
    LinearLayoutManager layoutManager;

    String projectId,username,userId,color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);

        list=new ArrayList<>();
        listKey=new ArrayList<>();

        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);
        username=sharedPreferences.getString("username",null);

        projectId=getIntent().getStringExtra("project_id");
        databaseReference=FirebaseDatabase.getInstance().getReference();
        messageReference=databaseReference.child("chats").child(projectId);

        progressBar.setVisibility(View.VISIBLE);
        color= UtilsClass.getColor();
        initViews();

    }

    public void initViews(){

        adapter=new ChatRecyclerViewAdapter(this,list);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        final long timeStamp=new Date().getTime();
        Query chatsQuery=messageReference.orderByChild("time").endAt(timeStamp).limitToLast(20);
        chatsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                for(DataSnapshot mydata:dataSnapshot.getChildren()){
                    ChatPojo chatPojo=mydata.getValue(ChatPojo.class);
                    list.add(chatPojo);
                    listKey.add(mydata.getKey());
                    adapter.notifyDataSetChanged();
                }
                addListner();
                scrollToBottom();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //sending message
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getMessage=messageText.getText().toString().trim();
                if(getMessage.isEmpty()){
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                }else{
                    messageText.setText("");
                    sendMessage(username,userId,getMessage);
                }
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //SWAPPER FOR LOADMORE MESSAGE
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                firstVisibleItem=layoutManager.findFirstVisibleItemPosition();
                swipeRefreshLayout.setRefreshing(false);
                if(firstVisibleItem == 0 && loadingStatus== false){
                    loadMoreMessage(list.get(0).getTime()-1);
                }
            }
        });


        //scrolling for showing scroll down button icon
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy < 0){
                    //configuration for the scroll down button
                    int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    if(lastVisibleItem < list.size()-3) {
                        downArrorwBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //SCROLLDOWN ICON IN CHATBOX
        downArrorwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollToBottom();
                downArrorwBtn.setVisibility(View.GONE);
            }
        });



    }

    //load more pagination call
    public void loadMoreMessage(long timeStamp){
        messageReference=databaseReference.child("chats").child(projectId);
        Query query=messageReference.orderByChild("time").endAt(timeStamp).limitToLast(20);
        final List<ChatPojo> reverseList=new ArrayList<>();
        final List reverseListKey=new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    ChatPojo chatPojo=data.getValue(ChatPojo.class);
                    reverseList.add(chatPojo);
                    reverseListKey.add(data.getKey());
                }

                Collections.reverse(reverseList);
                Collections.reverse(reverseListKey);

                for(int i=0;i<reverseList.size();i++){
                    list.add(0,reverseList.get(i));
                    listKey.add(reverseListKey.get(i));
                }

                adapter.notifyItemRangeInserted(0,reverseList.size());
                recyclerView.scrollToPosition(reverseList.size()+2);
                loadingStatus=false;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"inter connection erro",Toast.LENGTH_LONG).show();
            }

        });
    }


    //scroll to bottom
    public void scrollToBottom(){
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
        downArrorwBtn.setVisibility(View.GONE);
    }

    //add child listner
    public void addListner(){
        messageReference=databaseReference.child("chats").child(projectId);
        long currentTime=new Date().getTime()+1;
        Query query=messageReference.orderByChild("time").startAt(currentTime);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("milan_fire_aa",dataSnapshot.getKey());
                ChatPojo chatPojo=dataSnapshot.getValue(ChatPojo.class);
                list.add(chatPojo);
                listKey.add(dataSnapshot.getKey());
                adapter.notifyItemChanged(list.size());

                //configuration for the scroll down button
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                Log.i("milan_mil", String.valueOf(lastVisibleItem)+"->"+list.size());

                if(messageClickScroll == true){
                    scrollToBottom();
                    messageClickScroll=false;
                }else {
                    if (lastVisibleItem < list.size() - 2) {
                        downArrorwBtn.setVisibility(View.VISIBLE);
                    } else if (lastVisibleItem == list.size() - 1 || lastVisibleItem == list.size()) {
                        //SCROLLING tO BOTTOM
                        scrollToBottom();
                    }else{
                        scrollToBottom();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                int position=listKey.indexOf(dataSnapshot.getKey());
//                ChatBoxPojo chatBoxPojo=dataSnapshot.getValue(ChatBoxPojo.class);
//                list.remove(position);
//                list.add(position,chatBoxPojo);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void sendMessage(String username,String userId,String message){

        final long timeStamp=new Date().getTime();
        ChatPojo chatPojo=new ChatPojo(username, message, userId, timeStamp,color);
        String pushKey=messageReference.push().getKey();
        messageReference.child(pushKey).setValue(chatPojo);
    }

}
