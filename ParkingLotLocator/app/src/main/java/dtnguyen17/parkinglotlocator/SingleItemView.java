package dtnguyen17.parkinglotlocator;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class SingleItemView extends FragmentActivity{

    TextView txtAddress;
    TextView txtRate;
    TextView txtType;
    TextView txtCapacity;
    TextView txtOptions;

    String address;
    String rate;
    String type;
    String capacity;
    String lng;
    String lat;
    List<String> options;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_list_item);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        lng = i.getStringExtra("lng");
        lat = i.getStringExtra("lat");

        double dlng = Double.parseDouble(lng);
        double dlat = Double.parseDouble(lat);

        address = i.getStringExtra("address");
        rate = i.getStringExtra("rate");
        type = i.getStringExtra("carparktype");
        capacity = i.getStringExtra("capacity");
        options = i.getStringArrayListExtra("options");

        txtAddress = (TextView) findViewById(R.id.address);
        txtRate = (TextView) findViewById(R.id.rate);
        txtType = (TextView) findViewById(R.id.type);
        txtCapacity = (TextView) findViewById(R.id.capacity);
        txtOptions = (TextView) findViewById(R.id.options);


        txtAddress.setText("\t" + address);
        txtRate.setText("\t" + rate);
        txtType.setText("\t" + type);
        txtCapacity.setText("\t" + capacity);
        StringBuilder sb = new StringBuilder();
        for (int j=0; j < options.size(); j++)
            sb.append("\t" + options.get(j) + "\n");
        txtOptions.setText(sb);

        Bundle bundle = new Bundle();
        bundle.putString("addr", address);
        bundle.putDouble("lng", dlng);
        bundle.putDouble("lat", dlat);

        MapFrag mfrag = new MapFrag();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        mfrag.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragView,mfrag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
