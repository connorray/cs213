package com.example.prog5;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

/**
 * The second screen for the advice output.
 *
 * @author Raymond Yoo
 * @author Paul Chung
 */
public class MainActivity2 extends AppCompatActivity {
    private String adviceGet = "normal";
    private ImageView img;
    private TextView adviceOutput;
    private HashMap<String, String> advices = new HashMap<>();

    /**
     * Creates the new activity screen.
     * @param savedInstanceState the state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String currentAdvice = getIntent().getStringExtra("ADVICE_TO_SEND");
        adviceGet = currentAdvice;
        img=(ImageView)findViewById(R.id.imageView2);
        adviceOutput = (TextView) findViewById(R.id.textView2);
        populateMap();
        show();
    }

    /**
     * Populates the advice hashmap to show an advice text on screen.
     */
    private void populateMap() {
        advices.put("overweight", "Try to lose a little bit of weight");
        advices.put("normal", "Try to keep your weight! Good job!");
        advices.put("underweight", "Try to gain a little weight!");
        advices.put("obese", "Try to lose a lot of weight.");
    }

    /**
     * Determines which image to show based on the current advice string, which is given from the
     * main screen.
     */
    public void show() {
        switch(adviceGet){
            case "overweight":
                img.setImageResource(R.drawable.overweight);
                adviceOutput.setText(advices.get(adviceGet));
                break;
            case "underweight":
                img.setImageResource(R.drawable.underweight);
                adviceOutput.setText(advices.get(adviceGet));
                break;
            case "normal":
                img.setImageResource(R.drawable.normalweight);
                adviceOutput.setText(advices.get(adviceGet));
                break;
            case "obese":
                img.setImageResource(R.drawable.obese);
                adviceOutput.setText(advices.get(adviceGet));
                break;
            default:
                img.setImageResource(R.drawable.normalweight);
                break;
        }
    }
}
