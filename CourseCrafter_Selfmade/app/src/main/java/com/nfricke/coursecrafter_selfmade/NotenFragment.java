package com.nfricke.coursecrafter_selfmade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class NotenFragment extends Fragment {

    private NotenListAdapter notenListAdapter;
    private TextView durchschnittTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noten, container, false);

        ListView notesListView = view.findViewById(R.id.notesListView);
        notenListAdapter = new NotenListAdapter(getContext());
        notesListView.setAdapter(notenListAdapter);

        durchschnittTextView = view.findViewById(R.id.noten_durchschnitt);
        durchschnittTextView. setText((((MainActivity) getActivity()).modulManager.durchschnitt() > 0 ? "Durchschnitt: " + ((MainActivity) getActivity()).modulManager.durchschnitt() : "Keine Noten"));

        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Modul selectedModule = (Modul) adapterView.getItemAtPosition(position);
                showEditGradeDialog(selectedModule);
                return true;
            }
        });

        return view;
    }

    private void showEditGradeDialog(Modul selectedModule) {
        // Inflate the dialog view with your custom form
        LayoutInflater inflaterAddDialog = LayoutInflater.from(getActivity());
        View dialogView = inflaterAddDialog.inflate(R.layout.noten_dialog_edit, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        final Spinner notenSpinner = dialogView.findViewById(R.id.noten_spinner);

        final float [] noten = new float[] {0.0F, 1.0F, 1.3F, 1.7F, 2.0F, 2.3F, 2.7F, 3.0F, 3.3F, 3.7F, 4.0F, 5.0F};
        String[] notenStrings = new String[noten.length];
        notenStrings[0] = "-";
        for (int i = 1; i < noten.length; i++) { notenStrings[i] = String.valueOf(noten[i]); }

        ArrayAdapter<String> notenSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, notenStrings);
        notenSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notenSpinner.setAdapter(notenSpinnerAdapter);

        float currentNote = selectedModule.getNote();
        int currentNotePosition = 0;
        for (int i = 0; i < noten.length; i++) {
            if (noten[i] == currentNote) {
                currentNotePosition = i;
                break;
            }
        }
        notenSpinner.setSelection(currentNotePosition);


        builder.setTitle("Note für " + selectedModule.getModulName() + " auswählen")
                .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int notePosition = notenSpinner.getSelectedItemPosition();
                        selectedModule.setNote(noten[notePosition]);
                        ((MainActivity) getActivity()).modulManagerDAO.saveModulManager(((MainActivity) getActivity()).modulManager);
                        notenListAdapter.notifyDataSetInvalidated();
                        durchschnittTextView. setText("Durchschnitt: " + ((MainActivity) getActivity()).modulManager.durchschnitt());
                        Toast.makeText(getActivity(), "Note angepasst", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getActivity(), "Abgebrochen", Toast.LENGTH_SHORT).show();
                    }
                });

        builder.create().show();
    }

}