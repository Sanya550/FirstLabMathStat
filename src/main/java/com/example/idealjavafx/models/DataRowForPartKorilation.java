package com.example.idealjavafx.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
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
}
