package iam.thevoid.sudoku.util

import iam.thevoid.sudoku.db.model.Board

/**
 * Created by iam on 08/09/2017.
 */

/**
 * CHAR
 */

fun Char.toIntVal() : Int {
    val res = toInt() - '0'.toInt()
    return if (res < 0 || res > 9) 0 else res
}

/**
 * BOARD
 */

fun Board.isAllCellsFilled() : Boolean {
    cells?.forEach {
        if (it.insertedNumber == 0) {
            return false
        }
    }

    return true
}

