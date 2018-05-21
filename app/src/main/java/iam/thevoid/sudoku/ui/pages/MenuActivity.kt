package iam.thevoid.sudoku.ui.pages

import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.MenuActivityBinding
import iam.thevoid.sudoku.ui.view.MenuView
import iam.thevoid.sudoku.ui.viewmodel.MenuViewModel
import iam.thevoid.sudoku.util.showToast
import iam.thevoid.sudoku.util.startActivityNewTask
import iam.thevoid.sudoku.util.startGameActivity

/**
 * Created by iam on 12/09/2017.
 */
class MenuActivity : ViewModelActivity<MenuViewModel, MenuActivityBinding, MenuView>(), MenuView {

    override fun showNoMoreGamesError() = showToast(this, "Error, boards is end")

    override fun goStart(gameId: Long) = startGameActivity(this, gameId)

    override fun goResume() = startActivityNewTask(this, StartedGamesActivity::class)

    override fun goOptions() {}

    override fun goExit() = finish()

    override fun layoutId(): Int = R.layout.activity_menu

    override fun variableId(): Int = BR.vm

    override fun viewModel(): MenuViewModel = MenuViewModel()

}