package soundboys.wm.p_oslo;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BnB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_b);

        getSupportActionBar().setTitle("Brøyting og bortkjøring av snø");

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void b1(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Class1.class);
        startActivity(intent);
    }

    public void b2(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Class2.class);
        startActivity(intent);
    }

    public void b3(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Class3.class);
        startActivity(intent);
    }

    public void b4(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Class4.class);
        startActivity(intent);
    }

}
