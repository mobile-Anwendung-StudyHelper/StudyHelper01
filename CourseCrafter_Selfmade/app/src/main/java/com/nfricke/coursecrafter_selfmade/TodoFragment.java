package com.nfricke.coursecrafter_selfmade;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
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
import java.time.format.DateTimeFormatter;

public class TodoFragment extends Fragment {

    private TodoListAdapter todoListAdapter;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
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

        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new TodolistSwipeToDeleteListener(todoListView, getActivity(), todoListAdapter));
        todoListView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return false;
        });

        return view;
    }

    private void showEditTodoDialog(int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.todo_dialog_add_edit, null);

        final EditText todoNameInput = dialogView.findViewById(R.id.todoNameInput);

        Todo selectedTodo = ((MainActivity)getActivity()).todoManager.get(position);

        todoNameInput.setText(selectedTodo.getName());

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Todo Bearbeiten")
                .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String todoName = todoNameInput.getText().toString();
                        if (todoName.isEmpty()) {
                            Toast.makeText(getActivity(), "Todo darf nicht leer sein", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ((MainActivity)getActivity()).todoManager.set(position, new Todo(todoName,selectedTodo.isErledigt()));
                        ((MainActivity)getActivity()).todoManagerDAO.saveTodoManager(((MainActivity)getActivity()).todoManager);
                        todoListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Todo gespeichert", Toast.LENGTH_SHORT).show();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Hinzufügen")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String todoName = todoNameInput.getText().toString();
                        if (todoName.isEmpty()) {
                            Toast.makeText(getActivity(), "Todo darf nicht leer sein", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ((MainActivity)getActivity()).todoManager.add(new Todo(todoName, false));
                        ((MainActivity)getActivity()).todoManagerDAO.saveTodoManager(((MainActivity)getActivity()).todoManager);
                        todoListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Todo gespeichert", Toast.LENGTH_SHORT).show();
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