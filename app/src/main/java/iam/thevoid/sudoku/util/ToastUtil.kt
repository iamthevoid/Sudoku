package iam.thevoid.sudoku.util

import android.widget.Toast
import iam.thevoid.sudoku.App

/**
 * Created by iam on 16/09/2017.
 */
object ToastUtil {
    fun show(message: Int) {
        Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
    }

    fun show(message: String) {
        Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
    }
}