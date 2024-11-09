package com.nfricke.coursecrafter_selfmade.DAO;

import java.util.ArrayList;

//List Object for todos with extra functionality
public class TodoManager extends ArrayList<Todo> {

    public int getAnzahl() { return this.size(); }

    public int getAnzahl(boolean erledigt) {
        int count = 0;
        for (Todo todo : this) { if (todo.isErledigt() == erledigt) { count++; } }
        return count;
    }

    public double getAnzahlProzent(boolean erledigt) {
        if (getAnzahl() == 0) { return 0.0; }
        return Math.round((((double) getAnzahl(erledigt) / (double) getAnzahl()) * 100) * 100.0) / 100.0;
    }
}