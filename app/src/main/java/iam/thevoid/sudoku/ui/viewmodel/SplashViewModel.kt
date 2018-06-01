package iam.thevoid.sudoku.ui.viewmodel

import android.databinding.ObservableInt
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.ui.view.SplashView
import iam.thevoid.sudoku.ui.viewmodel.base.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by iam on 16/09/2017.
 */
class SplashViewModel : ViewModel<SplashView>() {

    val percent: ObservableInt = ObservableInt(0);

    override fun init() {
        DbHelper.database.gameDao().count().subscribe { count: Int -> checkGamesCount(count) }
    }

    override fun deinit() {
    }

    private fun checkGamesCount(gamesCount: Int) {
        when {
            gamesCount > 0 -> mvvmView.goToMenu()
            else -> mvvmView.requestExtractGames()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete { mvvmView.goToMenu() }
                    .subscribe(percent::set)
        }
    }
}