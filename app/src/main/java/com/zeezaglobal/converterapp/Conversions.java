package com.zeezaglobal.converterapp;

import android.util.Log;

import java.text.DecimalFormat;

public class Conversions {



    public Double centimeterToInch(double parseDouble) {
        return 0.39 * parseDouble;
    }

    public Double InchToCentimeter(double parseDouble) {
        return  parseDouble*2.54;
    }

    public Double kilometerToMiles(double parseDouble) {
        return  parseDouble*0.62;
    }

    public Double KilogramToLb(double parseDouble) {
        return  parseDouble*2.2;
    }

    public Double MilesToKilometer(double parseDouble) {
        return  parseDouble/0.62;
    }

    public Double LbtoKilogram(double parseDouble) {
        return  parseDouble/2.2;
    }
    public Double roundFunction(double parseDouble) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(parseDouble));
    }
}
