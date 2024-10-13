package com.nfricke.coursecrafter_selfmade;

import java.util.ArrayList;

public class ModulManager extends ArrayList<Modul> {

    public int getIndexByName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getModulName() == name) {
                return i;
            }
        }
        return -1;
    }
    public String[][] getByTagBlock(int tag, int block) {
        int size = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int n = 0; n < this.get(i).getAnzahlVeranstaltungen(); n++) {
                if (this.get(i).getTag(n) == tag && this.get(i).getBlock(n) == block) {
                    size = size + 1;
                }
            }
        }

        String[][] result = new String[size][2];
        int c = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int n = 0; n < this.get(i).getAnzahlVeranstaltungen(); n++) {
                if (this.get(i).getTag(n) == tag && this.get(i).getBlock(n) == block) {
                    result[c][0] = this.get(i).getModulName();
                    result[c][1] = this.get(i).getRaum(n);
                    c = c + 1;
                }
            }
        }
        return result;
    }

    public boolean nameIstVorhanden(String name){
        for ( Modul s: this){
            if (s.getModulName().equals(name))
                return true;
        }
        return false;
    }

    public void printTest() {
        for (Modul s : this) {
            s.printTest();
        }
    }
}
