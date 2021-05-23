package com.mkl.cyberpolicebogura.adapter;




import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.activity.ChatActivity;
import com.mkl.cyberpolicebogura.activity.ProfileActivity;
import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.utils.HexagonMaskView;

import java.util.List;

import static com.mkl.cyberpolicebogura.utils.Data.PHOTO_BASE;
import static com.mkl.cyberpolicebogura.utils.Data.USER_ID;
import static com.mkl.cyberpolicebogura.utils.Data.tempPolice;
import static com.mkl.cyberpolicebogura.utils.Initials.getUserType;


public class CyberPoliceChatListAdapter extends RecyclerView.Adapter<CyberPoliceChatListAdapter.MyViewHolder> {
    private Context context;
    private List<Police> contactListFiltered;
    private NotificationAdapterListener listener;

    public void setListener(NotificationAdapterListener l) {
        this.listener = l;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_designation, tv_body, tv_date;
        LinearLayout notification_row;
        HexagonMaskView imageView;


        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_body = view.findViewById(R.id.tv_body);
            tv_designation = view.findViewById(R.id.tv_designation);
            imageView = view.findViewById(R.id.imageView);




        }
    }


    public CyberPoliceChatListAdapter(Context context, List<Police> contactList) {
        this.context = context;
        this.listener = listener;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.police_item_dark, parent, false);

        return new MyViewHolder(itemView);
    }
//https://gl-images.condecdn.net/image/lN39xbMKeop/crop/405/f/Gal-Gadot-1.jpg
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Police contact = contactListFiltered.get(position);
        holder.tv_name.setText(contact.getName());
        holder.tv_body.setText("Open Chat");
        holder.tv_designation.setText(getUserType(contact.getType()));
       // Toast.makeText(context,contact.getPhoto() , Toast.LENGTH_SHORT).show();
        Glide.with(context).load(PHOTO_BASE+contact.getPhoto()).into(holder.imageView);
        //Glide.with(context).load("https://gl-images.condecdn.net/image/lN39xbMKeop/crop/405/f/Gal-Gadot-1.jpg").into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tempPolice=contact;
               context.startActivity(new Intent(context, ChatActivity.class));
               // Toast.makeText(context, USER_ID, Toast.LENGTH_SHORT).show();
            }
        });







    }





    @Override
    public int getItemCount() {
        return contactListFiltered.size();

    }


    public interface NotificationAdapterListener {
        void onNotificationSelected(Complaints contact);
    }
}