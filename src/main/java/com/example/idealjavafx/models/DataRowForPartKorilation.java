package com.example.idealjavafx.models;


public class DataRowForPartKorilation {
    private String index;
    private String lowValue;
    private String value;
    private String highValue;
    private String tValue;
    private String zna;

    public void setIndex(String index) {
        this.index = index;
    }

    public void setLowValue(String lowValue) {
        this.lowValue = lowValue;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setHighValue(String highValue) {
        this.highValue = highValue;
    }

    public void setTValue(String tValue) {
        this.tValue = tValue;
    }

    public void setZna(String zna) {
        this.zna = zna;
    }

    public DataRowForPartKorilation(String index, String lowValue, String value, String highValue, String tValue, String zna) {
        this.index = index;
        this.lowValue = lowValue;
        this.value = value;
        this.highValue = highValue;
        this.tValue = tValue;
        this.zna = zna;
    }

    public DataRowForPartKorilation() {
    }

    public String getIndex() {
        return index;
    }

    public String getLowValue() {
        return lowValue;
    }

    public String getValue() {
        return value;
    }

    public String getHighValue() {
        return highValue;
    }

    public String gettValue() {
        return tValue;
    }

    public void settValue(String tValue) {
        this.tValue = tValue;
    }

    public String getZna() {
        return zna;
    }
}
