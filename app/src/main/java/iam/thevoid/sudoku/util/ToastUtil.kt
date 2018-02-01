package iam.thevoid.sudoku.util

import android.content.Context
import android.widget.Toast
import iam.thevoid.sudoku.App

/**
 * Created by iam on 16/09/2017.
 */
fun showToast(context: Context, message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
