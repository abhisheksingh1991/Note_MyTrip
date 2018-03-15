package com.example.sg0301594.myapplication;


        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.HashMap;

public class TravelDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.traveldetailToolbar);
        setSupportActionBar(toolbar);

        String maxTemp = getIntent().getStringExtra("maxTemp");
        TextView maxTempTextView = (TextView) findViewById(R.id.maxTemp);
        maxTempTextView.setText(maxTemp);
        String humidity = getIntent().getStringExtra("humidity");
        TextView humidityTextView = (TextView) findViewById(R.id.humidit);
        humidityTextView.setText(humidity);
        String departureDate = getIntent().getStringExtra("departureDate");
        TextView departureDateTextView = (TextView) findViewById(R.id.dpdate);
        departureDateTextView.setText(departureDate);
        String destination = getIntent().getStringExtra("destination");
        TextView destinationTextView = (TextView) findViewById(R.id.destination);
        destinationTextView.setText(destination);
        String weatherCondition = getIntent().getStringExtra("weatherCondition");
        TextView weatherConditonTextView = (TextView) findViewById(R.id.weatherinfo);
        weatherConditonTextView.setText(weatherCondition);

        sharedpreferences = getSharedPreferences("ADD_TRIP_NAME", Context.MODE_PRIVATE);
        String tripName = sharedpreferences.getString("TRIP_NAME","");

        SharedPreferences.Editor editor = sharedpreferences.edit();
        StringBuffer tripDetails = new StringBuffer();
        tripDetails.append(tripName.toString());
        tripDetails.append("    ");
        tripDetails.append(destination);
        tripDetails.append("    ");
        tripDetails.append(departureDate);

        editor.putString("TRIP_NAME",tripDetails.toString());
        editor.commit();

        final Button button = (Button) findViewById(R.id.contbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pnrIntent = getIntent();
                Intent intent = new Intent(TravelDetails.this, PassengerInfo.class);
                intent.putExtras(pnrIntent);
                startActivity(intent);
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
                TravelDetails.this,
                MyTriplistActivity.class);
        startActivity(i);

        return true;
    }
}
