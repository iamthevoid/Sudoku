package iam.thevoid.sudoku.viewmodel

import android.content.Context
import android.databinding.ObservableInt
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.pages.MenuActivity
import iam.thevoid.sudoku.util.extractFiles
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by iam on 16/09/2017.
 */
class SplashViewModel : ViewModel() {

    val percent: ObservableInt = ObservableInt(0);

    override fun init(context: Context) {
        val count = DbHandler.getDao(Board().javaClass).count()
        when {
            count > 0 -> MenuActivity.start(context)
            else -> extractFiles(context)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete({ MenuActivity.start(context) })
                    .subscribe(percent::set)
        }
    }

    override fun deinit(context: Context) {
    }


}