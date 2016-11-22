package com.synapsehub.medicollect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.synapsehub.medicollect.ui.CollectActivity;
import com.synapsehub.medicollect.ui.SettingsActivity;

public class InActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);


    }

    public void onClickFeature (View v)
    {
        int id = v.getId ();
        switch (id) {
            case R.id.home_btn_feature1 :
                startActivity (new Intent(getApplicationContext(), CollectActivity.class));
                break;
            case R.id.home_btn_feature2 :
                // startActivity (new Intent(getApplicationContext(), SendOneSmsActivity.class));
                break;
            case R.id.home_btn_feature6 :
                //startActivity (new Intent(getApplicationContext(), AboutActivity.class));
                break;

            /**
             case R.id.home_btn_feature3 :
             startActivity (new Intent(getApplicationContext(), F3Activity.class));
             break;
             case R.id.home_btn_feature4 :
             startActivity (new Intent(getApplicationContext(), F4Activity.class));
             break;
             case R.id.home_btn_feature5 :
             startActivity (new Intent(getApplicationContext(), F5Activity.class));
             break;
             */

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
