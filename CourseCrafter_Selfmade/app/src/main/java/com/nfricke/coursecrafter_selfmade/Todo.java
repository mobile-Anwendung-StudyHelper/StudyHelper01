package com.nfricke.coursecrafter_selfmade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Todo {

    private String name;
    private LocalDateTime faelligkeitsdatum;
    private boolean erledigt;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Todo() {
    }

    public Todo(String name, LocalDateTime faelligkeitsdatum, boolean erledigt) {
        this.name = name;
        this.faelligkeitsdatum = faelligkeitsdatum;
        this.erledigt = erledigt;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDateTime getFaelligkeitsdatum() { return faelligkeitsdatum; }

    public void setFaelligkeitsdatum(LocalDateTime faelligkeitsdatum) { this.faelligkeitsdatum = faelligkeitsdatum; }

    public boolean isErledigt() { return erledigt; }

    public void setErledigt(boolean erledigt) { this.erledigt = erledigt; }

    public String getFaelligkeitsdatumString() {
        return faelligkeitsdatum.format(DATE_FORMATTER);
    }

    public void setFaelligkeitsdatumString(String dateStr) {
        try {
            this.faelligkeitsdatum = LocalDateTime.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd.MM.yyyy");
        }
    }
}