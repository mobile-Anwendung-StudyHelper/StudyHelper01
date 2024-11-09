package com.nfricke.coursecrafter_selfmade.Fragment;

import android.annotation.SuppressLint;
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

import com.nfricke.coursecrafter_selfmade.DAO.Modul;
import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.Adapter.NotenListAdapter;
import com.nfricke.coursecrafter_selfmade.R;

public class NotenFragment extends Fragment {

    private NotenListAdapter notenListAdapter;
    private TextView durchschnittTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noten, container, false);

        //Initialize fragment with listview and average grade
        TextView notenTitel = view.findViewById(R.id.notesTitle);
        ListView notesListView = view.findViewById(R.id.notesListView);
        notenListAdapter = new NotenListAdapter(getContext());
        notesListView.setAdapter(notenListAdapter);
        notenTitel.setText(getString(R.string.notenTitel));
        durchschnittTextView = view.findViewById(R.id.noten_durchschnitt);
        double note;
        note = ((MainActivity) getActivity()).modulManager.getDurchschnitt();
        if (note > 0) durchschnittTextView.setText(getString(R.string.durchschnitt)+": " + String.valueOf(note));
        else durchschnittTextView.setText(getString(R.string.durchschnitt)+": " + "-");

        //Set listener for edit grades
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Modul selectedModule = (Modul) adapterView.getItemAtPosition(position);
                showEditGradeDialog(selectedModule);
            }
        });

        return view;
    }

    private void showEditGradeDialog(Modul selectedModule) {
        // Inflate the dialog view with your custom form
        LayoutInflater inflaterAddDialog = LayoutInflater.from(getActivity());
        View dialogView = inflaterAddDialog.inflate(R.layout.dialog_edit_noten, null);
        TextView gradeText = dialogView.findViewById(R.id.gradeText);
        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        gradeText.setText(getString(R.string.grade));

        final Spinner notenSpinner = dialogView.findViewById(R.id.noten_spinner);

        //Set up grade adapter with all posible grades
        final float [] noten = new float[] {0.0F, 1.0F, 1.3F, 1.7F, 2.0F, 2.3F, 2.7F, 3.0F, 3.3F, 3.7F, 4.0F, 5.0F};
        String[] notenStrings = new String[noten.length];
        notenStrings[0] = "-";
        for (int i = 1; i < noten.length; i++) { notenStrings[i] = String.valueOf(noten[i]); }
        ArrayAdapter<String> notenSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, notenStrings);
        notenSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notenSpinner.setAdapter(notenSpinnerAdapter);

        //Set position in spinner
        float currentNote = selectedModule.getNote();
        int currentNotePosition = 0;
        for (int i = 0; i < noten.length; i++) {
            if (noten[i] == currentNote) {
                currentNotePosition = i;
                break;
            }
        }
        notenSpinner.setSelection(currentNotePosition);

        //Create dialog for edit with buttons
        builder.setTitle(getString(R.string.grade_for) + " " + selectedModule.getModulName() + " " + getString(R.string.auswaehlen))
                .setPositiveButton(getString(R.string.add_Button), new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int notePosition = notenSpinner.getSelectedItemPosition();
                        selectedModule.setNote(noten[notePosition]);
                        ((MainActivity) getActivity()).modulManagerDAO.saveModulManager(((MainActivity) getActivity()).modulManager);
                        notenListAdapter.notifyDataSetInvalidated();
                        durchschnittTextView.setText(getString(R.string.durchschnitt)+": " + ((MainActivity) getActivity()).modulManager.getDurchschnitt());

                        Toast.makeText(getActivity(), getString(R.string.edit_grade), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.abbrechen_Button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getActivity(), getString(R.string.abgebrochen), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.create().show();
    }

}