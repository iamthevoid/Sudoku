package iam.thevoid.sudoku.ui.viewmodel

import android.databinding.ObservableBoolean
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.ui.view.MenuView
import iam.thevoid.sudoku.ui.viewmodel.base.ViewModel
import iam.thevoid.sudoku.util.*
import io.reactivex.functions.Action

class MenuViewModel : ViewModel<MenuView>() {

    val loading = ObservableBoolean(false)

    val isGameStarted = ObservableBoolean(false)

    val onStartClick = Action {
        DbHelper.database.gameDao().notStarted()
                .doOnSubscribe { loading.set(true) }
                .doOnEvent { _, _ -> loading.set(false) }
                .doOnError { mvvmView.showNoMoreGamesError() }
                .subscribe { game -> mvvmView.goStart(game.id) }
    }

    val onResumeClick = Action { mvvmView.goResume() }

    val onExitClick = Action { mvvmView.goExit() }

    val onOptionsClick = Action {}

    val onStatisticsClick = Action {}


    override fun deinit() {

    }

    override fun init() {
        DbHelper.database.gameDao()
                .countStarted()
                .subscribe { startedGamesCount : Int -> isGameStarted.set(startedGamesCount > 0) }
    }
}