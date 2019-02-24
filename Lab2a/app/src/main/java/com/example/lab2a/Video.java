package com.example.lab2a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Video extends AppCompatActivity {

    VideoView vidView;
    private Button takeVidButton;
    private Button galleryButton;

    public final static int PICK_VIDEO_CODE = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setTitle("Video");

        vidView = findViewById(R.id.vidView);

        takeVidButton = findViewById(R.id.btn_takeVideo);
        takeVidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeVideo(v);
            }
        });

        galleryButton = findViewById(R.id.btn_gallery2);
        galleryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                pickVideo(v);
            }

        });

    }

    public void takeVideo(View view){
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(takeVideoIntent);
        }
    }


    // Trigger gallery selection for a photo
    public void pickVideo(View view) {

        System.out.println("pickPhoto: PICKING VIDEO");
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Video.Media.INTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_VIDEO_CODE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult: PICKING VIDEO");
        if (data != null) {

            System.out.println("onActivityResult: SETTING VIDEO");
            Uri vidUri = data.getData();
            vidView.setVideoURI(vidUri);

            vidView.setVisibility(View.VISIBLE);
            vidView.setFocusable(true);
            vidView.setZOrderOnTop(true);
            vidView.start();
        }

    }
}
