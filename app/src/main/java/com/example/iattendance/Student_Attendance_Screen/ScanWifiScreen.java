package com.example.iattendance.Student_Attendance_Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iattendance.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ScanWifiScreen extends AppCompatActivity {
    private WifiManager wifiManager;
    private ListView listView;
    private MaterialButton scan_btn;
    private final int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_wifi_screen);

        scan_btn = findViewById(R.id.scan_btn);
        listView = findViewById(R.id.listView);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ScanWifiScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Permission not granted, request it
                    ActivityCompat.requestPermissions(ScanWifiScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    // Permission granted, proceed with scanning
                    scanWifi();
                }
            }
        });

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "Wifi is disabled... Please enable wifi!", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with scanning
                scanWifi();
            } else {
                // Permission denied, handle accordingly (e.g., display a message)
                Toast.makeText(this, "Permission denied. Unable to scan for Wi-Fi networks.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanWifi() {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning Wifi...", Toast.LENGTH_LONG).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for (ScanResult scanResult : results) {
                arrayList.add(scanResult.SSID);
            }
            adapter.notifyDataSetChanged();
        }
    };


}