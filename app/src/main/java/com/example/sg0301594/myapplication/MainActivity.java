package com.example.sg0301594.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        b1 = (Button) findViewById(R.id.saveMainAct);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esential
               /* tripName = (EditText)findViewById(R.id.tripName);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("TRIP_NAME",tripName.getText().toString());
                editor.commit();*/

               MainActivityList mainTripList = new MainActivityList();
               StringBuilder esenBuild = new StringBuilder();
               StringBuilder clothBuild = new StringBuilder();
               StringBuilder docBuild = new StringBuilder();
               StringBuilder healBuild = new StringBuilder();
               StringBuilder hydBuild = new StringBuilder();


                SharedPreferences sharedPreferences = getSharedPreferences("TRIP_DEST_WEATHER_DETAILS", Context.MODE_PRIVATE);
                String tripName = sharedPreferences.getString("TRIP_NAME","");

                Set<String> tripdetailsSet = new HashSet<String>();
                String tripEsenKey=tripName+"esen";
                String tripClothKey=tripName+"cloth";
                String tripdocKey=tripName+"doc";
                String triphealkey=tripName+"heal";
                String triphygKey=tripName+"hyg";

                List<Boolean> esenlist = new ArrayList<Boolean>();
                CheckBox ech1=(CheckBox)findViewById(R.id.p1);
                CheckBox ech2=(CheckBox)findViewById(R.id.p2);
                CheckBox ech3=(CheckBox)findViewById(R.id.p3);
                CheckBox ech4=(CheckBox)findViewById(R.id.p4);
                CheckBox ech5=(CheckBox)findViewById(R.id.p5);
                CheckBox ech6=(CheckBox)findViewById(R.id.p6);
                esenlist.add(ech1 != null ? ech1.isChecked():false);
                esenlist.add(ech2 != null ? ech2.isChecked():false);
                esenlist.add(ech3 != null ? ech3.isChecked():false);
                esenlist.add(ech4 != null ? ech4.isChecked():false);
                esenlist.add(ech5 != null ? ech4.isChecked():false);
                esenlist.add(ech6 != null ? ech5.isChecked():false);
                mainTripList.setEsenList(esenlist);

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


                /*List<Boolean> clothlist = new ArrayList<Boolean>();
                CheckBox ecloth1=(CheckBox)findViewById(R.id.belt);
                CheckBox ecloth2=(CheckBox)findViewById(R.id.dress);
                CheckBox ecloth3=(CheckBox)findViewById(R.id.jacket);
                CheckBox ecloth4=(CheckBox)findViewById(R.id.braclets);
                clothlist.add(ecloth1.isChecked());
                clothlist.add(ecloth2.isChecked());
                clothlist.add(ecloth3.isChecked());
                clothlist.add(ecloth4.isChecked());
                mainTripList.setClothList(clothlist);

                for(int i=0;i<clothlist.size();i++)
                {
                    if(clothlist.get(i) == true)
                    {
                        clothBuild.append("T");
                        clothBuild.append(";");
                    }else
                    {
                        clothBuild.append("F");
                    }
                }


                List<Boolean> docList = new ArrayList<Boolean>();
                CheckBox edoc1=(CheckBox)findViewById(R.id.birthCert);
                CheckBox edoc2=(CheckBox)findViewById(R.id.Cash);
                CheckBox edoc3=(CheckBox)findViewById(R.id.Cashcard);
                CheckBox edoc4=(CheckBox)findViewById(R.id.IDcard);
                docList.add(edoc1.isChecked());
                docList.add(edoc1.isChecked());
                docList.add(edoc1.isChecked());
                docList.add(edoc1.isChecked());
                docList.add(edoc1.isChecked());
                mainTripList.setDocList(docList);
                for(int i=0;i<docList.size();i++)
                {
                    if(docList.get(i) == true)
                    {
                        docBuild.append("T");
                        docBuild.append(";");
                    }else
                    {
                        docBuild.append("F");
                    }
                }


                List<Boolean> healthList = new ArrayList<Boolean>();
                CheckBox ehlt1=(CheckBox)findViewById(R.id.h1);
                CheckBox ehlt2=(CheckBox)findViewById(R.id.h2);
                CheckBox ehlt3=(CheckBox)findViewById(R.id.h3);
                CheckBox ehlt4=(CheckBox)findViewById(R.id.h4);
                CheckBox ehlt5=(CheckBox)findViewById(R.id.h5);
                CheckBox ehlt6=(CheckBox)findViewById(R.id.h6);
                CheckBox ehlt7=(CheckBox)findViewById(R.id.h7);
                CheckBox ehlt8=(CheckBox)findViewById(R.id.h8);
                healthList.add(ehlt1.isChecked());
                healthList.add(ehlt2.isChecked());
                healthList.add(ehlt3.isChecked());
                healthList.add(ehlt4.isChecked());
                healthList.add(ehlt5.isChecked());
                healthList.add(ehlt6.isChecked());
                healthList.add(ehlt7.isChecked());
                healthList.add(ehlt8.isChecked());
                mainTripList.setHealthList(healthList);


                for(int i=0;i<healthList.size();i++)
                {
                    if(healthList.get(i) == true)
                    {
                        healBuild.append("T");
                        healBuild.append(";");
                    }else
                    {
                        healBuild.append("F");
                    }
                }



                List<Boolean> hygList = new ArrayList<Boolean>();
                CheckBox hy1=(CheckBox)findViewById(R.id.y1);
                CheckBox hy2=(CheckBox)findViewById(R.id.y2);
                CheckBox hy3=(CheckBox)findViewById(R.id.y3);
                CheckBox hy4=(CheckBox)findViewById(R.id.y4);
                CheckBox hy5=(CheckBox)findViewById(R.id.y5);
                CheckBox hy6=(CheckBox)findViewById(R.id.y6);
                CheckBox hy7=(CheckBox)findViewById(R.id.y7);
                CheckBox hy8=(CheckBox)findViewById(R.id.y8);
                hygList.add(hy1.isChecked());
                hygList.add(hy2.isChecked());
                hygList.add(hy3.isChecked());
                hygList.add(hy4.isChecked());
                hygList.add(hy5.isChecked());
                hygList.add(hy6.isChecked());
                hygList.add(hy7.isChecked());
                hygList.add(hy8.isChecked());
                mainTripList.setHygList(hygList);


                for(int i=0;i<hygList.size();i++)
                {
                    if(hygList.get(i) == true)
                    {
                        hydBuild.append("T");
                        hydBuild.append(";");
                    }else
                    {
                        hydBuild.append("F");
                    }
                }
*/

                SharedPreferences.Editor editor = sharedPreferences.edit();
                tripdetailsSet.add(esenBuild.toString());
                tripdetailsSet.add(clothBuild.toString());
                tripdetailsSet.add(docBuild.toString());
                tripdetailsSet.add(healBuild.toString());
                tripdetailsSet.add(hydBuild.toString());
                editor.putStringSet(tripEsenKey,tripdetailsSet);
                editor.commit();
            }
        });




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
                MainActivity.this,
        MyTriplistActivity.class);
        startActivity(i);

        return true;
        }


private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Essentials(), "Essentials");
        adapter.addFragment(new Documents(), "Documents");
        adapter.addFragment(new Clothing(), "Clothing");
        adapter.addFragment(new Health(), "Health");
        adapter.addFragment(new Hygiene(), "Hygiene");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
