package com.mkl.cyberpolicebogura.utils;

/**
 * Created by hrsoftbd_mk_1 on 2/5/2018.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mkl on 2/8/2018.
 */

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setToken(String nid) {
        prefs.edit().putString("token", nid).commit();
    }

    public String getToken() {
        String nid = prefs.getString("token","");
        return nid;
    }
    public void setLoggedIn(boolean nid) {
        prefs.edit().putBoolean("loggedIn", nid).commit();
    }

    public boolean getLoggedIn() {
        boolean nid = prefs.getBoolean("loggedIn",false);
        return nid;
    }
    public void setSuperAdmin(boolean nid) {
        prefs.edit().putBoolean("superAdmin", nid).commit();
    }

    public boolean isSuperAdmin() {
        boolean nid = prefs.getBoolean("superAdmin",false);
        return nid;
    }
    public void setuserType(int nid) {
        prefs.edit().putInt("userType", nid).commit();
    }

    public int getUserType() {
        int nid = prefs.getInt("userType",0);
        return nid;
    }

    public void setuserName(String nid) {
        prefs.edit().putString("userName", nid).commit();
    }

    public String getUserName() {
        String nid = prefs.getString("userName","");
        return nid;
    }

    public void setuserId(String nid) {
        prefs.edit().putString("id", nid).commit();
    }

    public String getUserId() {
        String nid = prefs.getString("id","");
        return nid;
    }


    public void setSound(boolean nid) {
        prefs.edit().putBoolean("sound", nid).commit();
    }

    public boolean getsound() {
        boolean nid = prefs.getBoolean("sound",true);
        return nid;
    }
    public void setnewCaseNoti(boolean nid) {
        prefs.edit().putBoolean("newCaseNoti", nid).commit();
    }

    public boolean getnewCaseNoti() {
        boolean nid = prefs.getBoolean("newCaseNoti",true);
        return nid;
    }
    public void setNewMsgNoti(boolean nid) {
        prefs.edit().putBoolean("newMsgNoti", nid).commit();
    }

    public boolean getNewMsgNoti() {
        boolean nid = prefs.getBoolean("newMsgNoti",true);
        return nid;
    }
    public void set_lang(String usename) {
        prefs.edit().putString("lang", usename).commit();
    }

    public String get_lang() {
        String id = prefs.getString("lang","bn");
        return id;
    }
    public void set_userPhoto(String usename) {
        prefs.edit().putString("userPhoto", usename).commit();
    }

    public String get_userPhoto() {
        String id = prefs.getString("userPhoto","");
        return id;
    }
}