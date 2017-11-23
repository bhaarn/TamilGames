package com.padhuga.tamil.games.models;

import java.util.ArrayList;

public class Data {
    private String title;
    private String parentCount;
    private ArrayList<String> parentHeading;
    private String parentType;
    private ArrayList<Integer> items;
    private ArrayList<Data> type;
    private ArrayList<Results> result;

    public String getTitle() {
        return title;
    }

    public String getParentCount() {
        return parentCount;
    }

    public ArrayList<String> getParentHeading() {
        return parentHeading;
    }

    public String getParentType() {
        return parentType;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public ArrayList<Data> getType() {
        return type;
    }

    public ArrayList<Results> getResult() {
        return result;
    }
}
