package com.example.prog5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String currentUnits = "";
    double currentBMI = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup englishOrMetricRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Button calculateBMI = (Button) findViewById(R.id.calculateButton);
        Button adviceButton = (Button) findViewById(R.id.adviceButton);
        final TextView bmiOutput = (TextView) findViewById(R.id.bmiOutput);
        englishOrMetricRadioGroup.clearCheck();
        final EditText weightInput = (EditText) findViewById(R.id.weightInput);
        final EditText heightInput = (EditText) findViewById(R.id.heightInput);

        englishOrMetricRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch (checkedID) {
                    case R.id.englishUnits:
                        bmiOutput.setText("");
                        weightInput.setHint("enter weight in pounds");
                        heightInput.setHint("enter height in inches");
                        currentUnits = "english";
                        break;
                    case R.id.metricUnits:
                        bmiOutput.setText("");
                        weightInput.setHint("enter weight in kilograms");
                        heightInput.setHint("enter height in meters");
                        currentUnits = "metric";
                        break;
                }
            }
        });

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weightInput.getText().length() < 1 || heightInput.getText().length() < 1) {
                    toastErrorHelper("Must enter weight and height as well as units");
                    return;
                }

                String weightGlobal = String.valueOf(weightInput.getText());
                String heightGlobal = String.valueOf(heightInput.getText());

                double bmi = computeBMI(weightGlobal, heightGlobal);
                currentBMI = bmi;
                String bmiStringOutput = String.valueOf(bmi);
                String outputText = "Your BMI: " + bmiStringOutput;
                bmiOutput.setText(outputText);
                weightInput.setText("");
                heightInput.setText("");
            }
        });

    }

    private double computeBMI(String weightInput, String heightInput) {
        // need error check for when weight or height are not filled yet
        int weight = Integer.parseInt(weightInput);
        int height = Integer.parseInt(heightInput);
        double answer = 0.0;
        if (currentUnits.equals("english")) {
            answer = (double) ((weight * 703) / (height * height));
        } else if (currentUnits.equals("metric")) {
            answer = (double) (weight / (height * height));
        } else {
            // add toast error message here
            answer = -1.;  // if error return -1.0
        }
        return answer;
    }

    private String giveAdvice(double bmi) {
        String adviceString = "";
        if (bmi < 18.5) {
            adviceString = "underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            adviceString = "normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            adviceString = "overweight";
        } else if (bmi >= 30.0) {
            adviceString = "obese";
        } else {
            adviceString = "error";  // this should never happen but just in case
        }
        return adviceString;
    }

    public void advice(View v) {
        if(currentUnits.length() < 1 || currentBMI < 0.0) {
            toastErrorHelper("Must enter weight and height as well as units");
            return;
        }
        // navigate to new screen to show advice based on the function i made and results
        Intent myIntent = new Intent(this, MainActivity2.class);
        String advice = giveAdvice(currentBMI);
        myIntent.putExtra("ADVICE_TO_SEND", advice);
        startActivity(myIntent);
    }

    private void toastErrorHelper(String errorMessage) {
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
