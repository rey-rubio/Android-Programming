package com.example.lab2a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MagneticField extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor magneticfieldSensor;
    private SensorEventListener magneticfieldEventListener;

    private TextView textView_magneticfield_X;
    private TextView textView_magneticfield_Y;
    private TextView textView_magneticfield_Z;
    private float timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_field);

        getSupportActionBar().setTitle("Magnetic Field (uT)");

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magneticfieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //textView_magneticfield = findViewById(R.id.textView_magneticfield);

        textView_magneticfield_X = findViewById(R.id.textView_magneticfield_X);
        textView_magneticfield_Y = findViewById(R.id.textView_magneticfield_Y);
        textView_magneticfield_Z = findViewById(R.id.textView_magneticfield_Z);

        if (magneticfieldSensor == null) {
            Toast.makeText(this, "The device does not have a magnetic field sensor!", Toast.LENGTH_SHORT).show();
            finish();
        }
        magneticfieldEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // This timestep's delta rotation to be multiplied by the current rotation
                // after computing it from the gyro sample data.
                if (timestamp != 0) {
                    // Axis of the rotation sample, not normalized yet.
                    float axisX = event.values[0];
                    float axisY = event.values[1];
                    float axisZ = event.values[2];


                    textView_magneticfield_X.setText(String.valueOf(axisX));
                    textView_magneticfield_Y.setText(String.valueOf(axisY));
                    textView_magneticfield_Z.setText(String.valueOf(axisZ));
                }
                timestamp = event.timestamp;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(magneticfieldEventListener, magneticfieldSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(magneticfieldEventListener);
    }
}
