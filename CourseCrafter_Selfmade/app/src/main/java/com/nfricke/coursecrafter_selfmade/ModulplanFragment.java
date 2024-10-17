package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class ModulplanFragment extends Fragment {

    private MainActivity parent;
    private int currentDayIndex = 1;

    private TextView currentDayTextView;
    private TextView moduleScheduleTextView;
    private Button previousDayButton;
    private Button nextDayButton;


    public ModulplanFragment (MainActivity p){
        this.parent = p;
    }

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

        currentDayTextView = view.findViewById(R.id.currentDayTextView);
        moduleScheduleTextView = view.findViewById(R.id.moduleScheduleTextView);
        previousDayButton = view.findViewById(R.id.previousDayButton);
        nextDayButton = view.findViewById(R.id.nextDayButton);

        previousDayButton.setOnClickListener(v -> navigateDay(false)); // Move to the previous day
        nextDayButton.setOnClickListener(v -> navigateDay(true)); // Move to the next day

        // Initialize display for the first day
        updateDayView();

        return view;
    }

    private void navigateDay(boolean forward) {
        if (forward) {
            currentDayIndex = ((currentDayIndex) % (parent.wochentage.length-1))+1;
        } else {
            currentDayIndex = ((currentDayIndex - 2 + (parent.wochentage.length-1)) % (parent.wochentage.length-1))+1;
        }
        updateDayView();
    }

    private void updateDayView() {
        currentDayTextView.setText(parent.wochentage[currentDayIndex]);
        displayModulesForCurrentDay();
    }

    private void displayModulesForCurrentDay() {
        StringBuilder scheduleBuilder = new StringBuilder();

        for (int block = 1; block < parent.bloecke.length; block++) {
            // Header for the block time
            scheduleBuilder.append(parent.bloecke[block]).append(":\n");

            // Fetch modules for the current day and block
            String[][] modules = parent.modulManager.getByTagBlock(currentDayIndex, block);

            if (modules.length == 0) {
                scheduleBuilder.append("  Keine Veranstaltungen\n");
            } else {
                for (String[] module : modules) {
                    scheduleBuilder
                            .append("  ")
                            .append(module[0]) // Module Name
                            .append(" - Raum: ")
                            .append(module[1]) // Room
                            .append("\n");
                }
            }
            scheduleBuilder.append("\n");
        }

        moduleScheduleTextView.setText(scheduleBuilder.toString());
    }
}








/*package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class ModulplanFragment extends Fragment {

    public ModulplanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modulplan, container, false);

        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return inflater.inflate(R.layout.fragment_modulplan_landscape,
                    container, false);
        } else {
            return inflater.inflate(R.layout.fragment_modulplan, container, false);
        }
    }
}*/



