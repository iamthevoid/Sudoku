package iam.thevoid.sudoku.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent

/**
 * Created by iam on 07/09/2017.
 */

object Intent {
    val NEW_TASK_CLOSE_STACK = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
}

fun getActivity(context: Context): Activity {
    return when (context) {
        is Activity -> context
        is ContextWrapper -> getActivity(context)
        else -> throw IllegalStateException("Context $context NOT contains activity!")
    }
}