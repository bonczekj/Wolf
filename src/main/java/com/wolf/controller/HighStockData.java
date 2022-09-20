package com.wolf.controller;

import java.util.ArrayList;

/**
 * Created by Jirka on 16.01.2017.
 */



public class HighStockData {
    //private HighStockItem[] data;


    private String name;
    private ArrayList<Long[]> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long[]> getData() {
        return data;
    }

    public void setData(ArrayList<Long[]> data) {
        this.data = data;
    }

}
