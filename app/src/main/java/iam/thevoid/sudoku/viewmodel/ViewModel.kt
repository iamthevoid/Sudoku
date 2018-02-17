package iam.thevoid.sudoku.viewmodel

import android.content.Context

/**
 * Created by iam on 16/09/2017.
 */
abstract class ViewModel : LifecycleCallbacks {

    abstract fun init(context: Context)
    abstract fun deinit(context: Context)

    override fun onStart(context: Context) = init(context)

    override fun onStop(context: Context) = deinit(context)

    override fun onCreate(context: Context) {
    }

    override fun onResume(context: Context) {
    }

    override fun onPause(context: Context) {
    }


    override fun onDestroy(context: Context) {
    }

}