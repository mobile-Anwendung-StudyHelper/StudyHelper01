package com.nfricke.coursecrafter_selfmade;

public class Modul {
    private int anzahlVeranstaltungen = 3;
    public String[] wochentage;
    public String[] bloecke;
    private String modulName;
    private String profName;
    private int[] tag = new int[anzahlVeranstaltungen];
    private int[] block = new int[anzahlVeranstaltungen];
    private String[] raum = new String[anzahlVeranstaltungen];

    public Modul() {
    }

    public Modul(String[] sW, String [] sB, String n, String p, int[] t, int[] b, String[] r) {
        this.wochentage = sW;
        this.bloecke = sB;
        this.modulName = n;
        this.profName = p;
        this.tag = t;
        this.block = b;
        this.raum = r;
    }

    //------------------------------------------
    public String getModulName() { return modulName; }

    public String getProfName() { return profName; }

    public int getTag(int index) { return tag[index]; }

    public String getTagString(int index) { return wochentage[tag[index]]; }

    public int getBlock(int index) { return block[index]; }

    public String getBlockString(int index) { return bloecke[block[index]]; }

    public String getBlockIntString(int index) { return block[index] + " -> " + bloecke[block[index]]; }

    public String getRaum(int index) { return raum[index]; }

    //------------------------------------------
    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public void setTag(int tag, int index) {
        this.tag[index] = tag;
    }

    public void setBlock(int block, int index) {
        this.block[index] = block;
    }

    public void setRaum(String raum, int index) {
        this.raum[index] = raum;
    }

    //------------------------------------------
    public static int getAnzahlVeranstaltungen() {
        return new Modul().anzahlVeranstaltungen;
    }
    public void printTest() {
        System.out.println(this.getModulName());
        System.out.println(this.getProfName());
        for (int i = 0; i < anzahlVeranstaltungen; i++) {
            System.out.println("Tag" + i + ":   " + this.getTag(i));
            System.out.println("Block" + i + ": " + this.getBlock(i));
            System.out.println("Raum" + i + ":  " + this.getRaum(i));
        }
        System.out.println("-------");
    }
}
