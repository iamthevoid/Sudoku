package iam.thevoid.sudoku.ui.view

import io.reactivex.Observable

interface SplashView : MvvmView {
    fun goToMenu()
    fun requestExtractGames() : Observable<Int>
}