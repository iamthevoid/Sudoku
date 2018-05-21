package iam.thevoid.sudoku.ui.view

interface MenuView : MvvmView {

    fun goStart(gameId: Long)
    fun goResume()
    fun goOptions()
    fun goExit()
    fun showNoMoreGamesError()
}