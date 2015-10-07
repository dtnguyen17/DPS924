package dtnguyen17.lab5;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    String[] colourNames;
    String[] colourValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout  background = (RelativeLayout) findViewById(R.id.main_id);
        try {
            String sdcard = Environment.getExternalStorageDirectory().getPath();
            File myFile = new File(sdcard+"/mysdfile.txt");
            if (myFile.exists()) {
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String line;
                if ((line = myReader.readLine()) != null) {
                    background.setBackgroundColor(Color.parseColor("#" + line.substring(2)));
                }
                myReader.close();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        colourNames = getResources().getStringArray(R.array.listArray);
        colourValues =  getResources().getStringArray(R.array.listValues);
        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.activity_listview, colourNames);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                String code = colourValues[position];
                background.setBackgroundColor(Color.parseColor("#" + code.substring(2)));

                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Write colour to SDCard");
        menu.add(0, v.getId(), 1, "Read colour from SDCard");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        RelativeLayout  background = (RelativeLayout) findViewById(R.id.main_id);
        colourValues =  getResources().getStringArray(R.array.listValues);

        if (item.getTitle() == "Write colour to SDCard") {
            try {
                String sdcard = Environment.getExternalStorageDirectory().getPath();
                File myFile = new File(sdcard+"/mysdfile.txt");
                myFile.createNewFile();
                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int listPosition = info.position;
                String txtData = colourValues[listPosition];

                myOutWriter.append(txtData);
                myOutWriter.close();
                fOut.close();
                Toast.makeText(getBaseContext(), "Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        } else if (item.getTitle() == "Read colour from SDCard") {

            try {
                String sdcard = Environment.getExternalStorageDirectory().getPath();
                File myFile = new File(sdcard+"/mysdfile.txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String line;
                if ((line = myReader.readLine()) != null)
                {
                    background.setBackgroundColor(Color.parseColor("#" + line.substring(2)));
                }

                myReader.close();
                Toast.makeText(getBaseContext(), "Done reading SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            return false;
        }
        return true;
    }
}
