package org.firstinspires.ftc.teamcode.subsystems;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;

/* This class is for setting up the phone's gyro to be used during autonomous.
   It's straight from Android Studio Documentation. There is nothing that
   can be found for offical FTC documentation. This WILL have to be
   adjusted.
*/

public class GyroPhone extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mGyroscope;

    public GyroPhone(){
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mGyroscope     = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    }

}
