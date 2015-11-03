package dtnguyen17.myfriends;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Friend_list extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    public static ArrayList<String> arrayOfNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        final DBHandler dbHandler = new DBHandler(this, null);

        dbHandler.initializeDataBase();

        final List<Friends> friends = dbHandler.getAllFriendsNames();

        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.activity_listview, arrayOfNames);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                Friends clicked = friends.get(position);

                Intent intent = new Intent(Friend_list.this, Friend_details.class);
                Bundle extras = new Bundle();

                // http://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                extras.putString("_name", clicked.getName());
                extras.putString("_phone", clicked.getPhone());
                extras.putString("_email", clicked.getEmail());
                intent.putExtras(extras);

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_main)
            startActivity(new Intent(this, MainActivity.class));
        else if(id == R.id.action_help)
            startActivity(new Intent(this, Help.class));
        else if (id == R.id.action_about)
        {
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(getString(R.string.title_about));
            dialogBuilder.setMessage(getString(R.string.about));
            AlertDialog dialogTerm = dialogBuilder.create();
            dialogTerm.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
