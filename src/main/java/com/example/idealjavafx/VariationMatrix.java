package com.example.idealjavafx;

import javafx.beans.property.SimpleStringProperty;

public class VariationMatrix {
    public SimpleStringProperty xAndy;
    public SimpleStringProperty x1;
    public SimpleStringProperty x2;
    public SimpleStringProperty x3;
    public SimpleStringProperty x4;
    public SimpleStringProperty x5;
    public SimpleStringProperty x6;
    public SimpleStringProperty x7;

    public VariationMatrix(String xAndy, String x1, String x2, String x3, String x4, String x5, String x6, String x7) {
        this.xAndy = new SimpleStringProperty(xAndy);
        this.x1 = new SimpleStringProperty(x1);
        this.x2 = new SimpleStringProperty(x2);
        this.x3 = new SimpleStringProperty(x3);
        this.x4 = new SimpleStringProperty(x4);
        this.x5 = new SimpleStringProperty(x5);
        this.x6 = new SimpleStringProperty(x6);
        this.x7 = new SimpleStringProperty(x7);
    }

    public String getxAndy() {
        return xAndy.get();
    }

    public SimpleStringProperty xAndyProperty() {
        return xAndy;
    }

    public void setxAndy(String xAndy) {
        this.xAndy.set(xAndy);
    }

    public String getX1() {
        return x1.get();
    }

    public SimpleStringProperty x1Property() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1.set(x1);
    }

    public String getX2() {
        return x2.get();
    }

    public SimpleStringProperty x2Property() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2.set(x2);
    }

    public String getX3() {
        return x3.get();
    }

    public SimpleStringProperty x3Property() {
        return x3;
    }

    public void setX3(String x3) {
        this.x3.set(x3);
    }

    public String getX4() {
        return x4.get();
    }

    public SimpleStringProperty x4Property() {
        return x4;
    }

    public void setX4(String x4) {
        this.x4.set(x4);
    }

    public String getX5() {
        return x5.get();
    }

    public SimpleStringProperty x5Property() {
        return x5;
    }

    public void setX5(String x5) {
        this.x5.set(x5);
    }

    public String getX6() {
        return x6.get();
    }

    public SimpleStringProperty x6Property() {
        return x6;
    }

    public void setX6(String x6) {
        this.x6.set(x6);
    }

    public String getX7() {
        return x7.get();
    }

    public SimpleStringProperty x7Property() {
        return x7;
    }

    public void setX7(String x7) {
        this.x7.set(x7);
    }
}
