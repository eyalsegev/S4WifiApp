package com.s4.s4wifiapp;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WifiManager itsWifiManager;
    private Switch swWifiOnOff;
    private Boolean swWifiState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWifiController();

        // initiate a Switch
        swWifiOnOff = (Switch) findViewById(R.id.swWifiOnOff);
        swWifiOnOff.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        swWifiOnOff.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off state

        if (itsWifiManager.isWifiEnabled())
            swWifiOnOff.setChecked(true);

        // check current state of a Switch (true or false).
        swWifiState = swWifiOnOff.isChecked();
        if (swWifiState)
            swWifiOnOff.setTextColor(Color.GREEN); //Green color for displayed text of Switch
        else
            swWifiOnOff.setTextColor(Color.RED); //Red color for displayed text of Switchs

        swWifiOnOff.setOnClickListener(this);
    }

    public void setWifiController() {
        itsWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public void onClick(View v) {
        swWifiState = swWifiOnOff.isChecked();
        if (!swWifiState) {
            itsWifiManager.setWifiEnabled(false);
            swWifiOnOff.setTextColor(Color.RED); //Red color for displayed text of Switchs
            Toast.makeText(this, "Wifi is OFF", Toast.LENGTH_SHORT).show();
        } else {
            itsWifiManager.setWifiEnabled(true);
            swWifiOnOff.setTextColor(Color.GREEN); //Green color for displayed text of Switch
            Toast.makeText(this, "Wifi is ON", Toast.LENGTH_SHORT).show();
        }

    }
}
