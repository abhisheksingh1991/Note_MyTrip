package com.example.sg0301594.myapplication;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.os.Handler;
        import android.preference.PreferenceManager;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

//        import com.afollestad.materialdialogs.MaterialDialog;
//        import com.dd.processbutton.FlatButton;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import com.fasterxml.jackson.datatype.joda.JodaModule;
//        import com.sabre.hackday.pnr.Passenger;
//        import com.sabre.hackday.pnr.Pnr;
//        import com.sabre.hackday.weather.History;
//        import com.sabre.hackday.weather.WeatherResponse;
//
//        import org.joda.time.LocalDate;
//        import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//        import org.springframework.web.client.RestTemplate;

        import java.io.IOException;
        import java.util.HashMap;

        import butterknife.BindView;
//        import utils.Constants;

public class PnrRetrievalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String pnrLocator;
    private String tripName;

//    private MaterialDialog dialog;
    private Handler mHandler;
    private SharedPreferences sharedPreferences;
    private boolean malePresent;
    private boolean femalePresent;
    private boolean kidsPresent;
    private String destination;
//    private LocalDate arrivalDate;
    private String weatherCondition;
    private String season;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_retrieval);
        Toolbar toolbar = (Toolbar) findViewById(R.id.PnrRetrievalToolbar);
        setSupportActionBar(toolbar);

        final Button button = (Button) findViewById(R.id.fetchPnr);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // Fetch PNR
                    case R.id.fetchPnr:
                        TextView pnrTextView = (TextView) findViewById(R.id.pnr);
                        pnrLocator = pnrTextView.getText().toString();
                        TextView lastNameTextView = (TextView) findViewById(R.id.lastName);
                        lastName = lastNameTextView.getText().toString();
//                        fetchPnrDetails(pnrLocator, lastName);
//                        try {
//                            Thread.sleep(20000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                        HashMap<String,String> destinationList = new HashMap<String,String>();

                        destinationList.put("11223340","Mumbai;2018-03-20;Cloudy;30;25");
                        destinationList.put("11223341","Delhi;2018-03-21;;Cloudy;28;26");
                        destinationList.put("11223342","Ahemdabad;2018-03-22;Cloudy;26;24");
                        destinationList.put("11223343","Nagpur;2018-03-23;Cloudy;25;20");
                        destinationList.put("11223344","Bangalore;2018-03-24;Summer;40;35");
                        destinationList.put("11223345","Chennai;2018-03-25;Summer;45;40");
                        destinationList.put("11223346","Bhopal;2018-03-26;Summer;46;38");
                        destinationList.put("11223347","Lucknow;2018-03-27;Winter;20;15");
                        destinationList.put("11223348","Allahabad;2018-03-28;Winter;15;10");
                        destinationList.put("11223349","Srinagar;2018-03-29;Winter;10;8");
                        destinationList.put("11223350","kolkata;2018-03-30;Winter;5;2");
                        String city =destinationList.get(pnrLocator).split(";")[0];
                        String date =destinationList.get(pnrLocator).split(";")[1];
                        String weather =destinationList.get(pnrLocator).split(";")[2];
                        String maxTemp =destinationList.get(pnrLocator).split(";")[3];
                        String minTemp =destinationList.get(pnrLocator).split(";")[4];

                        SharedPreferences sharedPreferences = getSharedPreferences("TRIP_DEST_WEATHER_DETAILS", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("TRIP_DEST_WEATHER",destinationList.get(pnrLocator));
                        editor.commit();

                        Intent intent = new Intent(PnrRetrievalActivity.this, TravelDetails.class);
                        intent.putExtra("male", true);
                        intent.putExtra("female", femalePresent);
                        intent.putExtra("kids", true);
                        intent.putExtra("destination", city );
                        intent.putExtra("departureDate", date);
                        intent.putExtra("weatherCondition", weather);
                        intent.putExtra("maxTemp", maxTemp);
                        intent.putExtra("humidity", "80");
                        intent.putExtra("minTemp", minTemp);
                        startActivity(intent);
                        break;
                }
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
                PnrRetrievalActivity.this,
                MyTriplistActivity.class);
        startActivity(i);

        return true;
    }


//    private void fetchPnrDetails(String pnrLocator, String lastName) {
//        // Show a dialog box
//        dialog = new MaterialDialog.Builder(PnrRetrievalActivity.this)
//                .title(R.string.app_name)
//                .content("Please wait...")
//                .progress(true, 0)
//                .show();
//        new HttpRequestTask().execute();
      /*  Pnr pnr = null;
        final String url = Constants.weatherApiLink + "/3922037bb5da6dd4/history" + "_" + "20171231" + "/q/" + "IN" + "/" + "Mumbai" + ".json";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//                Pnr greeting = restTemplate.getForObject(url, Pnr.class);
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        String json = getpnrJson();
        try {
            pnr = mapper.readValue(json, Pnr.class);
            pnr.setWeatherCondition("cloudy");
            pnr.setSeason("winter");
            malePresent = isGenderMale(pnr);
            femalePresent = isGenderMale(pnr);
            kidsPresent = isChildTravelling(pnr);
            destination = pnr.getItineraries().get(0).getDestination();
            arrivalDate = pnr.getItineraries().get(0).getArrivalDate();
            weatherCondition = pnr.getWeatherCondition();
            season = pnr.getSeason();
            dialog.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pnr; */
//    }
//
//    private boolean isChildTravelling(Pnr pnr) {
//        for (Passenger passenger : pnr.getPassengers()) {
//            if (!passenger.getGender().equals("ADT")) ;
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isGenderMale(Pnr pnr) {
//        for (Passenger passenger : pnr.getPassengers()) {
//            if (passenger.getGender().equals("Male")) ;
//            return true;
//        }
//        return false;
//    }
//
//    private String getpnrJson() {
//        return "{\n" +
//                "  \"pnrLocator\": \"YJADJAL\",\n" +
//                "  \"creationDate\": \"2016-01-01\",\n" +
//                "  \"passengers\": [\n" +
//                "    {\n" +
//                "      \"paxNumber\": 1,\n" +
//                "      \"lastName\": \"Pax1LastName\",\n" +
//                "      \"firstName\": \"Pax1FistName\",\n" +
//                "      \"gender\": \"Male\",\n" +
//                "      \"passengerType\": \"ADT\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"paxNumber\": 2,\n" +
//                "      \"lastName\": \"Pax2LastName\",\n" +
//                "      \"firstName\": \"Pax2FirstName\",\n" +
//                "      \"gender\": \"Male\",\n" +
//                "      \"passengerType\": \"ADT\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"itineraries\": [\n" +
//                "    {\n" +
//                "      \"airline\": \"AI\",\n" +
//                "      \"flightNumber\": 1234,\n" +
//                "      \"origin\": \"DFW\",\n" +
//                "      \"destination\": \"BLR\",\n" +
//                "      \"departureDate\": \"2016-01-01\",\n" +
//                "      \"arrivalDate\": \"2016-01-01\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"airline\": \"9W\",\n" +
//                "      \"flightNumber\": 4321,\n" +
//                "      \"origin\": \"BLR\",\n" +
//                "      \"destination\": \"BOM\",\n" +
//                "      \"departureDate\": \"2016-01-01\",\n" +
//                "      \"arrivalDate\": \"2016-01-01\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//    }
//
//    private class HttpRequestTask extends AsyncTask<Void, Void, Pnr> {
//        @Override
//        protected Pnr doInBackground(Void... params) {
//            Pnr pnr = null;
//            try {
////                final String url = Constants.apilink + "/pnr?pnrLocator=" + pnrLocator;
//                final String url = Constants.weatherApiLink + "/3922037bb5da6dd4/history" + "_" + "20171231" + "/q/" + "IN" + "/" + "Mumbai" + ".json";
//                RestTemplate restTemplate = new RestTemplate();
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.registerModule(new JodaModule());
//                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
////                Pnr greeting = restTemplate.getForObject(url, Pnr.class);
//                WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
//                String json = getpnrJson();
//                pnr = mapper.readValue(json, Pnr.class);
//                pnr.setWeatherCondition("cloudy");
//                pnr.setSeason("winter");
//            } catch (Exception e) {
//                Log.e("MainActivity", e.getMessage(), e);
//            }
//            return pnr;
//        }
//
//        private String getpnrJson() {
//            return "{\n" +
//                    "  \"pnrLocator\": \"YJADJAL\",\n" +
//                    "  \"creationDate\": \"2016-01-01\",\n" +
//                    "  \"passengers\": [\n" +
//                    "    {\n" +
//                    "      \"paxNumber\": 1,\n" +
//                    "      \"lastName\": \"Pax1LastName\",\n" +
//                    "      \"firstName\": \"Pax1FistName\",\n" +
//                    "      \"gender\": \"Male\",\n" +
//                    "      \"passengerType\": \"ADT\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"paxNumber\": 2,\n" +
//                    "      \"lastName\": \"Pax2LastName\",\n" +
//                    "      \"firstName\": \"Pax2FirstName\",\n" +
//                    "      \"gender\": \"Male\",\n" +
//                    "      \"passengerType\": \"ADT\"\n" +
//                    "    }\n" +
//                    "  ],\n" +
//                    "  \"itineraries\": [\n" +
//                    "    {\n" +
//                    "      \"airline\": \"AI\",\n" +
//                    "      \"flightNumber\": 1234,\n" +
//                    "      \"origin\": \"DFW\",\n" +
//                    "      \"destination\": \"BLR\",\n" +
//                    "      \"departureDate\": \"2016-01-01\",\n" +
//                    "      \"arrivalDate\": \"2016-01-01\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"airline\": \"9W\",\n" +
//                    "      \"flightNumber\": 4321,\n" +
//                    "      \"origin\": \"BLR\",\n" +
//                    "      \"destination\": \"BOM\",\n" +
//                    "      \"departureDate\": \"2016-01-01\",\n" +
//                    "      \"arrivalDate\": \"2016-01-01\"\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";
//        }
//
//        @Override
//        protected void onPostExecute(Pnr pnr) {
//            malePresent = isGenderMale(pnr);
//            femalePresent = isGenderMale(pnr);
//            kidsPresent = isChildTravelling(pnr);
//            destination = pnr.getItineraries().get(0).getDestination();
//            arrivalDate = pnr.getItineraries().get(0).getArrivalDate();
//            weatherCondition = pnr.getWeatherCondition();
//            season = pnr.getSeason();
//            dialog.hide();
//        }
//
//        private boolean isChildTravelling(Pnr pnr) {
//            for (Passenger passenger : pnr.getPassengers()) {
//                if (!passenger.getGender().equals("ADT")) ;
//                return true;
//            }
//            return false;
//        }
//
//        private boolean isGenderMale(Pnr pnr) {
//            for (Passenger passenger : pnr.getPassengers()) {
//                if (passenger.getGender().equals("Male")) ;
//                return true;
//            }
//            return false;
//        }
//    }
}


