package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        CardView homeModulCard = view.findViewById(R.id.homemodulcard);
        CardView homeTodoCard = view.findViewById(R.id.hometodocard);
        CardView homeAverageCard = view.findViewById(R.id.homeaverageCard);

        homeModulCard.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).replaceFragment(new ModulplanFragment());
                ((MainActivity) getActivity()).appBarText.setText(getString(R.string.modulplan_fragment_title));
                ((MainActivity) getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.modulplan);
            }
        });

        homeTodoCard.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.nav);
                ((MainActivity) getActivity()).replaceFragment(new TodoFragment());
                ((MainActivity) getActivity()).appBarText.setText(getString(R.string.todo_fragment_title));
            }
        });

        homeAverageCard.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.nav);
                ((MainActivity) getActivity()).replaceFragment(new NotenFragment());
                ((MainActivity) getActivity()).appBarText.setText(getString(R.string.noten_fragment_title));
            }
        });

        int todayIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        /*StringBuilder modulesToday = new StringBuilder();
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
        homeModules.setText(modulesToday.length() > 0 ? modulesToday.toString() : "Heute keine Module");*/

        List<ModulWithBlock> todayModulesList = new ArrayList<>();
        for (Modul modul : modulManager) {
            for (int n = 0; n < Modul.getAnzahlVeranstaltungen(); n++) {
                if (modul.getTag(n) == todayIndex && modul.isBelegt()) {
                    todayModulesList.add(new ModulWithBlock(modul, modul.getBlock(n), n));
                }
            }
        }
        Collections.sort(todayModulesList, Comparator.comparingInt(ModulWithBlock::getBlockIndex));
        StringBuilder modulesToday = new StringBuilder();
        for (ModulWithBlock m : todayModulesList) {
            Modul modul = m.getModul();
            int n = m.getModulIndex();
            modulesToday.append(modul.getModulName());
            if (m.getBlockIndex() > 0 && m.getBlockIndex() < bloecke.length) {
                modulesToday.append(" - ");
                modulesToday.append(bloecke[m.getBlockIndex()].contains("-") ? bloecke[m.getBlockIndex()].substring(0, bloecke[m.getBlockIndex()].indexOf('-')) : bloecke[m.getBlockIndex()]);
            }
            if (!modul.getRaum(n).isEmpty()) modulesToday.append(" @ ").append(modul.getRaum(n));
            modulesToday.append("\n");
        }
        homeModules.setText(modulesToday.length() > 0 ? modulesToday.toString() : "Heute keine Module");

        //---

        homeProgressBar.setProgress((int) todoManager.getAnzahlProzent(true));
        homeProgressText.setText((int) todoManager.getAnzahlProzent(true) + "% der Aufgaben erledigt (" + todoManager.getAnzahl(true) + "/" + todoManager.getAnzahl() + ")");

        double durchschnitt = modulManager.durchschnitt();
        homeNoteText.setText((durchschnitt > 0 ? "Durchschnittsnote: " + durchschnitt : "Keine Noten"));

        return view;
    }


    private static class ModulWithBlock {
        private final Modul modul;
        private final int blockIndex;
        private final int modulIndex;

        public ModulWithBlock(Modul modul, int blockIndex, int modulIndex) {
            this.modul = modul;
            this.blockIndex = blockIndex;
            this.modulIndex = modulIndex;
        }

        public Modul getModul() {
            return modul;
        }

        public int getBlockIndex() {
            return blockIndex;
        }

        public int getModulIndex() {
            return modulIndex;
        }
    }

}