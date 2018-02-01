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

fun extractAssets(context: Context, listener: (percent: Int) -> Unit) {

    val inputStream = context.assets.open("boards")

    val data = inputStream.bufferedReader().use { it.readText() }

    inputStream.close()

    val dataArr = data.split(Pattern.compile("\n"))

    for (i in dataArr.indices) {

        val b = Board()
        val line = dataArr[i]
        if (line.length != 164) continue

        b.cellsData = line

        DbHandler.create(b)

        listener((((i + 1).toFloat() / dataArr.size.toFloat()) * 100F).toInt())
    }
}

fun extractFiles(context: Context): Observable<Int> {
    return Observable.create {
        extractAssets(context) { percent -> it.onNext(percent) }
        it.onComplete()
    }
}