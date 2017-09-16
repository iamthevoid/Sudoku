package iam.thevoid.sudoku.pages

import android.os.Bundle
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.SplashActivityBinding
import iam.thevoid.sudoku.viewmodel.SplashViewModel

/**
 * Created by iam on 11/09/2017.
 */
class SplashActivity : ViewModelActivity<SplashViewModel, SplashActivityBinding>() {
    override fun layoutId(): Int {
        return R.layout.splash_activity
    }

    override fun variableId(): Int {
        return BR.vm
    }

    override fun viewModel(): SplashViewModel {
        return SplashViewModel()
    }
}