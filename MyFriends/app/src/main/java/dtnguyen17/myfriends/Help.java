package dtnguyen17.myfriends;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        AlertDialog.Builder dialogBuilder;

        if(id == R.id.action_main)
            startActivity(new Intent(this, MainActivity.class));
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
