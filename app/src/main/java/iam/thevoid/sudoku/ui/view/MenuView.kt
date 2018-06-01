package iam.thevoid.sudoku.ui.view

import iam.thevoid.sudoku.ui.view.base.MvvmView

interface MenuView : MvvmView {

    fun goStart(gameId: Long)
    fun goResume()
    fun goOptions()
    fun goExit()
    fun showNoMoreGamesError()
}