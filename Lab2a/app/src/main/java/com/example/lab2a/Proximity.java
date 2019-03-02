package com.example.lab2a;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;

public class Proximity extends AppCompatActivity{

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximityEventListener;


    private TextView textView_proximity;
    private float timestamp;
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);


        getSupportActionBar().setTitle("Proximity");

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        textView_proximity = findViewById(R.id.textView_proximity);

//        if (proximitySensor == null) {
//            Toast.makeText(this, "The device does not have a proximity sensor!", Toast.LENGTH_SHORT).show();
//            finish();
//        }
        proximityEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (timestamp != 0) {
                    float value = sensorEvent.values[0];
                    getSupportActionBar().setTitle("Proximity: " + value + " cm");
                    if (value == 0) {
                        textView_proximity.setText("Near");
                    } else {
                        textView_proximity.setText("Far");
                    }
                }
                timestamp = sensorEvent.timestamp;





            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }




    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(proximityEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(proximityEventListener);
    }
}
