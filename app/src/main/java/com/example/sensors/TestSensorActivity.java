package com.example.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TestSensorActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tvValor;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_sensor_activity);

        int position = getIntent().getIntExtra("position", 0);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensor = sensorList.get(position);
        tvValor = (TextView)findViewById(R.id.txtValor);

        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < event.values.length; i++) {
            stringBuffer.append(i).append(": ").append(event.values[i]).append("\n");
        }

        tvValor.setText(stringBuffer.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
