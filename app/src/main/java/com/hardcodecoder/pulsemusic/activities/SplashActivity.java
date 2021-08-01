package com.hardcodecoder.pulsemusic.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hardcodecoder.pulsemusic.R;
import com.hardcodecoder.pulsemusic.loaders.LoaderHelper;
import com.hardcodecoder.pulsemusic.storage.AppFileManager;

public class SplashActivity extends PMBActivity {

    private static final int REQUEST_CODE = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getPermission();
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startMusicLoader();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startMusicLoader();
            } else {
                // Permission was not granted
                Toast.makeText(this, "App needs to access device storage to work", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(this::finish, 1500);
            }
        }
    }

    private void startHomeActivity() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 400);
    }

    private void startMusicLoader() {
        LoaderHelper.loadAllTracks(getContentResolver(), result -> {
            AppFileManager.deleteObsoleteHistoryFiles();
            startHomeActivity();
        });
    }
}

