package dtnguyen17.myfriends;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Friend_details extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);

        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);

        //http://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
        Bundle extras = getIntent().getExtras();
        name.setText(extras.getString("_name"));
        phone.setText(extras.getString("_phone"));
        email.setText(extras.getString("_email"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if(id == R.id.action_main)
            startActivity(new Intent(this, MainActivity.class));
        else if (id == R.id.action_list)
            startActivity(new Intent(this, Friend_list.class));
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

