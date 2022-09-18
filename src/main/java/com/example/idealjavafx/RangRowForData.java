package com.example.idealjavafx;

public class RangRowForData {
    public String gerenalVarRow;
    public double value;
    public double rang;

    public RangRowForData(String gerenalVarRow, double value, double rang) {
        this.gerenalVarRow = gerenalVarRow;
        this.value = value;
        this.rang = rang;
    }

    public String getGerenalVarRow() {
        return gerenalVarRow;
    }

    public void setGerenalVarRow(String gerenalVarRow) {
        this.gerenalVarRow = gerenalVarRow;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getRang() {
        return rang;
    }

    public void setRang(double rang) {
        this.rang = rang;
    }
}
