package com.mkl.cyberpolicebogura.utils;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.activity.SendComplaintActivity;
import com.mkl.cyberpolicebogura.adapter.CyberPoliceChatListAdapter;
import com.mkl.cyberpolicebogura.adapter.CyberPoliceListAdapter;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapter;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapterOnline;
import com.mkl.cyberpolicebogura.api.ApiListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mkl on 2/14/2019.
 */

public class Initials {
    public static Spinner setSubjectsSpinner(Spinner spinner, Context context, List<String> subjects, final ApiListener.SubjectSpinnerListener spinnerListener) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerListener.onSubjectClicked(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return spinner;
    }

    public static Spinner setDistrictSpinner(Spinner spinner, Context context, List<String> subjects, final ApiListener.DistrictSpinnerListener spinnerListener) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerListener.onDistrictClicked(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return spinner;
    }
    public static Spinner setTypeSpinner(Spinner spinner, Context context, List<String> subjects, final ApiListener.TypeSpinnerListener spinnerListener) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerListener.onTypeClicked(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return spinner;
    }

    public static RecyclerView setGallaryADapter(GallaryAdapter mAdapter, RecyclerView recyclerView, Context context, List<String> subjects) {

        //mAdapter = new GallaryAdapter(context,subjects);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(_sGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return recyclerView;
    }

    public static RecyclerView setGallaryADapterOnline(GallaryAdapterOnline mAdapter, RecyclerView recyclerView, Context context, List<String> subjects) {

        //mAdapter = new GallaryAdapter(context,subjects);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(_sGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return recyclerView;
    }

    public static RecyclerView setCyberPoliceListADapter(CyberPoliceListAdapter mAdapter, RecyclerView recyclerView, Context context) {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
        return recyclerView;
    }
    public static RecyclerView setCyberPoliceChatListADapter(CyberPoliceChatListAdapter mAdapter, RecyclerView recyclerView, Context context) {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
        return recyclerView;
    }
    public static String getUserType(String type) {
        String ret="";
        if (type.equals("0"))
            ret="Administrator";
        if (type.equals("1")){
            ret="Admin";
        }
        return  ret;
    }

    public static String changeDateformate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd yyyy");
        return  targetFormat.format(sourceDate);
    }
}
