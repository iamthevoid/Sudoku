package iam.thevoid.sudoku.ui.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.databinding.ObservableLong
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.ItemClickSupport
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.ui.view.GameScreenView
import iam.thevoid.sudoku.widget.BoardItemDecoration
import iam.thevoid.sudoku.widget.CellGameWrapper
import io.reactivex.Observable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.concurrent.TimeUnit

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel : ViewModel<GameScreenView>() {

    override fun init() {
        board = DbHandler.getDao(Board::class.java) findFirstAndClose
                { equalTo("isStarted", true) }

        board?.cells?.map { CellGameWrapper(it) }?.let { cells.addAll(it) }

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe { millis.set(millis.get() + 1000) }
    }

    override fun deinit() {

    }

    var board: Board? = null

    var millis: ObservableLong = ObservableLong(0)

    var selected: ObservableInt = ObservableInt(0)

    fun decoration(thick: Float, thin: Float): RecyclerView.ItemDecoration {
        return BoardItemDecoration(thick, thin)
    }

    var cells: ObservableArrayList<CellGameWrapper> = ObservableArrayList()

    val binding: ItemBinding<CellGameWrapper> = ItemBinding.of(BR.item, R.layout.cell)

    val onCellClickListener = object : ItemClickSupport.OnItemClick<CellGameWrapper> {
        override fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int, item: CellGameWrapper) {
            item.insertedNumber = selected.get()
            highlightItems()
        }
    }

    val onNumClickListener : View.OnClickListener = View.OnClickListener { v ->
        if (v !is TextView) return@OnClickListener
        val selected = v.text.toString().toInt()
        this.selected.set(if (selected == this.selected.get()) 0 else selected)
        highlightItems()
    }

    private fun highlightItems() {
        cells.forEach {
            it.sameNumber = false
        }
        cells.filter {
            it.insertedNumber == selected.get() && it.insertedNumber != 0
        }.forEach { it.sameNumber = true }
    }
}
