package com.synapsehub.medicollect.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.synapsehub.medicollect.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Michelo on 19/11/2016.
 */

public class PatientFragment extends Fragment {

    EditText _inputname;
    EditText _inputage;
    EditText _inputresidence;
    EditText _inputtelephone;
    Spinner _inputmarie;
    Spinner _inputvitseul;
    Spinner _inputrepudie;
    Spinner _inputrecruitment;


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_patient,container,false);

        _inputrecruitment=(Spinner)rootView.findViewById(R.id.input_recrutement);
        _inputmarie=(Spinner)rootView.findViewById(R.id.input_marie);

        //Marital status Spinner
        ArrayList<String> etatcivil=getLoginRoles("etatcivil.json","etat");
        ArrayAdapter<String> ecadapt=new ArrayAdapter<String>(this.getActivity(),
                R.layout.spinner_layout,R.id.txt,etatcivil);
        _inputrecruitment.setAdapter(ecadapt);

        //Recruitment Spinner
        ArrayList<String> recruitment=getLoginRoles("lieu.json","lieu");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),
                R.layout.spinner_layout,R.id.txt,recruitment);
        _inputrecruitment.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        super.onPause();
    }


    //Try to retrieve data from jsonarray in assets ressources
    private ArrayList<String> getLoginRoles(String fileName, String value){
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
                    cList.add(jsonArray.getJSONObject(i).getString(value));
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
