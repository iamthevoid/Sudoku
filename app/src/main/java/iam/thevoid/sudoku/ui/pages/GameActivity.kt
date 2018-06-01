package iam.thevoid.sudoku.ui.pages

import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.ActivityMainBinding
import iam.thevoid.sudoku.ui.view.GameScreenView
import iam.thevoid.sudoku.ui.viewmodel.GameViewModel
import iam.thevoid.sudoku.util.EXTRA_GAME_ID

class GameActivity : ViewModelActivity<GameViewModel, ActivityMainBinding, GameScreenView>(), GameScreenView {
    override fun variableId(): Int = BR.vm

    override fun viewModel(): GameViewModel = GameViewModel(intent.getLongExtra(EXTRA_GAME_ID, 0))

    override fun layoutId(): Int = R.layout.activity_main
}
