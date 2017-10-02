package ch.ethz.inf.vs.a1.ankoller.sensors.vs_ankoller_sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    int type;
    int position;
    private int nrOfValues;
    private String sensorUnit;

    private float[] sensor_values;
    private Sensor sensor;
    private SensorManager sensorMgr;

    TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        typeText = (TextView) findViewById(R.id.sensor_text);

        Bundle b = getIntent().getExtras();
        // if b == null, error...
        type = b.getInt(MainActivity.TYPE);
        position = b.getInt(MainActivity.POSITION);
        typeText.setTextSize(20);
        typeText.setText("Sensor Type: " + type + "\nPosition: " + position + "\n\n\n"
                + sensor_values);

        // Used to retrieve the sensor's number of values and unit.
        SensorTypesSpec sensor_type = new SensorTypesSpec();
        nrOfValues = sensor_type.getNumberValues(type);
        sensorUnit = sensor_type.getUnitString(type);




    }

    @Override
    protected void onPause() {
        //unregister sensor event listener
        sensorMgr.unregisterListener(this);

        super.onPause();
    }

    protected void  onResume() {
        // Registering a listener to the sensor in order to be able to catch sensor data.
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorMgr.getDefaultSensor(type);
        sensorMgr.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensor_values = sensorEvent.values;

        // dispaly data on TextView
        typeText.setText("");
        typeText.setText(sensor.getName() + "\n");

        for (int i = 0; i < nrOfValues; i++) {
            typeText.append(sensor_values[i] + " " + sensorUnit + "\n");
        }
    }

    // Parameters: GraphView object to handle, type of the sensor.
    // Result: GraphContainer object to set and retrieve data.

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do nothing here
    }
}
