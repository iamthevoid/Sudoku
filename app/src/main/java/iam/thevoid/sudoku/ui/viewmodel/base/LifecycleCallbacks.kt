package iam.thevoid.sudoku.ui.viewmodel.base

/**
 * Created by iam on 16/09/2017.
 */
interface LifecycleCallbacks {
    fun onCreate()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
}