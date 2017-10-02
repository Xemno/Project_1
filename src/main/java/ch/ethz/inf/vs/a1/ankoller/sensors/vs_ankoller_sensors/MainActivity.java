package ch.ethz.inf.vs.a1.ankoller.sensors.vs_ankoller_sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public final static String POSITION = "ch.ethz.inf.vs.a1.POSITION";
    public final static String TYPE = "ch.ethz.inf.vs.a1.TYPE";

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

        for (int i = 0; i < sensorsList.size(); i++) {
            liststring.add(sensorsList.get(i).getName());
        }

        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, liststring
        );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long value) {
        // new intent
        Intent intent = new Intent(getApplicationContext(), SensorActivity.class);

        // pass infromation (Extras) in the intent for the new activity to be started
        intent.putExtra(POSITION, position);
        Sensor selected_sensor = (Sensor) sensorsList.get(position);
        intent.putExtra(TYPE, selected_sensor.getType());

        // start new activity
        startActivity(intent);
    }
}
