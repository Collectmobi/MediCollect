package com.synapsehub.medicollect;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Michelo on 18/11/2016.
 */

public class MediCollectApplication extends Application{

    public static SharedPreferences prefs;
    public static SharedPreferences signinas;

    @Override
    public void onCreate(){
        super.onCreate();
        prefs=getSharedPreferences("com.synapsehub.medicollect",MODE_PRIVATE);
        signinas=getSharedPreferences("com.synapsehub.medicollect",MODE_PRIVATE);
    }

}
