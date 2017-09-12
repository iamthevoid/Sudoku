package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableArrayList
import android.util.Log
import iam.thevoid.sudoku.App
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.model.Cell
import iam.thevoid.sudoku.util.FileUtil.extractAssets
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by iam on 07/09/2017.
 */
class GameScreenViewModel {
    var cells: ObservableArrayList<Cell> = ObservableArrayList()
    val binding: ItemBinding<Cell> = ItemBinding.of(BR.item, R.layout.cell)

    init {
        extractAssets(App.instance) { percent ->
            Log.i("fsd", "percent: " + percent)
        }
    }
}
