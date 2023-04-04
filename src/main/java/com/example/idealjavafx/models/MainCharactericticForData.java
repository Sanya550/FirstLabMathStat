package com.example.idealjavafx.models;

public class MainCharactericticForData {
    public String characteristic;
    public double inf;
    public double values;
    public double sup;
    public double average;

    public MainCharactericticForData(String characteristic, double inf, double values, double sup, double average) {
        this.characteristic = characteristic;
        this.inf = inf;
        this.values = values;
        this.sup = sup;
        this.average = average;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public double getInf() {
        return inf;
    }

    public void setInf(double inf) {
        this.inf = inf;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public double getSup() {
        return sup;
    }

    public void setSup(double sup) {
        this.sup = sup;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
