package com.mkl.cyberpolicebogura.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.utils.SessionManager;

public class GeneralAdminActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_admin);
        sessionManager=new SessionManager(this);
    }
    public void showPop(View view) {
        PopupMenu popup = new PopupMenu(this, view);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.logout_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.setLoggedIn(false);
                FirebaseMessaging.getInstance().unsubscribeFromTopic("cyberPolice");
                startActivity(new Intent(this,LoginActivity.class));
                finishAffinity();
                return true;

            default:
                return false;
        }
    }
    public void ComplaintListActivity(View view) {
        startActivity(new Intent(this,ComplaintsListActivity.class));
    }
    public void PoliceListActivity(View view) {
       // startActivity(new Intent(this,PoliceListActivity.class));
    }
    public void openChat(View view) {
        startActivity(new Intent(this,ChatListActivity.class));
    }
    public void back(View view) {
        onBackPressed();
    }

    public void openMyProfile(View view) {
        startActivity(new Intent(this,ProfileActivityEdit.class));
    }

    public void openTrack(View view) {
        startActivity(new Intent(this,TrackActivity.class));

    }
}
