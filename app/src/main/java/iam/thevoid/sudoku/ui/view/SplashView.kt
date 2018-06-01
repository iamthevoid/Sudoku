package iam.thevoid.sudoku.ui.view

import iam.thevoid.sudoku.ui.view.base.MvvmView
import io.reactivex.Observable

interface SplashView : MvvmView {
    fun goToMenu()
    fun requestExtractGames() : Observable<Int>
}