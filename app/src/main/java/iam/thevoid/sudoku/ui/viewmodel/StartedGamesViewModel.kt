package iam.thevoid.sudoku.ui.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import iam.thevoid.sudoku.db.newdb.DbHelper
import iam.thevoid.sudoku.db.newdb.models.Game
import iam.thevoid.sudoku.ui.view.StartedGamesView
import iam.thevoid.sudoku.ui.viewmodel.base.ViewModel
import iam.thevoid.sudoku.util.started

class StartedGamesViewModel : ViewModel<StartedGamesView>() {

    private val games : ObservableList<Game> = ObservableArrayList<Game>()

    override fun init() {
        DbHelper.database.gameDao().started().subscribe { games.addAll(it) }
    }

    override fun deinit() {

    }
}