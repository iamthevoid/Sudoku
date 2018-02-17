package iam.thevoid.sudoku.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.model.Cell

/**
 * Created by alese_000 on 09.02.2018.
 */
class CellGameWrapper(private val cell: Cell) {

    var selected = false

    var sameNumber = false

    var wrong = false

    val insertedNumber: Int by lazy { cell.insertedNumber }

    val isOddBlock: Boolean
        get() {
            val xMod = cell.x / 3
            val yMod = cell.y / 3
            return (xMod % 2 == 0 && yMod % 2 == 0) || xMod == yMod
        }

    fun color(context : Context): Int {
        return when {
            selected -> ContextCompat.getColor(context, R.color.selected_cell)
            sameNumber -> ContextCompat.getColor(context, R.color.same_num_cell)
            isOddBlock -> ContextCompat.getColor(context, R.color.odd_cell)
            else -> ContextCompat.getColor(context, R.color.even_cell)
        }
    }

    fun foreground(context : Context): Drawable? {
        return ContextCompat.getDrawable(context, when {
            cell.x % 3 == 0 && cell.y % 3 == 0 -> R.drawable.cell_foreground_top_left_thick
            cell.x % 3 == 0 && cell.y % 3 == 1 -> R.drawable.cell_foreground_left_thick
            cell.x % 3 == 0 && cell.y % 3 == 2 -> R.drawable.cell_foreground_bottom_left_thick
            cell.x % 3 == 1 && cell.y % 3 == 0 -> R.drawable.cell_foreground_top_thick
            cell.x % 3 == 1 && cell.y % 3 == 1 -> R.drawable.cell_foreground_all_thin
            cell.x % 3 == 1 && cell.y % 3 == 2 -> R.drawable.cell_foreground_bottom_thick
            cell.x % 3 == 2 && cell.y % 3 == 0 -> R.drawable.cell_foreground_top_right_thick
            cell.x % 3 == 2 && cell.y % 3 == 1 -> R.drawable.cell_foreground_right_thick
            cell.x % 3 == 2 && cell.y % 3 == 2 -> R.drawable.cell_foreground_bottom_right_thick
            else -> throw IllegalArgumentException("Cell with x = ${cell.x} and y = ${cell.y} cannot has foreground")
        })
    }

}