package com.android.monk.museumapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

public class SplashActivity extends AppCompatActivity {

    private DilatingDotsProgressBar splashProgress;
    private TextView tvVersionApp;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;

        splashProgress = (DilatingDotsProgressBar) findViewById(R.id.splashProgress);
        tvVersionApp = (TextView) findViewById(R.id.tvAppVersion);

        checkRequirementAndInitialize(mContext);
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

    private void displayErrorDialog(Context context, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        TextView dialogTextView = new TextView(context);
        dialogTextView.setText(msg);
        dialogTextView.setGravity(Gravity.CENTER);
        dialog.setView(dialogTextView);
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                checkRequirementAndInitialize(mContext);
            }
        });
        dialog.show();
    }

    private boolean isConnectedInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    private void checkRequirementAndInitialize(Context context) {
        if (isConnectedInternet(context)) {
            goToMainMenuScreen();
        } else {
            displayErrorDialog(context, context.getResources().getString(R.string.internet_error));
        }
    }

    private void goToMainMenuScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashProgress.hide();
                Intent mainMenuIntent = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
                finish();
            }
        }, 3000);
    }
}
