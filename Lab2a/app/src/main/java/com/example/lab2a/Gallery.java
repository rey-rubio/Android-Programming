package com.example.lab2a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;

public class Gallery extends AppCompatActivity {

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setTitle("Gallery");

        imgView =(ImageView)findViewById(R.id.imageView_gallery);

        Bitmap bmp = null;
        String filename = getIntent().getStringExtra("image");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            bmp = fixOrientation(bmp);
            imgView.setImageBitmap(bmp);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap fixOrientation(Bitmap mBitmap) {
        if (mBitmap.getWidth() > mBitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            mBitmap = Bitmap.createBitmap(mBitmap , 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        }
        return mBitmap;
    }
}
