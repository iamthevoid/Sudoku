package iam.thevoid.sudoku.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent

/**
 * Created by iam on 07/09/2017.
 */
object IntentUtils {


    val NEW_TASK_CLOSE_STACK = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

    @JvmStatic
    fun getActivity(context: Context): Activity {
        if (context is Activity) {
            return context
        } else if (context is ContextWrapper) {
            return getActivity(context.baseContext)
        }
        throw IllegalStateException("Context $context NOT contains activity!")
    }
}