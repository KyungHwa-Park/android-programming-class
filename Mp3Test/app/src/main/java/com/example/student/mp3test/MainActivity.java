package com.example.student.mp3test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    boolean bReadPerm = false;
    boolean bWritePerm = false;
    Button btn_play, btn_stop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_play.setOnClickListener(new MyButtonListener());
        btn_stop.setOnClickListener(new MyButtonListener());

        player = new MediaPlayer();

        if(bReadPerm && bWritePerm) {
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)) {
                try {       // sd card 경로
                    String musicPath = Environment.getExternalStorageDirectory()
                                                .getAbsolutePath() + "/song1.mp3";
                    player.setDataSource(musicPath);
                    player.prepare();
                    Log.d("PlayMp3", "mp3 file ");

                } catch(Exception e) {
                    Log.d("playMP3", "mp3 file error");
                }
            }
        }
    }

    private void setPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bReadPerm = true;
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
            bWritePerm = true;
        }

        if(!bReadPerm && !bWritePerm) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    200);
        }
    }

    private class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play:
                    if (player.isPlaying()) {
                        player.pause();
                        btn_play.setText("play");
                    } else {
                        player.start();
                        btn_play.setText("pause");
                    }
                    break;
                case R.id.btn_stop:
                    player.stop();
                    try {
                        player.prepare();
                    } catch (Exception e) {
                        Log.d("PlayMp3", "mp3 file error");
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bWritePerm = true;
            }
        }
    }
}
