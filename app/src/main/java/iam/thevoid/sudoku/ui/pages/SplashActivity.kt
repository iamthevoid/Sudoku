package iam.thevoid.sudoku.ui.pages

import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.SplashActivityBinding
import iam.thevoid.sudoku.ui.view.SplashView
import iam.thevoid.sudoku.ui.viewmodel.SplashViewModel
import iam.thevoid.sudoku.util.extractGames
import iam.thevoid.sudoku.util.startActivityCloseStack

/**
 * Created by iam on 11/09/2017.
 */
class SplashActivity : ViewModelActivity<SplashViewModel, SplashActivityBinding, SplashView>(), SplashView {

    override fun requestExtractGames() = extractGames(this)

    override fun goToMenu() = startActivityCloseStack(this, MenuActivity::class)

    override fun layoutId(): Int = R.layout.activity_splash

    override fun variableId(): Int = BR.vm

    override fun viewModel(): SplashViewModel = SplashViewModel()
}