package com.nfricke.coursecrafter_selfmade;

public class Modul {
    private int anzahlVeranstaltungen = 3;
    private String modulName;
    private String profName;
    private int[] tag = new int[anzahlVeranstaltungen];
    private int[] block = new int[anzahlVeranstaltungen];
    private String[] raum = new String[anzahlVeranstaltungen];
    private boolean belegt;
    private float note;

    public Modul() {
    }

    public Modul(String n, String p, int[] t, int[] b, String[] r, boolean be, float no) {
        this.modulName = n;
        this.profName = p;
        this.tag = t;
        this.block = b;
        this.raum = r;
        this.belegt = be;
        this.note = no;
    }

    //------------------------------------------
    public String getModulName() { return modulName; }

    public String getProfName() { return profName; }

    public int getTag(int index) { return tag[index]; }
    
    public int getBlock(int index) { return block[index]; }

    public String getRaum(int index) { return raum[index]; }

    public boolean isBelegt() { return belegt; }

    public float getNote() { return note; }

    public String getNoteString() { return String.valueOf(note); }

    //------------------------------------------
    public void setModulName(String modulName) { this.modulName = modulName; }

    public void setProfName(String profName) { this.profName = profName; }

    public void setTag(int tag, int index) { this.tag[index] = tag; }

    public void setBlock(int block, int index) { this.block[index] = block; }

    public void setRaum(String raum, int index) { this.raum[index] = raum; }

    public void setBelegt(boolean belegt) { this.belegt = belegt; }

    public void setNote(float note) { this.note = note; }

    //------------------------------------------
    public static int getAnzahlVeranstaltungen() {
        return new Modul().anzahlVeranstaltungen;
    }
    /*public void printTest() {
        System.out.println(this.getModulName());
        System.out.println(this.getProfName());
        System.out.println(this.getNote());
        System.out.println(this.isBelegt());
        for (int i = 0; i < anzahlVeranstaltungen; i++) {
            System.out.println("Tag" + i + ":   " + this.getTag(i));
            System.out.println("Block" + i + ": " + this.getBlock(i));
            System.out.println("Raum" + i + ":  " + this.getRaum(i));
        }
        System.out.println("-------");
    }*/
}
