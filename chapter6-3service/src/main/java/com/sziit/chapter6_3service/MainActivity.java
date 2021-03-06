package com.sziit.chapter6_3service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ServiceConnection serviceConnection;
    private DownloadBinder downloadBinder;

    private Button mStartDownload;
    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnbindService;
    private Button mGetDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                downloadBinder=(DownloadBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    private void initView() {
        mStartDownload = (Button) findViewById(R.id.start_download);
        mStartService = (Button) findViewById(R.id.start_service);
        mStopService = (Button) findViewById(R.id.stop_service);
        mBindService = (Button) findViewById(R.id.bind_service);
        mUnbindService = (Button) findViewById(R.id.unbind_service);
        mGetDownload = (Button) findViewById(R.id.get_download);

        mStartDownload.setOnClickListener(this);
        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
        mBindService.setOnClickListener(this);
        mUnbindService.setOnClickListener(this);
        mGetDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Log.d("????????? 1907040231", "onClick: ????????????");
                Intent intent=new Intent(this, MyService.class);
                startService(intent);//????????????
                break;
            case R.id.stop_service:
                Log.d("????????? 1907040231", "onClick: ????????????");
                Intent intent2=new Intent(this, MyService.class);
                stopService(intent2);//????????????
                break;
            case R.id.bind_service:
                Log.d("????????? 1907040231", "onClick: ????????????");
                Intent intent3=new Intent(this, MyService.class);
                bindService(intent3,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                Log.d("????????? 1907040231", "onClick: ??????????????????");
                unbindService(serviceConnection);
                break;
            case R.id.start_download:
                downloadBinder.startDownload();//????????????
                break;
            case R.id.get_download:
                downloadBinder.getDownload();//??????????????????
                break;
        }
    }
}
