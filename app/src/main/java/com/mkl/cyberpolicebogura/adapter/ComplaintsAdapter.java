package com.mkl.cyberpolicebogura.adapter;

/**
 * Created By TAOHID on 10/25/18.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.activity.ComplaintDetailActivity;
import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.utils.Data;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




/**
 * Created by ravi on 16/11/17.
 */

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.MyViewHolder> {
    private Context context;
    private List<Complaints> contactListFiltered;
    private NotificationAdapterListener listener;
    String SEEN="1";
    String UNSEEN="0";

    public void setListener(NotificationAdapterListener l) {
        this.listener = l;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_subject, tv_district, tv_body, tv_date,tv_attachment_count;
        RelativeLayout body,body3;
        LinearLayout body2;


        public MyViewHolder(View view) {
            super(view);
            tv_subject = view.findViewById(R.id.tv_subject);
            tv_district = view.findViewById(R.id.tv_district);
            tv_body = view.findViewById(R.id.tv_body);
            tv_date = view.findViewById(R.id.tv_date);
            tv_attachment_count = view.findViewById(R.id.tv_attachment_count);
            body = view.findViewById(R.id.body);
            body2 = view.findViewById(R.id.body2);
            body3 = view.findViewById(R.id.body3);




        }
    }


    public ComplaintsAdapter(Context context, List<Complaints> contactList) {
        this.context = context;
        this.listener = listener;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_item_dark, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Complaints contact = contactListFiltered.get(position);
//      holder.tv_body.setText(contact.getBody());
        holder.tv_date.setText(contact.getTime());
        holder.tv_district.setText(contact.getDistrict());
        holder.tv_subject.setText(contact.getComplaintSub());
        holder.tv_date.setText(changeDateformate(contact.getTime()));
        holder.tv_attachment_count.setText(getAttachmentCount(contact.getAttachment()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.tempComplain=contact;
                context.startActivity(new Intent(context, ComplaintDetailActivity.class));
                ((Activity)context).finish();
            }
        });
        if (contact.getSeen().equals(SEEN)){
            holder.body.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.body2.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.body3.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.tv_date.setTextColor(context.getResources().getColor(R.color.green));//
            holder.tv_date.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.tv_district.setTextColor(context.getResources().getColor(R.color.green));//
            holder.tv_district.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.tv_subject.setTextColor(context.getResources().getColor(R.color.green));//
            holder.tv_subject.setBackgroundColor(context.getResources().getColor(R.color.black));//
            holder.tv_date.setTextColor(context.getResources().getColor(R.color.green));//
            holder.tv_date.setBackgroundColor(context.getResources().getColor(R.color.black));
            holder.tv_attachment_count.setTextColor(context.getResources().getColor(R.color.green));//
            holder.tv_attachment_count.setBackgroundColor(context.getResources().getColor(R.color.black));//
        }else {
            holder.body.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.body2.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.body3.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.tv_date.setTextColor(Color.BLACK);
            holder.tv_date.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.tv_district.setTextColor(Color.BLACK);
            holder.tv_district.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.tv_subject.setTextColor(Color.BLACK);
            holder.tv_subject.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.tv_date.setTextColor(Color.BLACK);
            holder.tv_date.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.tv_attachment_count.setTextColor(Color.BLACK);
            holder.tv_attachment_count.setBackgroundColor(context.getResources().getColor(R.color.green));


        }



    }

    private String getAttachmentCount(String attachment) {
        String ret="0 attachment";
        try {
            JSONArray jsonArray=new JSONArray(attachment);
            ret=String.valueOf(jsonArray.length())+" attachment";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  ret;
    }

    private String changeDateformate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd");
       return  targetFormat.format(sourceDate);
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();

    }


    public interface NotificationAdapterListener {
        void onNotificationSelected(Complaints contact);
    }
}