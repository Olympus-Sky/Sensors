package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> name = new ArrayList<String>();

        for (Sensor sensor: sensorList) {
            name.add(sensor.getName() + " - " + sensor.getVendor() + " - " + sensor.getType());
        }

        ListView lvSensorsJ = (ListView)findViewById(R.id.lvSensors);
        lvSensorsJ.setOnItemClickListener(this);

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, layout, name);
        lvSensorsJ.setAdapter(adapter);
    }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Sensor sensor = sensorList.get(position);
            String message = sensor.getName() + " - " + sensor.getVendor();

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, TestSensorActivity.class);
            intent.putExtra("position", position);

            startActivity(intent);
        }
    }