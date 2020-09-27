package soundboys.wm.p_oslo;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.drawable.smallets);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
    }


    public void stroing(View v) {
        Intent intent = new Intent(getApplicationContext(), Stroing.class);
        startActivity(intent);
    }

    public void rengjoring(View v) {
        Intent intent = new Intent(getApplicationContext(), varrengjoring.class);
        startActivity(intent);
    }

    public void bnb(View v) {
        Intent intent = new Intent(getApplicationContext(), BnB.class);
        startActivity(intent);
    }

    public void plas(View v) {
        Intent intent = new Intent(getApplicationContext(), PlanlagtAsfaltering.class);
        startActivity(intent);
    }
}