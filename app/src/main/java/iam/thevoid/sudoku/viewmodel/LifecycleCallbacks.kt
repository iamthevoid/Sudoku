package iam.thevoid.sudoku.viewmodel

import android.content.Context

/**
 * Created by iam on 16/09/2017.
 */
interface LifecycleCallbacks {
    fun onCreate(context: Context)
    fun onStart(context: Context)
    fun onResume(context: Context)
    fun onPause(context: Context)
    fun onStop(context: Context)
    fun onDestroy(context: Context)
}