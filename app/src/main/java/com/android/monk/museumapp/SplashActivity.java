package com.android.monk.museumapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

public class SplashActivity extends AppCompatActivity {

    private DilatingDotsProgressBar splashProgress;
    private TextView tvVersionApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashProgress = (DilatingDotsProgressBar) findViewById(R.id.splashProgress);
        tvVersionApp = (TextView) findViewById(R.id.tvAppVersion);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashProgress.show();
        loadVersionApp();
    }

    private void loadVersionApp() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            tvVersionApp.setText("V."+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
