package com.example.geetakrishna.mypedometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {


    private TextView textView, distTV,calTv;
    private Button resetbtn;
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        distTV = (TextView) findViewById(R.id.distTv);
        calTv = (TextView) findViewById(R.id.calTv);



        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = (mSensorManager)
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = (mSensorManager)
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {
            textView.setText("Steps: " + value);

        }   else if (sensor.getType()== Sensor.TYPE_STEP_DETECTOR) {

            textView.setText("Step Detector Detected:" + value);

        }

        double multi = (Integer.parseInt(String.valueOf(value)) * Float.parseFloat(String.valueOf(0.000804)));

        double rounded = Math.round(multi *100)/100.0;

        distTV.setText("Distance: " + String.valueOf(rounded) + " km");

        double cal = (Integer.parseInt(String.valueOf(value)) * Float.parseFloat(String.valueOf(0.044)));

        double round = Math.round(cal *100)/100.0;

        calTv.setText("Calories Burnt: " + String.valueOf(round) + " Cal");


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume(){

        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);


    }






}
