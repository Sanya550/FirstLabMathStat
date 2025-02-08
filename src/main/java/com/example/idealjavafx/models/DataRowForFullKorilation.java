package com.example.idealjavafx.models;


public class DataRowForFullKorilation {
    private String index;
    private String value;
    private String fValue;
    private String zna;

    public void setIndex(String index) {
        this.index = index;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFValue(String fValue) {
        this.fValue = fValue;
    }

    public void setZna(String zna) {
        this.zna = zna;
    }

    public DataRowForFullKorilation(String index, String value, String fValue, String zna) {
        this.index = index;
        this.value = value;
        this.fValue = fValue;
        this.zna = zna;
    }

    public DataRowForFullKorilation() {
    }

    public String getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public String getfValue() {
        return fValue;
    }

    public void setfValue(String fValue) {
        this.fValue = fValue;
    }

    public String getZna() {
        return zna;
    }
}
