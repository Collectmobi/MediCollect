package com.synapsehub.medicollect.config;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Michelo on 18/11/2016.
 */

public class config {

    public static final String PREFS_USERIDentity = "user_identity";
    public static final String prefs_signup="login";
    public static final String LOGIN_AS="loginas";
    public static final String FORMS_ENTITY="forms";
    public static final String Child_form = "form";


    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (Context c, String msg)
    {
        Toast.makeText (c, msg, Toast.LENGTH_SHORT).show ();
    } // end toast



}
