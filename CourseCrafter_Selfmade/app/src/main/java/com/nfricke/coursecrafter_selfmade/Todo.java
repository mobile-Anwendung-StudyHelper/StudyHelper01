package com.nfricke.coursecrafter_selfmade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Todo {

    private String name;
    private boolean erledigt;


    public Todo() {
    }

    public Todo(String name, boolean erledigt) {
        this.name = name;
        this.erledigt = erledigt;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isErledigt() { return erledigt; }

    public void setErledigt(boolean erledigt) { this.erledigt = erledigt; }
}