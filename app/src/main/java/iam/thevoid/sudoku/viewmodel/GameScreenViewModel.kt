package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableLong
import android.support.v7.widget.RecyclerView
import android.view.View
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.ItemClickSupport
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.util.showToast
import iam.thevoid.sudoku.widget.BoardItemDecoration
import iam.thevoid.sudoku.widget.CellGameWrapper
import io.reactivex.Observable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.concurrent.TimeUnit

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel {

    var board: Board? = null

    var millis: ObservableLong = ObservableLong(0)

    fun decoration(thick: Float, thin: Float): RecyclerView.ItemDecoration {
        return BoardItemDecoration(thick, thin)
    }

    var cells: ObservableArrayList<CellGameWrapper> = ObservableArrayList()

    val binding: ItemBinding<CellGameWrapper> = ItemBinding.of(BR.item, R.layout.cell)

    val onCellClickListener = object : ItemClickSupport.OnItemClick<CellGameWrapper> {
        override fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int, item: CellGameWrapper) {
            item.selected = true
            cells[position] = item
        }
    }

    fun onInit() {
        board = DbHandler.getDao(Board::class.java) findFirstAndClose
                { equalTo("isStarted", true) }

        board?.cells?.map { CellGameWrapper(it) }?.let { cells.addAll(it) }

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe { millis.set(millis.get() + 1000) }
    }
}
