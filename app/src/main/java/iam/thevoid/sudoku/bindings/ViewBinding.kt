package iam.thevoid.sudoku.bindings

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by iam on 16/09/2017.
 */
object ViewBinding {

    @JvmStatic
    @BindingAdapter("click")
    fun setOnClickListener(view : View, listener : View.OnClickListener) {
        view.setOnClickListener(listener)
    }
    @JvmStatic
    @BindingAdapter("visibile")
    fun setOnClickListener(view : View, boolean: Boolean) {
        view.visibility = if (boolean) View.VISIBLE else View.GONE
    }
}