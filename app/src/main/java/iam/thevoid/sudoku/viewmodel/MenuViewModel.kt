package iam.thevoid.sudoku.viewmodel

import android.content.Context
import android.databinding.ObservableBoolean
import android.view.View
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.pages.GameActivity
import iam.thevoid.sudoku.util.getActivity
import iam.thevoid.sudoku.util.showToast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuViewModel : ViewModel() {

    val loading = ObservableBoolean(false)

    val isGameStarted = ObservableBoolean(false)

    val onStartClick = View.OnClickListener { view ->
        val board = DbHandler.getDao(Board::class.java) findFirstAndClose { }

        if (board == null) {
            showToast(view.context, "Error, boards is end")
        } else {
            loading.set(true)
            Observable.create<Boolean> {
                board.start()
                it.onNext(true)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        GameActivity.start(view.context)
                        loading.set(false)
                    })
        }
    }

    val onResumeClick = View.OnClickListener { GameActivity.start(it.context) }

    val onExitClick = View.OnClickListener { getActivity(it.context).finish() }

    val onOptionsClick = View.OnClickListener {}

    val onStatisticsClick = View.OnClickListener {}


    override fun deinit(context: Context) {

    }

    override fun init(context: Context) {
        isGameStarted.set(DbHandler.getDao(Board::class.java).count { equalTo("isStarted", true) } == 1)
    }

}