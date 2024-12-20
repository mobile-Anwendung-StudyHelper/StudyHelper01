package com.nfricke.coursecrafter_selfmade.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nfricke.coursecrafter_selfmade.DAO.Modul;
import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.Adapter.ModulListAdapter;
import com.nfricke.coursecrafter_selfmade.Listener_Handler.ModullistSwipeToDeleteListener;
import com.nfricke.coursecrafter_selfmade.R;

public class ModullistFragment extends Fragment {

    private View view;
    private ModulListAdapter modulListAdapter;
    private ListView modulListView;
    private FloatingActionButton addModuleButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modullist, container, false);

        // Use 'view' to find views inside the fragment
        modulListView = view.findViewById(R.id.listViewModul);
        // Create the adapter using the fragment's context or activity context
        modulListAdapter = new ModulListAdapter(getActivity());
        // Set the adapter
        modulListView.setAdapter(modulListAdapter);

        // Initialize FloatingActionButton and set up the listener
        addModuleButton = view.findViewById(R.id.addModule);
        addModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddModuleDialog();
            }
        });

        //Set other lisstener
        modulListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showEditModuleDialog(position);
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new ModullistSwipeToDeleteListener(modulListView, getActivity(), modulListAdapter));
        modulListView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return false;
        });

        return view;
    }

    private void showAddModuleDialog() {
        // Inflate the dialog view with your custom form
        LayoutInflater inflaterAddDialog = LayoutInflater.from(getActivity());
        View dialogView = inflaterAddDialog.inflate(R.layout.dialog_add_edit_modullist, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        // Setup the inputs from dialog view
        final EditText modulNameInput = dialogView.findViewById(R.id.modulNameInput);
        final EditText profNameInput = dialogView.findViewById(R.id.profNameInput);
        final Spinner tagSpinner1 = dialogView.findViewById(R.id.tagSpinnerInput1);
        final Spinner blockSpinner1 = dialogView.findViewById(R.id.blockSpinnerInput1);
        final EditText raumInput1 = dialogView.findViewById(R.id.raumInput1);
        final Spinner tagSpinner2 = dialogView.findViewById(R.id.tagSpinnerInput2);
        final Spinner blockSpinner2 = dialogView.findViewById(R.id.blockSpinnerInput2);
        final EditText raumInput2 = dialogView.findViewById(R.id.raumInput2);
        final Spinner tagSpinner3 = dialogView.findViewById(R.id.tagSpinnerInput3);
        final Spinner blockSpinner3 = dialogView.findViewById(R.id.blockSpinnerInput3);
        final EditText raumInput3 = dialogView.findViewById(R.id.raumInput3);
        final TextView v_one = dialogView.findViewById(R.id.Veranstaltung_one);
        final TextView v_two = dialogView.findViewById(R.id.Veranstaltung_two);
        final TextView v_three = dialogView.findViewById(R.id.Veranstaltung_three);

        final TextView day_one = dialogView.findViewById(R.id.day_one);
        final TextView day_two = dialogView.findViewById(R.id.day_two);
        final TextView day_three = dialogView.findViewById(R.id.day_three);

        final TextView block_one = dialogView.findViewById(R.id.block_one);
        final TextView block_two = dialogView.findViewById(R.id.block_two);
        final TextView block_three = dialogView.findViewById(R.id.block_three);

        final TextView room_one = dialogView.findViewById(R.id.room_one);
        final TextView room_two = dialogView.findViewById(R.id.room_two);
        final TextView room_three = dialogView.findViewById(R.id.room_three);


        ArrayAdapter<String> tagAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ((MainActivity)getActivity()).wochentage);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner1.setAdapter(tagAdapter);
        tagSpinner2.setAdapter(tagAdapter);
        tagSpinner3.setAdapter(tagAdapter);
        ArrayAdapter<String> blockAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ((MainActivity)getActivity()).bloecke);
        blockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockSpinner1.setAdapter(blockAdapter);
        blockSpinner2.setAdapter(blockAdapter);
        blockSpinner3.setAdapter(blockAdapter);

        v_one.setText(getString(R.string.veranstaltung) + " 1:");
        v_two.setText(getString(R.string.veranstaltung) + " 2:");
        v_three.setText(getString(R.string.veranstaltung) + " 3:");
        day_one.setText(getString(R.string.day));
        day_two.setText(getString(R.string.day));
        day_three.setText(getString(R.string.day));
        block_one.setText(getString(R.string.block));
        block_two.setText(getString(R.string.block));
        block_three.setText(getString(R.string.block));
        room_one.setText(getString(R.string.room));
        room_two.setText(getString(R.string.room));
        room_three.setText(getString(R.string.room));

        //create dialog and buttons with listeners
        builder.setTitle(getString(R.string.add_title))
                .setPositiveButton(getString(R.string.add_Button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String modulName = modulNameInput.getText().toString();
                        String profName = profNameInput.getText().toString();
                        int[] tag = new int[] { tagSpinner1.getSelectedItemPosition(), tagSpinner2.getSelectedItemPosition(), tagSpinner3.getSelectedItemPosition() };
                        int[] block = new int[] { blockSpinner1.getSelectedItemPosition(), blockSpinner2.getSelectedItemPosition(), blockSpinner3.getSelectedItemPosition() };
                        String[] raum = new String[] { raumInput1.getText().toString(), raumInput2.getText().toString(), raumInput3.getText().toString()};

                        if (modulName.isEmpty()) {
                            Toast.makeText(getActivity(), getString(R.string.modulname_error), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Add new Modul instance
                        ((MainActivity)getActivity()).modulManager.add(new Modul(modulName, profName, tag, block, raum, false, 0));
                        ((MainActivity)getActivity()).modulManagerDAO.saveModulManager(((MainActivity)getActivity()).modulManager);
                        modulListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), getString(R.string.add_suggsess), Toast.LENGTH_SHORT).show();
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


    @SuppressLint("SetTextI18n")
    private void showEditModuleDialog(int position) {
        // Inflate the dialog view with your custom form
        LayoutInflater inflaterAddDialog = LayoutInflater.from(getActivity());
        View dialogView = inflaterAddDialog.inflate(R.layout.dialog_add_edit_modullist, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        // Setup the inputs from dialog view
        final EditText modulNameInput = dialogView.findViewById(R.id.modulNameInput);
        final EditText profNameInput = dialogView.findViewById(R.id.profNameInput);
        final Spinner tagSpinner1 = dialogView.findViewById(R.id.tagSpinnerInput1);
        final Spinner blockSpinner1 = dialogView.findViewById(R.id.blockSpinnerInput1);
        final EditText raumInput1 = dialogView.findViewById(R.id.raumInput1);
        final Spinner tagSpinner2 = dialogView.findViewById(R.id.tagSpinnerInput2);
        final Spinner blockSpinner2 = dialogView.findViewById(R.id.blockSpinnerInput2);
        final EditText raumInput2 = dialogView.findViewById(R.id.raumInput2);
        final Spinner tagSpinner3 = dialogView.findViewById(R.id.tagSpinnerInput3);
        final Spinner blockSpinner3 = dialogView.findViewById(R.id.blockSpinnerInput3);
        final EditText raumInput3 = dialogView.findViewById(R.id.raumInput3);
        final TextView v_one = dialogView.findViewById(R.id.Veranstaltung_one);
        final TextView v_two = dialogView.findViewById(R.id.Veranstaltung_two);
        final TextView v_three = dialogView.findViewById(R.id.Veranstaltung_three);
        final TextView day_one = dialogView.findViewById(R.id.day_one);
        final TextView day_two = dialogView.findViewById(R.id.day_two);
        final TextView day_three = dialogView.findViewById(R.id.day_three);
        final TextView block_one = dialogView.findViewById(R.id.block_one);
        final TextView block_two = dialogView.findViewById(R.id.block_two);
        final TextView block_three = dialogView.findViewById(R.id.block_three);
        final TextView room_one = dialogView.findViewById(R.id.room_one);
        final TextView room_two = dialogView.findViewById(R.id.room_two);
        final TextView room_three = dialogView.findViewById(R.id.room_three);

        ArrayAdapter<String> tagAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ((MainActivity)getActivity()).wochentage);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner1.setAdapter(tagAdapter);
        tagSpinner2.setAdapter(tagAdapter);
        tagSpinner3.setAdapter(tagAdapter);
        ArrayAdapter<String> blockAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ((MainActivity)getActivity()).bloecke);
        blockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockSpinner1.setAdapter(blockAdapter);
        blockSpinner2.setAdapter(blockAdapter);
        blockSpinner3.setAdapter(blockAdapter);

        Modul editModul = ((MainActivity)getActivity()).modulManager.get(position);
        modulNameInput.setText(editModul.getModulName());
        profNameInput.setText(editModul.getProfName());
        tagSpinner1.setSelection(editModul.getTag(0));

        blockSpinner1.setSelection(editModul.getBlock(0));
        raumInput1.setText(editModul.getRaum(0));
        tagSpinner2.setSelection(editModul.getTag(1));
        blockSpinner2.setSelection(editModul.getBlock(1));
        raumInput2.setText(editModul.getRaum(1));
        tagSpinner3.setSelection(editModul.getTag(2));
        blockSpinner3.setSelection(editModul.getBlock(2));
        raumInput3.setText(editModul.getRaum(2));
        v_one.setText(getString(R.string.veranstaltung) + " 1:");
        v_two.setText(getString(R.string.veranstaltung) + " 2:");
        v_three.setText(getString(R.string.veranstaltung) + " 3:");
        day_one.setText(getString(R.string.day));
        day_two.setText(getString(R.string.day));
        day_three.setText(getString(R.string.day));
        block_one.setText(getString(R.string.block));
        block_two.setText(getString(R.string.block));
        block_three.setText(getString(R.string.block));
        room_one.setText(getString(R.string.room));
        room_two.setText(getString(R.string.room));
        room_three.setText(getString(R.string.room));

        //create dialog and buttons with listeners
        builder.setTitle(getString(R.string.edit_Title))
                .setPositiveButton(getString(R.string.save_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String modulName = modulNameInput.getText().toString();
                        String profName = profNameInput.getText().toString();
                        int[] tag = new int[] { tagSpinner1.getSelectedItemPosition(), tagSpinner2.getSelectedItemPosition(), tagSpinner3.getSelectedItemPosition() };
                        int[] block = new int[] { blockSpinner1.getSelectedItemPosition(), blockSpinner2.getSelectedItemPosition(), blockSpinner3.getSelectedItemPosition() };
                        String[] raum = new String[] { raumInput1.getText().toString(), raumInput2.getText().toString(), raumInput3.getText().toString()};

                        if (modulName.isEmpty()) {
                            Toast.makeText(getActivity(), getString(R.string.modulname_error), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // replace new Modul instance
                        ((MainActivity)getActivity()).modulManager.set(position, new Modul(modulName, profName, tag, block, raum, editModul.isBelegt(), editModul.getNote()));
                        ((MainActivity)getActivity()).modulManagerDAO.saveModulManager(((MainActivity)getActivity()).modulManager);
                        modulListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), getString(R.string.save_modul), Toast.LENGTH_SHORT).show();

                    }
                })
                .setNeutralButton(getString(R.string.abbrechen_Button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getActivity(), getString(R.string.abgebrochen), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.del_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove the item from the data source
                        ((MainActivity) getActivity()).modulManager.remove(position);
                        // Persist changes if necessary
                        ((MainActivity) getActivity()).modulManagerDAO.saveModulManager(((MainActivity) getActivity()).modulManager);
                        // Notify the adapter of deletion
                        modulListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), getString(R.string.abgebrochen), Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }

}