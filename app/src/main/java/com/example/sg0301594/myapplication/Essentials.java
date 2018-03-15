package com.example.sg0301594.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Essentials extends Fragment {
    public void OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        Set<String> tripdetailsSet = new HashSet<String>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TRIP_DEST_WEATHER_DETAILS", Context.MODE_PRIVATE);
        String tripName = sharedPreferences.getString("TRIP_NAME","");
        String tripEsenKey=tripName+"esen";

        tripdetailsSet = sharedPreferences.getStringSet(tripEsenKey,tripdetailsSet);

        StringBuilder esenBuild = new StringBuilder();
        List<Boolean> esenlist = new ArrayList<Boolean>();
        CheckBox ech1=(CheckBox)getActivity().findViewById(R.id.p1);
        CheckBox ech2=(CheckBox)getActivity().findViewById(R.id.p2);
        CheckBox ech3=(CheckBox)getActivity().findViewById(R.id.p3);
        CheckBox ech4=(CheckBox)getActivity().findViewById(R.id.p4);
        CheckBox ech5=(CheckBox)getActivity().findViewById(R.id.p5);
        CheckBox ech6=(CheckBox)getActivity().findViewById(R.id.p6);
        esenlist.add(ech1 != null ? ech1.isChecked(): false);
        esenlist.add(ech2 != null ? ech2.isChecked(): false);
        esenlist.add(ech3 != null ? ech3.isChecked(): false);
        esenlist.add(ech4 != null ? ech4.isChecked(): false);
        esenlist.add(ech5 != null ? ech5.isChecked(): false);
        esenlist.add(ech6 != null ? ech6.isChecked(): false);

        for(int i=0;i<esenlist.size();i++)
        {
            if(esenlist.get(i) == true)
            {
                esenBuild.append("T");
                esenBuild.append(";");
            }else
            {
                esenBuild.append("F");
            }
        }
        String strEsen = "";
        if(tripdetailsSet != null && tripdetailsSet.size()>0) {

            Iterator iter1 = tripdetailsSet.iterator();
            while (iter1.hasNext()) {
                strEsen = (String) iter1.next();
            }
        }
        if(ech1 != null && strEsen != null)
        {
                String[] stringesen = strEsen.split(";");
                ech1.setChecked(stringesen[0].toString() == "T" ? true : false);

        }
        // Esentials
        //CheckBox ech1=(CheckBox)getActivity().findViewById(R.id.p1);
        if(ech1 != null) {
            System.out.println("Values of Eseent" + ech1.isChecked());
        }
        //CheckBox ech2=(CheckBox)getActivity().findViewById(R.id.p1);
        if(ech2 != null) {
            System.out.println("Values of Eseent" + ech2.isChecked());
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        tripdetailsSet.add(esenBuild.toString());

        editor.putStringSet(tripEsenKey,tripdetailsSet);
        editor.commit();


        System.out.println("***********************************"+tripdetailsSet);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_essentials, container, false);
    }
}