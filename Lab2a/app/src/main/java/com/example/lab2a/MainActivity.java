package com.example.lab2a;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button lightButton;
    private Button accelerometerButton;
    private Button proximityButton;
    private Button cameraButton;
    private Button videoButton;

    static final int REQUEST_TAKE_PHOTO = 1;

    static final int REQUEST_VIDEO_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Lab2 Main Menu");

        lightButton = findViewById(R.id.lightButton);
        accelerometerButton = findViewById(R.id.accelerometerButton);
        proximityButton = findViewById(R.id.proximityButton);
        cameraButton = findViewById(R.id.cameraButton);
        videoButton = findViewById(R.id.videoButton);

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Light.class);
                startActivity(intent);
            }
        });
        accelerometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Accelerometer.class);
                startActivity(intent);
            }
        });

        proximityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Proximity.class);
                startActivity(intent);
            }
        });


        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Camera.class);
                startActivity(intent);
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//
//                }

                Intent intent = new Intent(getApplicationContext(), Video.class);
                startActivity(intent);
            }


        });


//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                // Ensure that there's a camera activity to handle the intent
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    // Create the File where the photo should go
//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    } catch (IOException ex) {
//                        // Error occurred while creating the File
//                    }
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        Uri photoURI = FileProvider.getUriForFile(this,
//                                "com.example.android.fileprovider",
//                                photoFile);
//                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//                    }
//                }
    }


    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
