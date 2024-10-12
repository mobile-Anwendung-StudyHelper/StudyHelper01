package com.nfricke.testapp03


class ModulManager : ArrayList<Modul>() {

    fun getByTagBlock(tag: Int, block: Int): Array<String> {
        val result = emptyArray<String>()
        var c = 0
        for (i in this.indices) {
            for (n in 0 until this[i].getAnzahlVeranstaltungen()) {
                if (this[i].getTag(n) == tag && (this[i].getBlock(n) == block)) {
                    result[c] = this[i].getModulName()
                    c++
                }
            }
        }
        return result
    }

    fun nameIstVorhanden(name: String?): Boolean {
        for (s in this) {
            if (s.getModulName() == name) return true
        }
        return false
    }

    fun getIndexByName(name: String): Int {
        for (i in this.indices) {
            if (this[i].getModulName() === name) {
                return i
            }
        }
        return -1
    }


    fun printTest() {
        for (s in this) {
            s.printTest()
        }
    }
}