package iam.thevoid.sudoku.viewmodel

import android.databinding.ObservableInt
import iam.thevoid.sudoku.App
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.model.Board
import iam.thevoid.sudoku.pages.MenuActivity
import iam.thevoid.sudoku.util.FileUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by iam on 16/09/2017.
 */
class SplashViewModel : ViewModel() {

    val percent: ObservableInt = ObservableInt(0);

    override fun init() {
        val count = DbHandler.getDao(Board().javaClass).count { }
        if (count > 0) {
            MenuActivity.start(App.instance)
        } else {
            FileUtil.extractFiles(App.instance)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete({ MenuActivity.start(App.instance) })
                    .subscribe(percent::set)
        }
    }

    override fun deinit() {
    }


}