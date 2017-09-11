package iam.thevoid.sudoku.util

import android.content.Context
import android.util.Log
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.db.model.Cell
import io.realm.RealmList
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern


/**
 * Created by iam on 08/09/2017.
 */
object FileUtil {

    @JvmStatic
    fun getBoard(context: Context) {
        val cells: ArrayList<Cell> = ArrayList()
        val inputStream = context.getAssets().open("boards")

        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        while (line != null) {
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
            break
//            line = reader.readLine()
        }
        val board = Board()
        board.cells = RealmList()
        board.cells!!.addAll(cells.toTypedArray())

        DbHandler.create(board)
        Log.i("tag", "999")
    }


}