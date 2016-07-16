package com.example.geetakrishna.mypedometer;


import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;


public class PedoLogService extends Service implements SensorEventListener {

    private static final String DEBUG_TAG = "PedoLogService";

    private SensorManager sensorManager = null;
    private Sensor StepCounterSensor = null;
    private Sensor StepDetectorSensor = null;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        StepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        StepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(this, StepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, StepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){

        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }


        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {

        }   else if (sensor.getType()== Sensor.TYPE_STEP_DETECTOR) {



        }

        double multi = (Integer.parseInt(String.valueOf(value)) * Float.parseFloat(String.valueOf(0.000804)));

        double rounded = Math.round(multi *100)/100.0;


        double cal = (Integer.parseInt(String.valueOf(value)) * Float.parseFloat(String.valueOf(0.044)));

        double round = Math.round(cal *100)/100.0;


        new SensorEventLoggerTask().execute(event);
    }


    private class SensorEventLoggerTask extends AsyncTask<SensorEvent, Void, Void> {

        @Override
        protected Void doInBackground(SensorEvent... events){
            SensorEvent event = events[0];
            return null;
        }


    }
}


