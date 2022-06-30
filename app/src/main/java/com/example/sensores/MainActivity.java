package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView, textMagnetometro;
    private SensorManager sensorManager;
    private Sensor sensor, magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textAcelerometro);
        textMagnetometro = findViewById(R.id.textMagnetometro);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,sensor, sensorManager.SENSOR_DELAY_NORMAL);

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(magnetometer != null){
            sensorManager.registerListener(MainActivity.this, magnetometer, sensorManager.SENSOR_DELAY_NORMAL);
        }else{
            textMagnetometro.setText("No sensor");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            textView.setText("valor de x: " +sensorEvent.values[0] + "\n"
                    + "valor de y: " + sensorEvent.values[1] + "\n"
                    + "valor de z: " + sensorEvent.values[2]);
        }
        if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            textMagnetometro.setText(
                    "valor de x: " +sensorEvent.values[0] + "\n"
                            + "valor de y: " + sensorEvent.values[1] + "\n"
                            + "valor de z: " + sensorEvent.values[2]
            );
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}












