package com.nfricke.coursecrafter_selfmade.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.R;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    private int[][] spielfeld;
    private int aktuellerSpieler = 1;
    private Button[] buttons;
    private TextView gewinnerTextView;
    private Button neuesSpielButton;

    //Variables for the points
    private TextView spieler1PunkteTextView;
    private TextView spieler2PunkteTextView;
    private int spieler1Punkte = 0;
    private int spieler2Punkte = 0;

    //Initialize fragment
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

        //Initialize layout links
        neuesSpielButton = view.findViewById(R.id.neuesSpielButton);
        neuesSpielButton.setOnClickListener(v -> neuesSpiel());
        spieler1PunkteTextView = view.findViewById(R.id.spieler1PunkteTextView);
        spieler2PunkteTextView = view.findViewById(R.id.spieler2PunkteTextView);
        neuesSpielButton.setText(getString(R.string.new_game));
        aktualisierePunktestandAnzeige(); // Initialize scoreboard

        return view;
    }

    //Initialize game
    @Override
    public void onClick(View v) {
        int index = -1;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == v) {
                index = i;
                break;
            }
        }

        //Game logic
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
                } else {
                    aktuellerSpieler = (aktuellerSpieler == 1) ? 2 : 1;
                }
            }
        }
    }

    //Check for winner
    private boolean hatGewonnen(int spieler) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (spielfeld[i][0] == spieler && spielfeld[i][1] == spieler && spielfeld[i][2] == spieler) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (spielfeld[0][i] == spieler && spielfeld[1][i] == spieler && spielfeld[2][i] == spieler) {
                return true;
            }
        }

        // Check diagonals
        if ((spielfeld[0][0] == spieler && spielfeld[1][1] == spieler && spielfeld[2][2] == spieler) ||
                (spielfeld[0][2] == spieler && spielfeld[1][1] == spieler && spielfeld[2][0] == spieler)) {
            return true;
        }

        return false;
    }

    //Check if more move are possible
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

    //Set toast for Winner and update score
    private void zeigeGewinner(int spieler) {
        Toast.makeText(getContext(), getString(R.string.player) + " "+ spieler + " " + getString(R.string.win) + "!" , Toast.LENGTH_SHORT).show();

        // Update score
        if (spieler == 1) {
            spieler1Punkte++;
        } else {
            spieler2Punkte++;
        }
        // View score
        aktualisierePunktestandAnzeige();
    }

    //Set score in TextView
    private void aktualisierePunktestandAnzeige() {
        getActivity().runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                spieler1PunkteTextView.setText(getString(R.string.player)+" 1: " + spieler1Punkte);
                spieler2PunkteTextView.setText(getString(R.string.player)+" 2: " + spieler2Punkte);
            }
        });
    }

    //Set Toast draw
    private void zeigeUnentschieden() {
        Toast.makeText(getContext(), getString(R.string.unentschieden), Toast.LENGTH_SHORT).show();
    }

    // reset everything
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

        spieler1Punkte = 0;
        spieler2Punkte = 0;
        aktualisierePunktestandAnzeige();
    }
}