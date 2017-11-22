package ittepic.edu.mx.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private Sensor stepCounter;
    SeekBar skb_sens;
    TextView txt_Counter;
    private SensorManager senMan;
    Context vent=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        txt_Counter=(TextView)findViewById(R.id.txt_Count);
        stepCounter= senMan.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        senMan.registerListener((SensorEventListener) this,stepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        skb_sens=(SeekBar)findViewById(R.id.seekBar);
        skb_sens.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress) {
                    case 0:
                        senMan.registerListener((SensorEventListener) vent,stepCounter,SensorManager.SENSOR_DELAY_NORMAL);
                        break;
                    case 1:
                        senMan.registerListener((SensorEventListener) vent,stepCounter,SensorManager.SENSOR_DELAY_GAME);
                        break;
                    case 2:
                        senMan.registerListener((SensorEventListener) vent,stepCounter,SensorManager.SENSOR_DELAY_FASTEST);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        txt_Counter.setText(""+event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
