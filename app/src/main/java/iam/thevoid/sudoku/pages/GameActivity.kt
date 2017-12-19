package iam.thevoid.sudoku.pages

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import iam.thevoid.sudoku.viewmodel.GameScreenViewModel
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.databinding.ActivityMainBinding

class GameActivity : BaseActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.vm = GameScreenViewModel()
    }

    override fun onStart() {
        super.onStart()
        binding?.vm?.onInit()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun generate()

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, GameActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
