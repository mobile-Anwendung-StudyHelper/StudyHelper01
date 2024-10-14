package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.util.Log;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    private int[][] spielfeld;
    private int aktuellerSpieler = 1;
    private Button[] buttons;
    private TextView gewinnerTextView;
    private Button neuesSpielButton;

    // Variablen für die Punkte
    private TextView spieler1PunkteTextView;
    private TextView spieler2PunkteTextView;
    private int spieler1Punkte = 0;
    private int spieler2Punkte = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tictactoe_game,
                container, false);

        spielfeld = new int[3][3];
        buttons = new Button[9];

        GridLayout gridLayout = view.findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            buttons[i].setOnClickListener(this);

        }

        gewinnerTextView = view.findViewById(R.id.gewinnerTextView);
        neuesSpielButton = view.findViewById(R.id.neuesSpielButton);
        neuesSpielButton.setOnClickListener(v -> neuesSpiel());

        spieler1PunkteTextView = view.findViewById(R.id.spieler1PunkteTextView);
        spieler2PunkteTextView = view.findViewById(R.id.spieler2PunkteTextView);

        aktualisierePunktestandAnzeige(); // Punktestandanzeige initialisieren

        return view;
    }

    @Override
    public void onClick(View v) {
        int index = -1;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == v) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int reihe = index / 3;
            int spalte = index % 3;

            if (spielfeld[reihe][spalte] == 0) {
                spielfeld[reihe][spalte] = aktuellerSpieler;
                buttons[index].setText(aktuellerSpieler == 1 ? "X" : "O");

                if (hatGewonnen(aktuellerSpieler)) {
                    zeigeGewinner(aktuellerSpieler);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            spielfeld[i][j] = 0;
                        }
                    }
                    for (Button button : buttons) {
                        button.setText("");
                    }
                    aktuellerSpieler = 1;

                    gewinnerTextView.setText("Gewinner:");
                } else if (istUnentschieden()) {
                    zeigeUnentschieden();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            spielfeld[i][j] = 0;
                        }
                    }
                    for (Button button : buttons) {
                        button.setText("");
                    }
                    aktuellerSpieler = 1;

                    gewinnerTextView.setText("Gewinner:");
                } else {
                    aktuellerSpieler = (aktuellerSpieler == 1) ? 2 : 1;
                }
            }
        }
    }

    private boolean hatGewonnen(int spieler) {
        // Überprüfe Reihen
        for (int i = 0; i < 3; i++) {
            if (spielfeld[i][0] == spieler && spielfeld[i][1] == spieler && spielfeld[i][2] == spieler) {
                return true;
            }
        }

        // Überprüfe Spalten
        for (int i = 0; i < 3; i++) {
            if (spielfeld[0][i] == spieler && spielfeld[1][i] == spieler && spielfeld[2][i] == spieler) {
                return true;
            }
        }

        // Überprüfe Diagonalen
        if ((spielfeld[0][0] == spieler && spielfeld[1][1] == spieler && spielfeld[2][2] == spieler) ||
                (spielfeld[0][2] == spieler && spielfeld[1][1] == spieler && spielfeld[2][0] == spieler)) {
            return true;
        }

        return false;
    }

    private boolean istUnentschieden() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (spielfeld[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void zeigeGewinner(int spieler) {
        Toast.makeText(getContext(), "Spieler " + spieler + " hat gewonnen!", Toast.LENGTH_SHORT).show();

        // Punktestand aktualisieren
        if (spieler == 1) {
            spieler1Punkte++;
        } else {
            spieler2Punkte++;
        }

        // Punktestand anzeigen
        aktualisierePunktestandAnzeige();
    }

    private void aktualisierePunktestandAnzeige() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spieler1PunkteTextView.setText("Spieler 1: " + spieler1Punkte);
                spieler2PunkteTextView.setText("Spieler 2: " + spieler2Punkte);
            }
        });
    }

    private void zeigeUnentschieden() {
        Toast.makeText(getContext(), "Unentschieden!", Toast.LENGTH_SHORT).show();
    }

    private void neuesSpiel() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spielfeld[i][j] = 0;
            }
        }
        for (Button button : buttons) {
            button.setText("");
        }
        aktuellerSpieler = 1;

        gewinnerTextView.setText("Gewinner:");

        // Punktestand zurücksetzen
        spieler1Punkte = 0;
        spieler2Punkte = 0;
        aktualisierePunktestandAnzeige();
    }
}