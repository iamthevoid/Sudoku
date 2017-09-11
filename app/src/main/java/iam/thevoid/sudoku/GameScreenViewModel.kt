package iam.thevoid.sudoku

import android.databinding.ObservableArrayList
import iam.thevoid.sudoku.db.model.Cell
import iam.thevoid.sudoku.util.FileUtil
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel {
    var cells: ObservableArrayList<Cell> = ObservableArrayList()
    val binding: ItemBinding<Cell> = ItemBinding.of(BR.item, R.layout.cell)

    init {
        FileUtil.getBoard(App.instance)
//        cells.addAll(!!)
    }
}
