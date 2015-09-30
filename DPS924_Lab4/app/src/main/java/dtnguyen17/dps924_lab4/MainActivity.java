package dtnguyen17.dps924_lab4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentOne fragOne = new FragmentOne();
        FragmentTwo fragTwo = new FragmentTwo();
        transaction.add(R.id.fragView, fragOne, "Fragment1");
        transaction.add(R.id.fragView, fragTwo, "Fragment2");
        transaction.commit();
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

    public void swapImage(View view)
    {
        // Got idea from lab 3 but instead of autoCompleteTextView, I used ImageView.
        ImageView img1 = (ImageView) findViewById(R.id.imageView);
        ImageView img2 = (ImageView) findViewById(R.id.imageView2);

        // stackoverflow.com/questions/18582090/changing-imageview-image-depending-on-previous-fragment-viewpager
        if ( counter == 1 ) {
            img1.setImageResource(R.drawable.troll);
            img2.setImageResource(R.drawable.kappa);
            counter = 0;
        }
        else {
            img1.setImageResource(R.drawable.kappa);
            img2.setImageResource(R.drawable.troll);
            counter = 1;
        }
    }

}
