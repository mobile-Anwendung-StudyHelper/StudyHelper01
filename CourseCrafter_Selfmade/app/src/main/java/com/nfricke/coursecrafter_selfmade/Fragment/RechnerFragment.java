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

// This fragment class implements a basic calculator interface for a mobile app
public class RechnerFragment extends Fragment implements View.OnClickListener {

    // UI references and variables to handle input, results, and operations
    private TextView display;
    private StringBuilder input = new StringBuilder();
    private double result = 0;
    private String operator = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout and initialize the display
        View view = inflater.inflate(R.layout.fragment_rechner, container, false);
        display = view.findViewById(R.id.display);

        // Set click listeners for all calculator buttons
        initializeButtons(view);

        return view;
    }

    private void initializeButtons(View view) {
        // List of all button IDs to set click listeners
        int[] buttonIDs = new int[]{
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonDecimal, R.id.buttonEqual, R.id.buttonClearAll, R.id.buttonClear,
                R.id.buttonProzent, R.id.buttonBack
        };

        // Assign this class as the click listener for each button
        for (int id : buttonIDs) {
            view.findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks and perform actions based on the button text
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        // Reset input if it contains an error from previous actions
        if (input.toString().equals("Error")) {
            input.setLength(0);
        }

        // Append numerical input or decimal point if length constraint is met
        if ("0123456789.".contains(buttonText)) {
            if (input.length() <= 10) input.append(buttonText);
        }
        // Process arithmetic operators and calculate if needed
        else if ("+-*/%".contains(buttonText)) {
            processOperator(buttonText);
        }
        // Compute result on equals button click
        else if ("=".equals(buttonText)) {
            calculate();
        }
        // Clear current input when C is pressed
        else if ("C".equals(buttonText)) {
            input.setLength(0);
            operator = "";
        }
        // Reset input and result on AC press
        else if ("AC".equals(buttonText)) {
            input.setLength(0);
            result = 0;
            operator = "";
        }
        // Handle backspace (delete last character)
        else if ("◄".equals(buttonText)) {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
            }
        }
        // Always update display after any action
        updateDisplay();
    }

    private void processOperator(String buttonText) {
        // Calculate the existing expression when an operator is pressed
        // Set or update the current operator for next operations
        if (input.length() > 0) {
            calculate();
            operator = buttonText;
        } else {
            operator = buttonText;
        }
    }

    private void calculate() {
        // Parse and compute the input against the current operator
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
                            input.append("Error"); // Handle division by zero
                        }
                        break;
                    case "%":
                        result = result / 100 * value;
                        break;
                    default:
                        result = value;
                        break;
                }
                operator = ""; // Reset operator after calculation
            } catch (NumberFormatException e) {
                input.append("Error"); // Handle input errors
            }
        }
    }

    private void updateDisplay() {
        // Update the display with input or current calculation result
        if (input.length() == 0) {
            display.setText(String.valueOf(result));
        } else {
            display.setText(input.toString());
        }
    }
}