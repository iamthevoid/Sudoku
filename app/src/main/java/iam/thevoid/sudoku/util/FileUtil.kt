package iam.thevoid.sudoku.util

import android.content.Context
import iam.thevoid.sudoku.db.newdb.DbHelper
import io.reactivex.Observable
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
        val line = dataArr[i]
        if (line.length != 164) continue

        val gameDao = DbHelper.database.gameDao()
        gameDao.insert(gameDao.create(line.substring(82, line.length - 1)))

        listener((((i + 1).toFloat() / dataArr.size.toFloat()) * 100F).toInt())
    }
}

fun extractGames(context: Context): Observable<Int> {
    return Observable.create {
        extractAssets(context) { percent -> it.onNext(percent) }
        it.onComplete()
    }
}