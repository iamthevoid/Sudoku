package iam.thevoid.sudoku.ui.pages

import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.ActivityStartedGamesBinding
import iam.thevoid.sudoku.ui.view.StartedGamesView
import iam.thevoid.sudoku.ui.viewmodel.StartedGamesViewModel

class StartedGamesActivity : ViewModelActivity<StartedGamesViewModel, ActivityStartedGamesBinding, StartedGamesView>(), StartedGamesView {
    override fun layoutId(): Int = R.layout.activity_started_games

    override fun variableId(): Int = BR.vm

    override fun viewModel(): StartedGamesViewModel = StartedGamesViewModel()
}