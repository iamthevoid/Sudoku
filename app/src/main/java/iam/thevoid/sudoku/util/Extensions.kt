package iam.thevoid.sudoku.util

/**
 * Created by iam on 08/09/2017.
 */

fun Char.toInteger() : Int {
    val res = toInt() - '0'.toInt()
    return if (res < 0 || res > 9) 0 else res
}