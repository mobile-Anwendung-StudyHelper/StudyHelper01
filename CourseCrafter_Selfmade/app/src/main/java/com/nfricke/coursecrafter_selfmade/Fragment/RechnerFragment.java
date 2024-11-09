package com.nfricke.coursecrafter_selfmade.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.R;

public class RechnerFragment extends Fragment implements View.OnClickListener {

    private TextView display;
    private StringBuilder input = new StringBuilder();
    private double result = 0;
    private String operator = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rechner, container, false);

        display = view.findViewById(R.id.display);

        initializeButtons(view);

        return view;
    }

    private void initializeButtons(View view) {
        int[] buttonIDs = new int[]{
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonDecimal, R.id.buttonEqual, R.id.buttonClearAll, R.id.buttonClear,
                R.id.buttonProzent, R.id.buttonBack
        };

        for (int id : buttonIDs) {
            view.findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        if(input.toString().equals("Error")) {
            input.setLength(0);
        }

        if ("0123456789.".contains(buttonText)) {
            if (input.length() <= 10) input.append(buttonText);
        } else if ("+-*/%".contains(buttonText)) {
            processOperator(buttonText);
        } else if ("=".equals(buttonText)) {
            calculate();
        } else if ("C".equals(buttonText)) {
            input.setLength(0);
            operator = "";
        } else if ("AC".equals(buttonText)) {
            input.setLength(0);
            result = 0;
            operator = "";
        } else if ("◄".equals(buttonText)) {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
            }
        }
        updateDisplay();
    }

    private void processOperator(String buttonText) {
        if (input.length() > 0) {
            calculate();
            operator = buttonText;
        } else {
            operator = buttonText;
        }
    }

    private void calculate() {
        if (input.length() > 0) {
            try {
                double value = Double.parseDouble(input.toString());
                input.setLength(0);
                switch (operator) {
                    case "+":
                        result += value;
                        break;
                    case "-":
                        result -= value;
                        break;
                    case "*":
                        result *= value;
                        break;
                    case "/":
                        if (value != 0) {
                            result /= value;
                        } else {
                            input.append("Error");
                        }
                        break;
                    case "%":
                        result = result / 100 * value;
                        break;
                    default:
                        result = value;
                        break;
                }
                operator = "";
            } catch (NumberFormatException e) {
                input.append("Error");
            }
        }
    }

    private void updateDisplay() {
        if (input.length() == 0) {
            display.setText(String.valueOf(result));
        } else {
            display.setText(input.toString());
        }
    }
}