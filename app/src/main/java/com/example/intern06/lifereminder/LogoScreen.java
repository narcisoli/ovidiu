package com.example.intern06.lifereminder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.intern06.lifereminder.fragmente.reminderfragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogoScreen extends AppCompatActivity {

    private int qwesdfsdfsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_screen);
        Intent mainIntent = new Intent(LogoScreen.this, Login.class);
        startActivity(mainIntent);
        finish();




    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


}

