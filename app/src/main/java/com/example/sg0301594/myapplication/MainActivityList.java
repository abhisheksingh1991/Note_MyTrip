package com.example.sg0301594.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SG0301594 on 3/15/2018.
 */

public class MainActivityList {

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public List<Boolean> getEsenList() {
        return esenList;
    }

    public void setEsenList(List<Boolean> esenList) {
        this.esenList = esenList;
    }

    private String tripName;

    private List<Boolean> esenList = new ArrayList<Boolean>();

    private List<Boolean> clothList = new ArrayList<Boolean>();

    public List<Boolean> getHygList() {
        return hygList;
    }

    public void setHygList(List<Boolean> hygList) {
        this.hygList = hygList;
    }

    private List<Boolean> hygList = new ArrayList<Boolean>();


    public List<Boolean> getClothList() {
        return clothList;
    }

    public void setClothList(List<Boolean> clothList) {
        this.clothList = clothList;
    }

    public List<Boolean> getDocList() {
        return docList;
    }

    public void setDocList(List<Boolean> docList) {
        this.docList = docList;
    }

    public List<Boolean> getHealthList() {
        return healthList;
    }

    public void setHealthList(List<Boolean> healthList) {
        this.healthList = healthList;
    }

    private List<Boolean> docList = new ArrayList<Boolean>();

    private List<Boolean> healthList = new ArrayList<Boolean>();
}
