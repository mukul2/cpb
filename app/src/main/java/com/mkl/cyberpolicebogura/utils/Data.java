package com.mkl.cyberpolicebogura.utils;

import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.Police;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkl on 2/14/2019.
 */

public class Data {
    public static String USER_ID;
    public static String USER_NAME;
    public static String TEMP_LINK;
    public static Complaints tempComplain;
    public static Police tempPolice;
    public static String BaseUrl="http://barakahshop.com/android/apis/";
    public static String PHOTO_BASE="http://barakahshop.com/android/apis/uploads/";
    public static String FACEBOOK_LINK="https://www.facebook.com/cpbogura/";
    public  static List<String> getSubjects(){
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Facebook ID Opening");
        categories.add("Facebook ID Hacking");
        categories.add("Facebook Blackmail");
        categories.add("facebook Threat");
        categories.add("Facebook Spamming");
        categories.add("Threat on EMail");
        categories.add("Email Bombing");
        categories.add("Others");
       
        return categories;
    }
    public  static List<String> getTypes(){
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Facebook");
        categories.add("Email");
        categories.add("Website");
        categories.add("Social Media");
        categories.add("YouTube");
        categories.add("bKash/Rocket/DutchBangla");
        categories.add("Mobile/Sim Scamming");
        categories.add("Virus Attack");
        categories.add("Crypto Currency");
        categories.add("Cyber Bullying");
        categories.add("Defeming");
        categories.add("Sexual Abuse");
        categories.add("Hacking");
        categories.add("Others ");

        return categories;
    }
    public  static List<String> getDistricts(){
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("DHAKA");
        categories.add("FARIDPUR");
        categories.add("GAZIPUR");
        categories.add("GOPALGANJ");
        categories.add("JAMALPUR");
        categories.add("KISHOREGONJ");
        categories.add("MADARIPUR");
        categories.add("MANIKGANJ");
        categories.add("MUNSHIGANJ");
        categories.add("MYMENSINGH");
        categories.add("NARAYANGANJ");
        categories.add("NARSINGDI");
        categories.add("NETRAKONA");
        categories.add("RAJBARI");
        categories.add("SHARIATPUR");
        categories.add("SHERPUR");
        categories.add("TANGAIL");
        categories.add("BARGUNA");
        categories.add("BARISAL");
        categories.add("BHOLA");
        categories.add("JHALOKATI");
        categories.add("PATUAKHALI");
        categories.add("PIROJPUR ");
        categories.add("BANDARBAN");
        categories.add("BRAHMANBARIA");
        categories.add("CHANDPUR");
        categories.add("CHITTAGONG");
        categories.add("COMILLA");
        categories.add("COX'S BAZAR");
        categories.add("COX'S BAZAR");
        categories.add("KHAGRACHHARI");
        categories.add("LAKSHMIPUR");
        categories.add("NOAKHALI");
        categories.add("RANGAMATI ");
        categories.add("BAGERHAT");
        categories.add("CHUADANGA");
        categories.add("JESSORE");
        categories.add("JHENAIDAH");
        categories.add("KHULNA");
        categories.add("KUSHTIA");
        categories.add("MAGURA");
        categories.add("MEHERPUR");
        categories.add("NARAIL");
        categories.add("SATKHIRA");
        categories.add("BOGRA");
        categories.add("CHAPAINABABGANJ");
        categories.add("JOYPURHAT");
        categories.add("PABNA");
        categories.add("NAOGAON");
        categories.add("NATORE");
        categories.add("RAJSHAHI");
        categories.add("SIRAJGANJ");
        categories.add("DINAJPUR");
        categories.add("GAIBANDHA");
        categories.add("KURIGRAM");
        categories.add("LALMONIRHAT");
        categories.add("NILPHAMARI");
        categories.add("PANCHAGARH");
        categories.add("RANGPUR");
        categories.add("THAKURGAON");
        categories.add("HABIGANJ");
        categories.add("MAULVIBAZAR");
        categories.add("SUNAMGANJ");
        categories.add("SYLHET");
        return categories;
    }

}
