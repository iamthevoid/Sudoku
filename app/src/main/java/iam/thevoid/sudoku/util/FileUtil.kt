package iam.thevoid.sudoku.util

import android.content.Context
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.db.model.Cell
import io.realm.RealmList
import java.util.regex.Pattern


/**
 * Created by iam on 08/09/2017.
 */
object FileUtil {

    @JvmStatic
    fun extractAssets(context: Context, listener: (percent: Long) -> Unit) {

        val inputStream = context.getAssets().open("boards")

        val data = inputStream.bufferedReader().use {
            it.readText()
        }

        val dataArr = data.split(Pattern.compile("\n"))

        for (i in dataArr.indices) {

            val line = dataArr[i]

            val cells: ArrayList<Cell> = ArrayList()

            val sudokuArr = line.split(Pattern.compile(","))
            val sudoku = sudokuArr[1]
            var count = 0
            for (char in sudoku) {
                val cell = Cell()
                cell.y = count / 9
                cell.x = count % 9
                cell.number = char.toInteger()
                cells.add(cell)
                count++

            }
            listener((((i + 1).toFloat() / dataArr.size.toFloat()) * 100F).toLong())
            val board = Board()
            board.cells = RealmList()
            board.cells!!.addAll(cells.toTypedArray())
            DbHandler.create(board)
        }
    }
}