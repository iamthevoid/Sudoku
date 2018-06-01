package iam.thevoid.sudoku.ui.viewmodel.base

import iam.thevoid.sudoku.ui.view.base.MvvmView

/**
 * Created by iam on 16/09/2017.
 */
abstract class ViewModel<T : MvvmView> : LifecycleCallbacks {

    protected lateinit var mvvmView: T

    fun attachView(mvvmView : T) {
        this.mvvmView = mvvmView
    }

    abstract fun init()

    abstract fun deinit()

    override fun onStart() = init()

    override fun onStop() = deinit()

    override fun onCreate() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}
}