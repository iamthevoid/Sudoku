package iam.thevoid.sudoku.bindings

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.TextView

/**
 * Created by iam on 07/09/2017.
 */
object RecyclerBinding {
    @JvmStatic
    @BindingAdapter("set_has_fixed_size")
    fun setHasFixedSize(view: RecyclerView, fixedSize: Boolean) {
        view.setHasFixedSize(fixedSize)
    }

    @JvmStatic
    @BindingAdapter("nestedScrollEnabled")
    fun setNestedScrollEnabled(view: RecyclerView, nestedScroll: Boolean) {
        view.isNestedScrollingEnabled = nestedScroll
    }
}