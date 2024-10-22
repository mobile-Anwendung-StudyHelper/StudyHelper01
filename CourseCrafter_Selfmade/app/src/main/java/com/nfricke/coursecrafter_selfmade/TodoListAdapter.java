package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TodoListAdapter extends ArrayAdapter<Todo> {

    public TodoListAdapter(@NonNull Context context, @NonNull TodoManager todoManager) {
        super(context, 0, todoManager);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        Todo todo = getItem(position);

        TextView todoName = view.findViewById(R.id.todoName);
        TextView todoDate = view.findViewById(R.id.todoDate);
        CheckBox todoDoneCheckbox = view.findViewById(R.id.todoDoneCheckbox);

        todoName.setText(todo.getName());
        todoDate.setText(todo.getFaelligkeitsdatumString());
        todoDoneCheckbox.setChecked(todo.isErledigt());

        todoDoneCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setErledigt(isChecked);
            // potentially save changes or refresh UI
        });

        return view;
    }
}