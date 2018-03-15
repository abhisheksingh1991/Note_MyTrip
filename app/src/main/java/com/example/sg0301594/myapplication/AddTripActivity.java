package com.example.sg0301594.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddTripActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    String addTrip = "AddTrip : ";
    ImageButton backBtn,continuebtn;
    EditText tripName;
    SharedPreferences sharedpreferences;
    Button btnAddTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addTripToolbar);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences("ADD_TRIP_NAME", Context.MODE_PRIVATE);

      //  continuebtn = (ImageButton)findViewById(R.id.backButton);
       // backBtn = (ImageButton)findViewById(R.id.backButton);
        btnAddTrip = (Button) findViewById(R.id.addTripBtn);
        btnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.d(addTrip,"Calling in Continue Button");
                tripName = (EditText)findViewById(R.id.tripName);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("TRIP_NAME",tripName.getText().toString());
                editor.commit();
                Intent i=new Intent(
                        AddTripActivity.this,
                        ChoosePNR.class);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trips) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        Intent i=new Intent(
                AddTripActivity.this,
                MyTriplistActivity.class);
        startActivity(i);

        return true;
    }

}
