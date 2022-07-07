package com.zeezaglobal.converterapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_one, et_two;
    private boolean etOneStatus = false, etTwoStatus = false;
    private Spinner spinner;
    private TextView unitOne, unitTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setSpinner();
        spinnerListener();

        et_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    etOneStatus = true;
                } else {
                    etOneStatus = false;
                }
            }
        });
        et_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    etTwoStatus = true;
                } else {
                    etTwoStatus = false;
                }
            }
        });
        et_one.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etOneStatus)
                    if (!s.toString().equals(""))
                        et_two.setText(doConversion(Double.parseDouble(s.toString())));
            }
        });
        et_two.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etTwoStatus)
                    if (!s.toString().equals(""))
                        et_one.setText(doBackConversion(Double.parseDouble(s.toString())));
            }
        });
    }

    private void spinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case Constants.CENTIMETER_TO_INCH:
                        et_one.setText("");
                        et_two.setText("");
                      unitOne.setText("Cm");
                      unitTwo.setText("In");
                      break;
                    case Constants.KILOGRAM_TO_LB:
                        et_one.setText("");
                        et_two.setText("");
                        unitOne.setText("Kg");
                        unitTwo.setText("Lb");
                        break;
                    case Constants.KILOMETER_TO_MILES:
                        et_one.setText("");
                        et_two.setText("");
                        unitOne.setText("Km");
                        unitTwo.setText("Mi");
                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String doBackConversion(double parseDouble) {
        Conversions conversions = new Conversions();
        switch (spinner.getSelectedItemPosition()) {
            case Constants.CENTIMETER_TO_INCH:
                return conversions.roundFunction(conversions.InchToCentimeter(parseDouble)).toString();

            case Constants.KILOMETER_TO_MILES:
                return conversions.roundFunction(conversions.MilesToKilometer(parseDouble)).toString();
            case Constants.KILOGRAM_TO_LB:
                return conversions.roundFunction(conversions.LbtoKilogram(parseDouble)).toString();
            default:
                return "";
        }

    }

    private String doConversion(double parseDouble) {
        Conversions conversions = new Conversions();
        switch (spinner.getSelectedItemPosition()) {
            case Constants.CENTIMETER_TO_INCH:
                return conversions.roundFunction(conversions.centimeterToInch(parseDouble)).toString();
            case Constants.KILOMETER_TO_MILES:
                return conversions.roundFunction(conversions.kilometerToMiles(parseDouble)).toString();
            case Constants.KILOGRAM_TO_LB:
                return conversions.roundFunction(conversions.KilogramToLb(parseDouble)).toString();
            default:
                return "";
        }

    }

    private void setSpinner() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    private void initComponents() {
        et_one = findViewById(R.id.editTextTextOne);
        et_two = findViewById(R.id.editTextTextTwo);
        spinner = (Spinner) findViewById(R.id.spinner);
        unitOne =  findViewById(R.id.unit_one);
        unitTwo =  findViewById(R.id.unit_two);
    }
}