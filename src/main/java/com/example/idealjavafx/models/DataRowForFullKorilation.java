package com.example.idealjavafx.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
