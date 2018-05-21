package iam.thevoid.sudoku.ui.pages

import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.ActivityMainBinding
import iam.thevoid.sudoku.ui.view.GameScreenView
import iam.thevoid.sudoku.ui.viewmodel.GameScreenViewModel

class GameActivity : ViewModelActivity<GameScreenViewModel, ActivityMainBinding, GameScreenView>(), GameScreenView {
    override fun variableId(): Int = BR.vm

    override fun viewModel(): GameScreenViewModel = GameScreenViewModel()

    override fun layoutId(): Int = R.layout.activity_main
}
