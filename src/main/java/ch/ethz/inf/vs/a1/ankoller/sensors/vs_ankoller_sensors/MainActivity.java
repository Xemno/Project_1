package ch.ethz.inf.vs.a1.ankoller.sensors.vs_ankoller_sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorMgr;
    private List <Sensor> sensorsList;
    private ListView listview;

    List<String> liststring;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        listview = (ListView) findViewById(R.id.listview);

        sensorsList = sensorMgr.getSensorList(Sensor.TYPE_ALL);
        liststring = new ArrayList<String>();

        for (int i = 1; i < sensorsList.size(); i++) {
            liststring.add(sensorsList.get(i).getName());
        }

        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1, liststring
        );

        listview.setAdapter(adapter);

    }
}
