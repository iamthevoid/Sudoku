package iam.thevoid.sudoku.viewmodel

import android.content.Context
import android.databinding.ObservableBoolean
import android.view.View
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.pages.GameActivity
import iam.thevoid.sudoku.util.ToastUtil
import iam.thevoid.sudoku.util.getActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by iam on 16/09/2017.
 */
class MenuViewModel : ViewModel() {

    val loading = ObservableBoolean(false)

    val isGameStarted = ObservableBoolean(false)

    val onStartClick = View.OnClickListener { view ->
        val board = DbHandler.getDao(Board::class.java).findFirstAndClose({ })
        if (board == null) {
            ToastUtil.show(view.context, "Error, boards is end")
        } else {
            loading.set(true)
            Observable.create<Boolean> { e ->
                run {
                    board.start()
                    e.onNext(true)
                }
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        GameActivity.start(view.context)
                        loading.set(false)
                    })
        }
    }

    val onResumeClick = View.OnClickListener { view ->
        GameActivity.start(view.context)
    }

    val onExitClick = View.OnClickListener { view ->
        getActivity(view.context).finish()
    }

    val onOptionsClick = View.OnClickListener { view ->
    }

    val onStatisticsClick = View.OnClickListener { view ->
    }


    override fun deinit(context: Context) {

    }

    override fun init(context: Context) {
        isGameStarted.set(DbHandler.getDao(Board::class.java).count { equalTo("isStarted", true) } == 1)
    }

}