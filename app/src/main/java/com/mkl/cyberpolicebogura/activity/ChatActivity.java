package com.mkl.cyberpolicebogura.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.ChatAdapter;
import com.mkl.cyberpolicebogura.adapter.ComplaintsAdapter;
import com.mkl.cyberpolicebogura.model.ChatMessage;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.HexagonMaskView;
import com.mkl.cyberpolicebogura.utils.LinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

import static com.mkl.cyberpolicebogura.utils.Data.PHOTO_BASE;
import static com.mkl.cyberpolicebogura.utils.Data.USER_ID;

public class ChatActivity extends AppCompatActivity {
    TextView tv_name;
    String oppositeID;
    HexagonMaskView imageView;
    Context context = this;
    DatabaseReference databaseReference;
    EditText chat_edit_text1;
    String roomName = "";
    List<ChatMessage> chatMessages = new ArrayList<>();
    RecyclerView recyclerView;
    ChatAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tv_name = (TextView) findViewById(R.id.tv_name);
        chat_edit_text1 = (EditText) findViewById(R.id.chat_edit_text1);
        imageView = (HexagonMaskView) findViewById(R.id.imageView);
        //Toast.makeText(context, USER_ID, Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Police police = Data.tempPolice;
        tv_name.setText(police.getName());
        oppositeID = police.getId();
        roomName = createChatName(Integer.valueOf(Integer.valueOf(USER_ID)), Integer.valueOf(police.getId()));
       // Toast.makeText(context, roomName, Toast.LENGTH_SHORT).show();
         Glide.with(context).load(PHOTO_BASE+police.getPhoto()).into(imageView);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new ChatAdapter(ChatActivity.this,chatMessages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        databaseReference.child("chat").child(roomName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatMessages.add(dataSnapshot.getValue(ChatMessage.class));
               // Toast.makeText(context, "" + chatMessages.size(), Toast.LENGTH_SHORT).show();
                mAdapter.notifyItemInserted(chatMessages.size()-1);
                recyclerView.smoothScrollToPosition(chatMessages.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public String createChatName(Integer myID, Integer partnerID) {
        String ret = "";
        if (myID < partnerID)
            ret = String.valueOf(myID) + "-" + String.valueOf(partnerID);
        else
            ret = String.valueOf(partnerID) + "-" + String.valueOf(myID);

        return ret;
    }

    public void back(View view) {
        onBackPressed();
    }

    public void sendMessage(View view) {
        String message = chat_edit_text1.getText().toString().trim();
        chat_edit_text1.setText("");
        if (message.length() > 0) {
            ChatMessage chatMessage = new ChatMessage(USER_ID, oppositeID, message);
            String key = databaseReference.child("chat").child(roomName).push().getKey();
            databaseReference.child("chat").child(roomName).child(key).setValue(chatMessage);
        }
    }
}
