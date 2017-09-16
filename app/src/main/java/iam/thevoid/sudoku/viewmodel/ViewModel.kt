package iam.thevoid.sudoku.viewmodel

/**
 * Created by iam on 16/09/2017.
 */
open abstract class ViewModel : LifecycleCallbacks {

    abstract fun init()
    abstract fun deinit()

    override fun onCreate() {
    }

    override fun onStart() {
        init()
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
        deinit()
    }

    override fun onDestroy() {
    }

}