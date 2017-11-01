package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableArrayList
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.db.model.Cell
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel {

    var board: Board? = null

    var cells: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells1: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells2: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells3: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells4: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells5: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells6: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells7: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells8: ObservableArrayList<Cell> = ObservableArrayList()
//    var cells9: ObservableArrayList<Cell> = ObservableArrayList()

    val binding: ItemBinding<Cell> = ItemBinding.of(BR.item, R.layout.cell)

    fun onInit() {
        board = DbHandler.getDao(Board::class.java).findFirstAndClose { equalTo("isStarted", true) }
        board?.cells?.let {
            cells.addAll(it)
//            for (i in it.indices) {
//                if (i / 9 == 0) {
//                    cells1.add(it[i])
//                }
//                if (i / 9 == 1) {
//                    cells2.add(it[i])
//                }
//                if (i / 9 == 2) {
//                    cells3.add(it[i])
//                }
//                if (i / 9 == 3) {
//                    cells4.add(it[i])
//                }
//                if (i / 9 == 4) {
//                    cells5.add(it[i])
//                }
//                if (i / 9 == 5) {
//                    cells6.add(it[i])
//                }
//                if (i / 9 == 6) {
//                    cells7.add(it[i])
//                }
//                if (i / 9 == 7) {
//                    cells8.add(it[i])
//                }
//                if (i / 9 == 8) {
//                    cells9.add(it[i])
//                }
//            }
        }
    }
}
