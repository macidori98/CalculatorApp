package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSub, btnEqual,
            btnMul, btnDiv, btnAC, btnDel, btnDot;
    private TextView tvHistory, tvResult;
    private String number = null, status = "";
    private double firstNumber, lastNumber;
    private boolean operator = false;

    private final DecimalFormat myFormatter = new DecimalFormat("######.######");

    private String history, currentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setOnClickListeners();
    }

    private void initView() {
        btn0 = findViewById(R.id.zeroButton);
        btn1 = findViewById(R.id.oneButton);
        btn2 = findViewById(R.id.twoButton);
        btn3 = findViewById(R.id.threeButton);
        btn4 = findViewById(R.id.fourButton);
        btn5 = findViewById(R.id.fiveButton);
        btn6 = findViewById(R.id.sixButton);
        btn7 = findViewById(R.id.sevenButton);
        btn8 = findViewById(R.id.eightButton);
        btn9 = findViewById(R.id.nineButton);
        btnAdd = findViewById(R.id.additionButton);
        btnMul = findViewById(R.id.multiplyButton);
        btnSub = findViewById(R.id.subtractionButton);
        btnDiv = findViewById(R.id.divisionButton);
        btnDel = findViewById(R.id.deleteButton);
        btnAC = findViewById(R.id.acButton);
        btnEqual = findViewById(R.id.equalButton);
        btnDot = findViewById(R.id.dotButton);

        tvHistory = findViewById(R.id.historyTextView);
        tvResult = findViewById(R.id.resultTextView);
    }

    private void setOnClickListeners() {
        btn0.setOnClickListener(v -> numberClick("0"));

        btn1.setOnClickListener(v -> numberClick("1"));

        btn2.setOnClickListener(v -> numberClick("2"));

        btn3.setOnClickListener(v -> numberClick("3"));

        btn4.setOnClickListener(v -> numberClick("4"));

        btn5.setOnClickListener(v -> numberClick("5"));

        btn6.setOnClickListener(v -> numberClick("6"));

        btn7.setOnClickListener(v -> numberClick("7"));

        btn8.setOnClickListener(v -> numberClick("8"));

        btn9.setOnClickListener(v -> numberClick("9"));

        btnAdd.setOnClickListener(v -> {
            history = tvHistory.getText().toString();
            currentResult = tvResult.getText().toString();
            tvHistory.setText(history.concat(currentResult.concat("+")));

            if (operator) {
                switch (status) {
                    case "multiply":
                        multiply();
                        break;
                    case "division":
                        divide();
                        break;
                    case "subtraction":
                        minus();
                        break;
                    default:
                        addition();
                        break;
                }

                status = "addition";
                operator = false;
                number = null;
            }
        });

        btnSub.setOnClickListener(v -> {
            history = tvHistory.getText().toString();
            currentResult = tvResult.getText().toString();
            tvHistory.setText(history.concat(currentResult.concat("-")));

            if (operator) {
                switch (status) {
                    case "multiply":
                        multiply();
                        break;
                    case "division":
                        divide();
                        break;
                    case "addition":
                        addition();
                        break;
                    default:
                        minus();
                        break;
                }

                status = "subtraction";
                operator = false;
                number = null;
            }
        });

        btnMul.setOnClickListener(v -> {
            history = tvHistory.getText().toString();
            currentResult = tvResult.getText().toString();
            tvHistory.setText(history.concat(currentResult.concat("*")));

            if (operator) {
                switch (status) {
                    case "subtraction":
                        minus();
                        break;
                    case "division":
                        divide();
                        break;
                    case "addition":
                        addition();
                        break;
                    default:
                        multiply();
                        break;
                }

                status = "multiply";
                operator = false;
                number = null;
            }
        });

        btnEqual.setOnClickListener(v -> {
            history = tvHistory.getText().toString();
            currentResult = tvResult.getText().toString();
            tvHistory.setText(history.concat(currentResult.concat("=")));

            if (operator) {
                switch (status) {
                    case "subtraction":
                        minus();
                        break;
                    case "division":
                        divide();
                        break;
                    case "addition":
                        addition();
                        break;
                    case "multiply":
                        multiply();
                        break;
                    default:
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        break;
                }
            }

            operator = false;
        });

        btnDiv.setOnClickListener(v -> {
            history = tvHistory.getText().toString();
            currentResult = tvResult.getText().toString();
            tvHistory.setText(history.concat(currentResult.concat("/")));

            if (operator) {
                switch (status) {
                    case "subtraction":
                        minus();
                        break;
                    case "multiply":
                        multiply();
                        break;
                    case "addition":
                        addition();
                        break;
                    default:
                        divide();
                        break;
                }

                status = "divide";
                operator = false;
                number = null;
            }
        });

        btnAC.setOnClickListener(v -> {
            number = null;
            status = "";
            tvResult.setText(R.string.zero);
            tvHistory.setText("");
            lastNumber = 0;
            firstNumber = 0;
        });

        btnDot.setOnClickListener(v -> {
            if (number == null) {
                number = "0.";
            } else {
                number = number.concat(".");
            }

            tvResult.setText(number);
        });

        btnDel.setOnClickListener(v -> {
            number = tvResult.getText().toString();
            number = number.substring(0, number.length()-1);
            tvResult.setText(number);
        });
    }

    private void numberClick(String view) {
        if (number == null) {
            number = view;
        } else {
            number = number.concat(view);
        }

        tvResult.setText(number);
        operator = true;
    }

    private void addition() {
        lastNumber = Double.parseDouble(tvResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        resultDisplayOnScreen();
    }

    private void minus() {
        if (firstNumber == 0) {
            firstNumber = Double.parseDouble(tvResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(tvResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }

        resultDisplayOnScreen();
    }

    private void multiply() {
        if (firstNumber == 0) {
            firstNumber = 1;
        }

        lastNumber = Double.parseDouble(tvResult.getText().toString());
        firstNumber = firstNumber * lastNumber;

        resultDisplayOnScreen();
    }

    private void divide() {
        lastNumber = Double.parseDouble(tvResult.getText().toString());

        if (firstNumber == 0) {
            firstNumber = 1;
            firstNumber = lastNumber / 1;
        } else {
            firstNumber = firstNumber / lastNumber;
        }

        resultDisplayOnScreen();
    }

    private void resultDisplayOnScreen() {
        tvResult.setText(myFormatter.format(firstNumber));
    }
}