package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableBoolean
import android.view.View
import android.widget.Toast
import iam.thevoid.sudoku.App
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.pages.GameActivity
import iam.thevoid.sudoku.util.IntentUtils
import iam.thevoid.sudoku.util.ToastUtil

/**
 * Created by iam on 16/09/2017.
 */
class MenuViewModel : ViewModel() {

    val isGameStarted = ObservableBoolean(false)

    val onStartClick = View.OnClickListener { view ->
        val board = DbHandler.getDao(Board::class.java).findFirstAndClose({  })
        if (board == null) {
            ToastUtil.show("Error, boards is end")
        } else{
            board.isStarted = true
            GameActivity.start(view.context)
        }
    }

    val onResumeClick = View.OnClickListener { view ->
        GameActivity.start(view.context)
    }

    val onExitClick = View.OnClickListener { view ->
        IntentUtils.getActivity(view.context).finish()
    }

    val onOptionsClick = View.OnClickListener { view ->
    }

    val onStatisticsClick = View.OnClickListener { view ->
    }


    override fun deinit() {

    }

    override fun init() {
        isGameStarted.set(DbHandler.getDao(Board::class.java).count { equalTo("isStarted", true) } == 1)
    }

}