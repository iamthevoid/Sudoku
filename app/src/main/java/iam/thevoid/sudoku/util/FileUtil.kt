package iam.thevoid.sudoku.util

import android.content.Context
import android.util.Log
import iam.thevoid.sudoku.Cell
import java.io.ByteArrayOutputStream


/**
 * Created by iam on 08/09/2017.
 */
object FileUtil {

    @JvmStatic
    fun getBoard(context: Context): Array<Cell>? {
        val cells: ArrayList<Cell> = ArrayList()
        val inputStream = context.getAssets().open("boards")

        val output = ByteArrayOutputStream()
        output.write(inputStream.readBytes())
        val file = output.toByteArray()
        var count = 0
        for (byte in file) {
            Log.i("t", "" + byte)
            if (byte.toChar() == ',') {
                continue
            } else if (byte.toChar() == '\n') {
                break
            } else {
                cells.add(Cell(byte.toChar().toString().toInt(), count % 9,  count / 9))
                count++
            }
        }

        return cells.toTypedArray()
    }


}