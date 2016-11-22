package com.synapsehub.medicollect.pattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.synapsehub.medicollect.R;

import com.synapsehub.medicollect.InActivity;
import com.synapsehub.medicollect.ui.CollectActivity;

/**
 * Created by Michelo on 19/11/2016.
 */

public class DashboardUI extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    /**
     * Different methods
     */

    protected void onDestroy ()
    {
        super.onDestroy ();
    }

    protected void onPause ()
    {
        super.onPause ();
    }

    protected void onRestart ()
    {
        super.onRestart ();
    }

    protected void onResume ()
    {
        super.onResume ();
    }

    protected void onStart ()
    {
        super.onStart ();
    }

    protected void onStop ()
    {
        super.onStop ();
    }

    /**
     */
    // Click Methods

    /**
     * Handle the click on the home button.
     *
     * @param v View
     * @return void
     */

    public void onClickHome (View v)
    {
        goHome (this);
    }

    /**
     * Handle the click on the search button.
     *
     * @param v View
     * @return void
     */

    public void onClickSearch (View v)
    {
       // startActivity (new Intent(getApplicationContext(), SearchActivity.class));
    }

    /**
     * Handle the click on the About button.
     *
     * @param v View
     * @return void
     */

    public void onClickAbout (View v)
    {
       // startActivity (new Intent(getApplicationContext(), AboutActivity.class));
    }

    /**
     * Handle the click of a Feature button.
     *
     * @param v View
     * @return void
     */

    public void onClickFeature2 (View v)
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

    /**
     */
    // More Methods

    /**
     * Go back to the home activity.
     *
     * @param context Context
     * @return void
     */

    public void goHome(Context context)
    {
        final Intent intent = new Intent(context, InActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity (intent);
    }

    /**
     * Use the activity label to set the text in the activity's title text view.
     * The argument gives the name of the view.
     *
     * <p> This method is needed because we have a custom title bar rather than the default Android title bar.
     * See the theme definitons in styles.xml.
     *
     * @param textViewId int
     * @return void
     */

    public void setTitleFromActivityLabel (int textViewId)
    {
        TextView tv = (TextView) findViewById (textViewId);
        if (tv != null) tv.setText (getTitle ());
    } // end setTitleText

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    } // end toast

    /**
     * Send a message to the debug log and display it using Toast.
     */
    public void trace (String msg)
    {
        Log.d("Michelo", msg);
        toast (msg);
    }

    // }


}// end class

