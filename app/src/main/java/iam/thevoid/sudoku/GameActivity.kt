package iam.thevoid.sudoku

import android.databinding.DataBindingUtil
import android.os.Bundle
import iam.thevoid.sudoku.databinding.ActivityMainBinding
import iam.thevoid.sudoku.util.FileUtil

class GameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = GameScreenViewModel()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun generate()

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
