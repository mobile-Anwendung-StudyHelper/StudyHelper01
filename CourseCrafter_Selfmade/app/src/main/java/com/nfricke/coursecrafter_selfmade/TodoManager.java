package com.nfricke.coursecrafter_selfmade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TodoManager extends ArrayList<Todo> {

    @Override
    public boolean add(Todo todo) {
        boolean result = super.add(todo); // Add the new item
        sortTodos(); // Sort the list by date after addition
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends Todo> c) {
        boolean result = super.addAll(c);
        sortTodos();
        return result;
    }
    private void sortTodos() {
        Collections.sort(this, new Comparator<Todo>() {
            @Override
            public int compare(Todo t1, Todo t2) {
                return t1.getFaelligkeitsdatum().compareTo(t2.getFaelligkeitsdatum());
            }
        });
    }

}
