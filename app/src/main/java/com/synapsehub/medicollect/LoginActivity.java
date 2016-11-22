package com.synapsehub.medicollect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synapsehub.medicollect.config.config;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michelo on 18/11/2016.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private static final int REQUEST_SIGNUP=0;
    private static int REQUEST_REGISTER=99;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListerner;
    private String user_id;
    private String role_id;
    private String[] Roles={
            "Administrateur","Medecin","Collector","Visiteur"
    };

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    @Bind(R.id.sp_roles) Spinner _spinnerLink;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //Vérification des préferences
        user_id= MediCollectApplication.prefs.getString(config.PREFS_USERIDentity,null);
        if(user_id!=null){
            startActivity(new Intent(LoginActivity.this,InActivity.class));
            finish();
        }

        mAuth=FirebaseAuth.getInstance();


        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start the register activity
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(intent,REQUEST_SIGNUP); //Passe le paramètre
                finish();
                //Ajoute l'animation entre les activities
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });

/*

        //Use Spinner from String
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt, Roles);
        aa.setDropDownViewResource(R.layout.spinner_layout);
        _spinnerLink.setAdapter(aa);


*/
/*        //Use Spinner from Array
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.login_role,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply to Spinner
        _spinnerLink.setAdapter(adapter);

*/


        ArrayList<String> items=getLoginRoles("login_roles.json");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.spinner_layout,R.id.txt,items);
        _spinnerLink.setAdapter(adapter);


        _spinnerLink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                //Something here
                // parent.getItemAtPosition(pos)
               /*
                        Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
               */

                switch (pos) {
                    case 0:
                        Toast.makeText(parent.getContext(),
                                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(),
                                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(),
                                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_SIGNUP && resultCode==RESULT_OK){
            _emailText.setText(data.getStringExtra(Intent.EXTRA_TEXT));
        }
    }



    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user=authResult.getUser();

                //Ajouter dans les préférences
                MediCollectApplication.prefs.edit().putString(config.PREFS_USERIDentity,user.getEmail());
                Intent in = new Intent(LoginActivity.this, InActivity.class);
                in.putExtra("usermail", user.getEmail());
                startActivity(in);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Utilisateur inconnu ...", Toast.LENGTH_SHORT).show();
            }
        });


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    //Le code Firebase pour l'authetication va se mettre ici dans ce code en bas

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //CODE A CHECKER ------------------------------- **********
        if(mAuthListerner!=null){
            mAuth.addAuthStateListener(mAuthListerner);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListerner!=null){
            mAuth                                                                                                                          .removeAuthStateListener(mAuthListerner);
        }
    }

    //Try to retrieve data from jsonarray in assets ressources

    private ArrayList<String> getLoginRoles(String fileName){
        JSONArray jsonArray=null;
        ArrayList<String> cList=new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray=new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("role"));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException je){
            je.printStackTrace();
        }
        return cList;
    }



}
