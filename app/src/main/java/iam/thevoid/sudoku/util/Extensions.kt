package iam.thevoid.sudoku.util

import android.text.Selection.selectAll
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.db.newdb.dao.ActionDao
import iam.thevoid.sudoku.db.newdb.entity.CellEntity
import iam.thevoid.sudoku.db.newdb.models.Action
import iam.thevoid.sudoku.db.newdb.models.Game
import io.reactivex.Flowable
import java.util.*

/**
 * Created by iam on 08/09/2017.
 */

/**
 * CHAR
 */

fun Char.toIntVal(): Int {
    val res = toInt() - '0'.toInt()
    return if (res < 0 || res > 9) 0 else res
}

/**
 * GAME
 */

fun Game.createCells() {
    val cells = ArrayList<CellEntity>()
    for (num in contents.toCharArray().indices) {
        cells.add(CellEntity(
                contents[num].toIntVal(),
                id,
                num % 9,
                num / 9
        ))
    }
    DbHelper.database.cellDao().insert(cells)
}