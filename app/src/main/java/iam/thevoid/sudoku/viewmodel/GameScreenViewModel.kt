package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableLong
import android.support.v7.widget.RecyclerView
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.db.model.Cell
import iam.thevoid.sudoku.widget.BoardItemDecoration
import io.reactivex.Observable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel {

    var board: Board? = null

    var millis : ObservableLong = ObservableLong(0)

    fun decoration(thick: Float, thin : Float) : RecyclerView.ItemDecoration {
        return BoardItemDecoration(thick, thin)
    }

    var cells: ObservableArrayList<Cell> = ObservableArrayList()
    val binding: ItemBinding<Cell> = ItemBinding.of(BR.item, R.layout.cell)

    fun onInit() {
        board = DbHandler.getDao(Board::class.java) findFirstAndClose
                { equalTo("isStarted", true) }
        board?.cells?.let {
            cells.addAll(it)
        }

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe { millis.set(millis.get() + 1000) }
    }
}
