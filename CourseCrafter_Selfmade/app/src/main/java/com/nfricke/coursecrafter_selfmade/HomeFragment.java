package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private ModulManager modulManager;
    private TodoManager todoManager;
    private String[] bloecke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (getActivity() instanceof MainActivity) {
            modulManager = ((MainActivity) getActivity()).modulManager;
            todoManager = ((MainActivity) getActivity()).todoManager;
            bloecke = ((MainActivity) getActivity()).bloecke;
        }

        TextView homeModules = view.findViewById(R.id.HomeModules);
        ProgressBar homeProgressBar = view.findViewById(R.id.homeProgressBar);
        TextView homeProgressText = view.findViewById(R.id.homeProgressText);
        TextView homeNoteText = view.findViewById(R.id.homeNoteText);

        int todayIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        StringBuilder modulesToday = new StringBuilder();

        for (Modul modul : modulManager) {
            for (int n = 0; n < Modul.getAnzahlVeranstaltungen(); n++) {
                if (modul.getTag(n) == todayIndex && modul.isBelegt()) {
                    modulesToday.append(modul.getModulName());
                    if (modul.getBlock(n) > 0 && modul.getBlock(n) < bloecke.length) {
                        modulesToday.append(" - ").append(bloecke[modul.getBlock(n)].contains("-") ? bloecke[modul.getBlock(n)].substring(0, bloecke[modul.getBlock(n)].indexOf('-')) : bloecke[modul.getBlock(n)]);
                    }
                    if (!modul.getRaum(n).isEmpty()) modulesToday.append(" @ ").append(modul.getRaum(n));
                    modulesToday.append("\n");
                }
            }
        }
        homeModules.setText(modulesToday.length() > 0 ? modulesToday.toString() : "Heute keine Module");

        homeProgressBar.setProgress((int) todoManager.getAnzahlProzent(true));
        homeProgressText.setText((int) todoManager.getAnzahlProzent(true) + "% der Aufgaben erledigt (" + todoManager.getAnzahl(true) + "/" + todoManager.getAnzahl() + ")");

        double durchschnitt = modulManager.durchschnitt();
        homeNoteText.setText((durchschnitt > 0 ? "Durchschnittsnote: " + durchschnitt : "Keine Noten"));

        return view;
    }
}