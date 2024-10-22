package com.nfricke.coursecrafter_selfmade;

import static android.provider.Settings.System.DATE_FORMAT;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TodoFragment extends Fragment {

    private TodoListAdapter todoListAdapter;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        ListView todoListView = view.findViewById(R.id.todoListView);
        FloatingActionButton addTodoButton = view.findViewById(R.id.addTodoButton);

        todoListAdapter = new TodoListAdapter(getContext(), ((MainActivity)getActivity()).todoManager);
        todoListView.setAdapter(todoListAdapter);

        addTodoButton.setOnClickListener(v -> showAddTodoDialog());
        
        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                showEditTodoDialog(position);
                return true;
            }
        });

        return view;
    }

    private void showEditTodoDialog(int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.todo_dialog_add_edit, null);

        final EditText todoNameInput = dialogView.findViewById(R.id.todoNameInput);
        final EditText todoDateInput = dialogView.findViewById(R.id.todoDateInput);

        Todo selectedTodo = ((MainActivity)getActivity()).todoManager.get(position);

        todoNameInput.setText(selectedTodo.getName());
        todoDateInput.setText(selectedTodo.getFaelligkeitsdatumString());

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Todo Bearbeiten")
                .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String todoName = todoNameInput.getText().toString();
                        String dateStr = todoDateInput.getText().toString();
                        if (todoName.isEmpty() || dateStr.isEmpty()) {
                            Toast.makeText(getActivity(), "Todo darf nicht leer sein", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            LocalDate dueDate = LocalDate.parse(dateStr, DATE_FORMAT);
                            selectedTodo.setName(todoName);
                            selectedTodo.setFaelligkeitsdatum(dueDate.atStartOfDay());
                            todoListAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Todo updated", Toast.LENGTH_SHORT).show();
                        } catch (DateTimeParseException e) {
                            Toast.makeText(getActivity(), "Invalid date format. Please use dd.MM.yyyy", Toast.LENGTH_SHORT).show();
                        }
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

    private void showAddTodoDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.todo_dialog_add_edit, null);

        final EditText todoNameInput = dialogView.findViewById(R.id.todoNameInput);
        final EditText todoDateInput = dialogView.findViewById(R.id.todoDateInput);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Hinzufügen")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String todoName = todoNameInput.getText().toString();
                        String dateStr = todoDateInput.getText().toString();
                        if (todoName.isEmpty()) {
                            Toast.makeText(getActivity(), "Todo name cannot be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            LocalDate dueDate = LocalDate.parse(dateStr, DATE_FORMAT);
                            ((MainActivity)getActivity()).todoManager.add(new Todo(todoName, dueDate.atStartOfDay(), false));
                            todoListAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Todo gespeichert", Toast.LENGTH_SHORT).show();
                        } catch (DateTimeParseException e) {
                            Toast.makeText(getActivity(), "Invalid date format. Please use dd.MM.yyyy", Toast.LENGTH_SHORT).show();
                        }
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