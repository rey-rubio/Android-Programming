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

public class Temperature extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private SensorEventListener temperatureEventListener;

    private TextView textView_temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        textView_temperature = findViewById(R.id.textView_temperature);

        if (temperatureSensor == null) {
            Toast.makeText(this, "The device does not have a temperature sensor!", Toast.LENGTH_SHORT).show();
            finish();
        }
        temperatureEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                getSupportActionBar().setTitle("Temperature: " + value + " degrees");
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
        sensorManager.registerListener(temperatureEventListener, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(temperatureEventListener);
    }
}


