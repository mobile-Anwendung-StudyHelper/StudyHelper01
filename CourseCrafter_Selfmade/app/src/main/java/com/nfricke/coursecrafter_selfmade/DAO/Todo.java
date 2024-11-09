package com.nfricke.coursecrafter_selfmade.DAO;

public class Todo {

    private String name;
    private boolean erledigt;

    //Todo object
    public Todo() {
    }

    //Initialize Todo
    public Todo(String name, boolean erledigt) {
        this.name = name;
        this.erledigt = erledigt;
    }

    //Getter + Setter
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isErledigt() { return erledigt; }

    public void setErledigt(boolean erledigt) { this.erledigt = erledigt; }
}