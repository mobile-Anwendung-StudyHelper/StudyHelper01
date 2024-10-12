package com.nfricke.testapp03

class Modul {
    private val anzahlVeranstaltungen = 3
    private var modulName: String? = null
    private var profName: String? = null
    private var tag = IntArray(anzahlVeranstaltungen)
    private var block = IntArray(anzahlVeranstaltungen)

    constructor()

    constructor(modulName: String?, profName: String?, tag: IntArray, block: IntArray) {
        this.modulName = modulName
        this.profName = profName
        this.tag = tag
        this.block = block
    }

    fun getModulName(): String {
        return modulName.toString()
    }

    fun getProfName(): String {
        return profName.toString()
    }

    fun getTag(index: Int): Int {
        return tag[index]
    }

    fun getBlock(index: Int): Int {
        return block[index]
    }

    fun setModulName(modulName: String?) {
        this.modulName = modulName
    }

    fun setProfName(profName: String?) {
        this.profName = profName
    }

    fun setTag(tag: Int, index: Int) {
        this.tag[index] = tag
    }

    fun setBlock(block: Int, index: Int) {
        this.block[index] = block
    }

    fun getAnzahlVeranstaltungen(): Int {

        return anzahlVeranstaltungen
    }

    fun printTest() {
        println(this.modulName)
        println(this.profName)
        for (i in 0 until anzahlVeranstaltungen) {
            println("Tag" + i + ":   " + this.getTag(i))
            println("Block" + i + ": " + this.getBlock(i))
        }
        println("-------")
    }
}