package iam.thevoid.sudoku.ui.pages

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.db.newdb.entity.ActionEntity
import iam.thevoid.sudoku.db.newdb.entity.GameEntity
import iam.thevoid.sudoku.db.newdb.enums.ActionType
import iam.thevoid.sudoku.db.newdb.enums.Difficulty
import iam.thevoid.sudoku.util.createCells
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder
import java.util.*

const val TEST_CELL_ID: Long = 1

class TestActivity : AppCompatActivity() {

    var currentCellId: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        DbHelper.init(this)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val cells = DbHelper.database.cellDao().all().blockingGet()

            val rand = Random()
            val action = ActionEntity(
                    currentCellId,
                    ActionType.values()[rand.nextInt(ActionType.values().size - 1)],
                    rand.nextInt(9) + 1)
            DbHelper.database.actionDao().insert(action)
            currentCellId = if (currentCellId == TEST_CELL_ID) 2 else TEST_CELL_ID
        }

        DbHelper.database.actionDao().selectAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("acts", "All")
                    Log.d("acts", it.joinToString { it.toString() })
                }, Throwable::printStackTrace)
                { print("completed") }


        val game = GameEntity(Difficulty.EASY, createRandom())
        DbHelper.database.gameDao().insert(game)
        DbHelper.database.gameDao().get(1)
                .subscribe({ it.createCells() }, Throwable::printStackTrace)


    }

    private fun createRandom(): String {
        val arr = arrayOfNulls<Int>(81)
        for (i in 1..9) {
            for (j in 1..9) {
                val rand = Random().nextInt(81)
                if (arr[rand] == null) {
                    arr[rand] = j
                } else {
                    for (k in arr.indices) {
                        if (arr[k] == null) {
                            arr[k] = j
                            break
                        }
                    }
                }
            }
        }
        val sb = StringBuilder()

        arr.forEach { sb.append(it) }

        return sb.toString()
    }
}