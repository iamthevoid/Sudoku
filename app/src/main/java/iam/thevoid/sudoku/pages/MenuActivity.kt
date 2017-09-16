package iam.thevoid.sudoku.pages

import android.content.Context
import android.content.Intent
import iam.thevoid.sudoku.BR
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.MenuActivityBinding
import iam.thevoid.sudoku.util.IntentUtils
import iam.thevoid.sudoku.viewmodel.MenuViewModel

/**
 * Created by iam on 12/09/2017.
 */
class MenuActivity : ViewModelActivity<MenuViewModel, MenuActivityBinding>() {
    override fun layoutId(): Int {
        return R.layout.menu_activity
    }

    override fun variableId(): Int {
        return BR.vm
    }

    override fun viewModel(): MenuViewModel {
        return MenuViewModel()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MenuActivity::class.java)
                    .addFlags(IntentUtils.NEW_TASK_CLOSE_STACK))
        }
    }



}