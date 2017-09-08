package iam.thevoid.sudoku

/**
 * Created by iam on 07/09/2017.
 */
class Cell(val number: Int, val x: Int, val y: Int) {

    fun isOddBlock(): Boolean {
        val xMod = x / 3
        val yMod = y / 3
        return (xMod % 2 == 0 && yMod % 2 == 0) || xMod == yMod
    }
}
