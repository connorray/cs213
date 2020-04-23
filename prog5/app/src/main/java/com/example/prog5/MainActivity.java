package com.example.prog5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String currentUnits = "";


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
                String weightGlobal = String.valueOf(weightInput.getText());
                String heightGlobal = String.valueOf(heightInput.getText());
                double bmi = computeBMI(weightGlobal, heightGlobal);
                String bmiStringOutput = String.valueOf(bmi);
                String outputText = "Your BMI: " + bmiStringOutput;
                bmiOutput.setText(outputText);
                weightInput.setText("");
                heightInput.setText("");
            }
        });

        adviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate to new screen to show advice based on the function i made and results

            }
        });

    }

    private double computeBMI(String weightInput, String heightInput) {
        // need error check for when weight or height are not filled yet
        int weight = Integer.parseInt(weightInput);
        int height = Integer.parseInt(heightInput);
        double answer = 0.0;
        if(currentUnits.equals("english")) {
            answer = (double)((weight * 703) / (height * height));
        }else if(currentUnits.equals("metric")) {
            answer = (double)(weight / (height * height));
        }else {
            // add toast error message here
            answer = -1.;  // if error return -1.0
        }
        return answer;
    }

    private String giveAdvice(double bmi) {
        String adviceString = "";
        if(bmi < 18.5) {
            adviceString = "You are underweight";
        }else if (bmi >= 18.5 && bmi <= 24.9) {
            adviceString = "You are at a good BMI";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            adviceString = "You are overweight";
        }else if (bmi >= 30.0) {
            adviceString = "You are obese";
        }else {
            adviceString = "error";  // this should never happen but just in case
        }
        return adviceString;
    }

}
