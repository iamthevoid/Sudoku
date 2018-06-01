package iam.thevoid.sudoku.ui.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ObservableLong
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.ItemClickSupport
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.db.newdb.models.Game
import iam.thevoid.sudoku.ui.view.GameScreenView
import iam.thevoid.sudoku.ui.viewmodel.base.ViewModel
import iam.thevoid.sudoku.ui.viewmodel.descriptors.CellGameWrapper
import iam.thevoid.sudoku.util.dispose
import iam.thevoid.sudoku.widget.BoardItemDecoration
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.concurrent.TimeUnit

/**
 * Created by iam on 07/09/2017.
 */
class GameViewModel(val gameId: Long) : ViewModel<GameScreenView>() {

//    var board: Board? = null

    lateinit var gameDisp: Disposable
    lateinit var cellDisp: Disposable

    val game: ObservableField<Game> = ObservableField()

    val cells: ObservableArrayList<CellGameWrapper> = ObservableArrayList()

    var millis: ObservableLong = ObservableLong(0)

    var selected: ObservableInt = ObservableInt(0)

    val binding: ItemBinding<CellGameWrapper> = ItemBinding.of(BR.item, R.layout.cell)

    override fun init() {
        gameDisp = DbHelper.database.gameDao().get(gameId)
                .subscribe(
                        { value: Game? ->
                            game.set(value)
                            updateCells()
                        },
                        { it.printStackTrace() }
                )

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe { millis.set(millis.get() + 1000) }
    }

    private fun updateCells() {
        dispose(cellDisp)
        cellDisp = DbHelper.database.cellDao()
                .getFor(gameId)
                .map { it.map { CellGameWrapper(it) } }
                .subscribe(
                        { cells.addAll(it) },
                        { it.printStackTrace() }
                )
    }

    override fun deinit() {

    }

    fun decoration(thick: Float, thin: Float): RecyclerView.ItemDecoration {
        return BoardItemDecoration(thick, thin)
    }

    // Выбирается цифра (или опция) из панели под полем. Если цифра - подсвечиваем все цифры с этим
    // значением на поле. Если выбран режим вставки кандидатов - подсвечиваем так же все кандидаты.
    // По клику на поле вставляем туда выбранную цифру, если это допустимо (если она не предустановлена)
    // TODO remake - select digit
    val onNumClickListener: View.OnClickListener = View.OnClickListener { v ->
        if (v !is TextView) return@OnClickListener
        val selected = v.text.toString().toInt()
        this.selected.set(if (selected == this.selected.get()) 0 else selected)
        highlightItems()
    }

    // TODO implement - select option
    val onPencilClickListener: View.OnClickListener = View.OnClickListener {

    }

    // TODO implement - pause click
    val onPauseClickListener: View.OnClickListener = View.OnClickListener {

    }

    // Создаём действие опираясь на выбранный режим
    // Если вставка номера - идём по вилке - а) Выбран тот же номер, что и в ячейке - REMOVE_NUMBER, нет - ADD_NUMBER
    // Если вставка кандидата - идем по вилке - а) Выбран кандидат, который есть в ячейке - REMOVE_CANDIDATE, нет - ADD_CANDIDATE
    // Если удаление - CLEAR_CELL
    // TODO remake - click to cell in board
    val onCellClickListener = object : ItemClickSupport.OnItemClick<CellGameWrapper> {
        override fun onItemClicked(recyclerView: RecyclerView, itemView: View, position: Int, item: CellGameWrapper) {
            item.insertedNumber = selected.get()
            highlightItems()
        }
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
