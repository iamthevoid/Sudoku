package iam.thevoid.sudoku.util

import android.content.Context
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.db.model.Cell
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.realm.RealmList
import java.util.regex.Pattern


/**
 * Created by iam on 08/09/2017.
 */
object FileUtil {

    @JvmStatic
    fun extractAssets(context: Context, listener: (percent: Int) -> Unit) {

        val inputStream = context.getAssets().open("boards")

        val data = inputStream.bufferedReader().use {
            it.readText()
        }

        inputStream.close()

        val dataArr = data.split(Pattern.compile("\n"))

        for (i in dataArr.indices) {
            val b = Board()
            val line = dataArr[i]
            b.cellsData = line

            listener((((i + 1).toFloat() / dataArr.size.toFloat()) * 100F).toInt())
        }
    }

    @JvmStatic
    fun extractFiles(context: Context) : Observable<Int> {
        return Observable.create { e: ObservableEmitter<Int> ->
            run {
                extractAssets(context, { percent -> e.onNext(percent) })
                e.onComplete()
            }
        }
    }
}