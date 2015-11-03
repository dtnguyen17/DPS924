package dtnguyen17.myfriends;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private AlertDialog.Builder dialogBuilder;
    private String DBName;
    private EditText databaseName;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseName = (EditText) findViewById(R.id.editText);

        search = (Button)findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBName = databaseName.getText().toString();

                if (DBName.equals("MyFriends")) {
                    Intent intent = new Intent(MainActivity.this, Friend_list.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No database found.", Toast.LENGTH_SHORT).show();
                }
            }
        });



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

        if(id == R.id.action_help)
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
