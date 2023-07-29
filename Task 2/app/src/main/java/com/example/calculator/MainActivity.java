package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstnum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);


        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button er = findViewById(R.id.er);
        Button div = findViewById(R.id.div);
        Button mul = findViewById(R.id.mul);
        Button min = findViewById(R.id.min);
        Button equal = findViewById(R.id.equal);
        Button plus = findViewById(R.id.plus);
        Button point = findViewById(R.id.point);

        TextView calc = findViewById(R.id.calc);

        ac.setOnClickListener(view -> {
            firstnum = 0;
            calc.setText("0");
        });

        off.setOnClickListener(view -> calc.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            calc.setVisibility(view.VISIBLE);
            calc.setText("0");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (!calc.getText().toString().equals("0")) {
                    calc.setText(calc.getText().toString() + b.getText().toString());
                } else {
                    calc.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> opr = new ArrayList<>();
        opr.add(div);
        opr.add(mul);
        opr.add(plus);
        opr.add(min);
        for (Button b : opr) {
            b.setOnClickListener(view -> {
                firstnum = Double.parseDouble(calc.getText().toString());
                operation = b.getText().toString();
                calc.setText("0");

            });
        }

        er.setOnClickListener(view -> {
            String num = calc.getText().toString();
            if(num.length()>1){
                calc.setText(num.substring(0, num.length()-1));
            } else if (num.length()==1 && !num.equals("0")) {
                calc.setText("0");
            }
        });

        point.setOnClickListener(view -> {
            if(!calc.getText().toString().contains(".")) {
                calc.setText(calc.getText().toString() +".");
            }
        });

        equal.setOnClickListener(view -> {
            double Num2 = Double.parseDouble(calc.getText().toString());
            double result;
            switch (operation) {
                case "/":
                    result = firstnum/Num2;
                    break;
                case "X":
                    result = firstnum*Num2;
                    break;
                case "+":
                    result = firstnum+Num2;
                    break;
                case "-":
                    result = firstnum-Num2;
                    break;
                default:
                    result = firstnum+Num2;
            }
            calc.setText(String.valueOf(result));
            firstnum = result;
        });
    }
}