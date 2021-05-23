package com.mkl.cyberpolicebogura.adapter;

/**
 * Created By TAOHID on 10/25/18.
 */

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.model.Complaints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.mkl.cyberpolicebogura.utils.Data.BaseUrl;


/**
 * Created by ravi on 16/11/17.
 */

public class GallaryAdapter extends RecyclerView.Adapter<GallaryAdapter.MyViewHolder> {
    private Context context;
    private List<String> contactListFiltered;
    private NotificationAdapterListener listener;

    public void setListener(NotificationAdapterListener l) {
        this.listener = l;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView,img_cancel;


        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
            img_cancel = view.findViewById(R.id.img_cancel);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                  //  listener.onNotificationSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public GallaryAdapter(Context context, List<String> contactList) {
        this.context = context;
        this.listener = listener;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String contact = contactListFiltered.get(position);
        if (contact.length()>0)
        holder.imageView.setImageURI(Uri.parse(contact));
        //Glide.with(context).load(Uri.parse(contact)).into(holder.imageView);

        holder.img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //contactListFiltered.remove(position);
                //notifyDataSetChanged();
                if (removeItem(position)) {
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            }
        });






    }
    public boolean removeItem(int position) {
        if (contactListFiltered.size() >= position + 1) {
            contactListFiltered.remove(position);
            return true;
        }
        return false;
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