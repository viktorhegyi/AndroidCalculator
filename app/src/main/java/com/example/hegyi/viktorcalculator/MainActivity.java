package com.example.hegyi.viktorcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button zero, one, two, three, four, five, six, seven, eight, nine;
    Button plus, minus, division, multiply, del, dot, equal;

    EditText equationDisplay, resultDisplay;

    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        division = (Button) findViewById(R.id.division);
        multiply = (Button) findViewById(R.id.multiply);
        del = (Button) findViewById(R.id.del);
        dot = (Button) findViewById(R.id.dot);
        equal = (Button) findViewById(R.id.equal);

        equationDisplay = (EditText) findViewById(R.id.equationDisplay);
        resultDisplay = (EditText) findViewById(R.id.resultDisplay);

        setUpListener(zero, 0);
        setUpListener(one, 1);
        setUpListener(two, 2);
        setUpListener(three, 3);
        setUpListener(four, 4);
        setUpListener(five, 5);
        setUpListener(six, 6);
        setUpListener(seven, 7);
        setUpListener(eight, 8);
        setUpListener(nine, 9);

        setUpOperationListener(plus, "+");
        setUpOperationListener(minus, "-");
        setUpOperationListener(division, "÷");
        setUpOperationListener(multiply, "x");
        setUpOperationListener(dot, ".");

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] operator = result.split("[0-9.]+");
                String[] number = result.split("[-+x÷]");
                float result;

                if (number[0].contains(".")) {
                  result = Float.parseFloat(number[0]);
                } else {
                    result = Integer.parseInt(number[0]);
                }

                for(int i = 1; i < number.length; i++){
                    if(operator[i].equals("+")) {
                        if ( number[i].contains(".")) {
                            result += Float.parseFloat(number[i]);
                        } else {
                            result += Integer.parseInt(number[i]);
                        }
                    } else if (operator[i].equals("-")) {
                        if ( number[i].contains(".")) {
                            result -= Float.parseFloat(number[i]);
                        } else {
                            result -= Integer.parseInt(number[i]);
                        }
                    } else if (operator[i].equals("x")) {
                        if ( number[i].contains(".")) {
                            result *= Float.parseFloat(number[i]);
                        } else {
                            result *= Integer.parseInt(number[i]);
                        }
                    } else if (operator[i].equals("÷")){
                        if ( number[i].contains(".")) {
                            result /= Float.parseFloat(number[i]);
                        } else {
                            result /= Integer.parseInt(number[i]);
                        }
                    }
                }

                if ( result % 1.0 == 0) {
                    int resultInt = (int) result;
                    resultDisplay.setText(resultInt + "");
                } else {
                    resultDisplay.setText(result + "");
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equationDisplay.setText("");
                resultDisplay.setText("");
                result = "";
            }
        });

    }

    void setUpListener(Button button, final int i) {
        final String number = "" + i;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equationDisplay.setText(equationDisplay.getText() + number);
                result = result + i;
            }
        });
    }

    void setUpOperationListener(Button button, final String input) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!result.endsWith("+") && !result.endsWith("-") && !result.endsWith("x") &&
                        !result.endsWith("÷") &&  !result.endsWith(".")) {
                    equationDisplay.setText(equationDisplay.getText() + input);
                    result = result + input;
                }
            }
        });
    }

}
