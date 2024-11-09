package com.nfricke.coursecrafter_selfmade.DAO;

import java.util.ArrayList;

//List Object for modules with extra functionality
public class ModulManager extends ArrayList<Modul> {

    //Get occupied modules
    public String[][] getByTagBlock(int tag, int block) {
        int size = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int n = 0; n < this.get(i).getAnzahlVeranstaltungen(); n++) {
                if (this.get(i).getTag(n) == tag && this.get(i).getBlock(n) == block && this.get(i).isBelegt() == true) {
                    size = size + 1;
                }
            }
        }

        String[][] result = new String[size][2];
        int c = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int n = 0; n < this.get(i).getAnzahlVeranstaltungen(); n++) {
                if (this.get(i).getTag(n) == tag && this.get(i).getBlock(n) == block && this.get(i).isBelegt() == true) {
                    result[c][0] = this.get(i).getModulName();
                    result[c][1] = this.get(i).getRaum(n);
                    c = c + 1;
                }
            }
        }
        return result;
    }

    //Get average grade
    public double getDurchschnitt(){
        ModulManager ergebnis = new ModulManager();
        for ( Modul s: this){
            if (s.getNote() != 0.0 && s.getNote() != 5.0){
                ergebnis.add(s);
            }
        }
        double durchschnit = 0;
        for ( Modul s: ergebnis){
            durchschnit = durchschnit + s.getNote();
        }
        durchschnit = durchschnit / ergebnis.size();

        return Math.round(durchschnit * 100.0) / 100.0;
    }

    //Temp test method
    public void printTest() {
        for (Modul s : this) {
            s.printTest();
        }
    }
}
