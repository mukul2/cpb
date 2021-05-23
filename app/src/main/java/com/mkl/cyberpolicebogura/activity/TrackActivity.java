package com.mkl.cyberpolicebogura.activity;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.api.ApiClientGSM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TrackActivity extends AppCompatActivity {
    EditText mcc, mnc, lac, cid, lat, log;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mcc = (EditText) findViewById(R.id.ed_mcc);
        mnc = (EditText) findViewById(R.id.ed_mnc);
        lac = (EditText) findViewById(R.id.ed_lac);
        cid = (EditText) findViewById(R.id.ed_cid);
        lat = (EditText) findViewById(R.id.ed_lat);
        log = (EditText) findViewById(R.id.ed_log);
        address = (TextView) findViewById(R.id.ed_address);
    }

    public void back(View view) {
    }

    public void search(View view) {
        String smcc = mcc.getText().toString().trim();
        String smnc = mnc.getText().toString().trim();
        String slac = lac.getText().toString().trim();
        String scid = cid.getText().toString().trim();
        ApiClientGSM.getApiInterface().getTrack(smcc, smnc, slac, scid).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {



                try {

                    Document doc = Jsoup.parse(response.body());
                    Elements links = doc.select("a[href]");
                    String[] separated = links.get(0).text().split(" ");
                    // Toast.makeText(TrackActivity.this, separated[0].toString()+"\n"+ separated[1].toString(), Toast.LENGTH_LONG).show();
                   // Toast.makeText(TrackActivity.this, response.body(), Toast.LENGTH_LONG).show();
                    lat.setText(separated[0].toString().replace("Lat=",""));
                    log.setText(separated[1].toString().replace("Lon=",""));
                    address.setText(getLocationAddress(Double.parseDouble(separated[0].toString().replace("Lat=","")), Double.parseDouble(separated[1].toString().replace("Lon=",""))));
                } catch (Exception e) {
                   // Toast.makeText(TrackActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    lat.setText("Not Found");
                    log.setText("Not Found");
                    address.setText("Not Found");

                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(TrackActivity.this, t.getMessage() + "\n" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String getLocationAddress(double latitude, double longitude) {
        String addres = "";
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
            addres = address;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addres;
    }
}
