package com.nfricke.coursecrafter_selfmade.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.R;

import java.util.Calendar;

public class ModulplanFragment extends Fragment {

    private int currentDayIndex = 1;

    private TextView currentDayTextView;
    private TextView moduleScheduleTextView;
    private Button previousDayButton;
    private Button nextDayButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set currentDayIndex to the current weekday
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.MONDAY:
                currentDayIndex = 1;
                break;
            case Calendar.TUESDAY:
                currentDayIndex = 2;
                break;
            case Calendar.WEDNESDAY:
                currentDayIndex = 3;
                break;
            case Calendar.THURSDAY:
                currentDayIndex = 4;
                break;
            case Calendar.FRIDAY:
                currentDayIndex = 5;
                break;
            case Calendar.SATURDAY:
                currentDayIndex = 6;
                break;
            case Calendar.SUNDAY:
                currentDayIndex = 7;
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modulplan, container, false);

        //Initialize layout links
        currentDayTextView = view.findViewById(R.id.currentDayTextView);
        moduleScheduleTextView = view.findViewById(R.id.moduleScheduleTextView);
        previousDayButton = view.findViewById(R.id.previousDayButton);
        nextDayButton = view.findViewById(R.id.nextDayButton);

        //Initialize button listeners
        previousDayButton.setOnClickListener(v -> navigateDay(false)); // Move to the previous day
        nextDayButton.setOnClickListener(v -> navigateDay(true)); // Move to the next day

        // Initialize display for the first day
        updateDayView();

        return view;
    }

    //navigation function (boolean true == forward / false == backwards
    private void navigateDay(boolean forward) {
        if (forward) {
            currentDayIndex = ((currentDayIndex) % (((MainActivity)getActivity()).wochentage.length-1))+1;
        } else {
            currentDayIndex = ((currentDayIndex - 2 + (((MainActivity)getActivity()).wochentage.length-1)) % (((MainActivity)getActivity()).wochentage.length-1))+1;
        }
        updateDayView();
    }

    //Set modules and Day
    private void updateDayView() {
        currentDayTextView.setText(((MainActivity)getActivity()).wochentage[currentDayIndex]);
        displayModulesForCurrentDay();
    }

    //Get module by day, sort and display
    private void displayModulesForCurrentDay() {
        StringBuilder scheduleBuilder = new StringBuilder();

        for (int block = 1; block < ((MainActivity)getActivity()).bloecke.length; block++) {
            // Fetch modules for the current day and block
            String[][] modules = ((MainActivity)getActivity()).modulManager.getByTagBlock(currentDayIndex, block);

            if (modules.length > 0) {
                // Header for the block time
                scheduleBuilder.append(((MainActivity)getActivity()).bloecke[block]).append(":\n");
                for (String[] module : modules) {
                    scheduleBuilder
                            .append("")
                            .append(module[0]) // Module Name
                            .append(" - " + getString(R.string.room) + ": ")
                            .append(module[1]) // Room
                            .append("\n");
                }
                scheduleBuilder.append("\n");
            }
        }
        moduleScheduleTextView.setText(scheduleBuilder.length() > 0 ? scheduleBuilder.toString() : getString(R.string.no_modul));
    }
}