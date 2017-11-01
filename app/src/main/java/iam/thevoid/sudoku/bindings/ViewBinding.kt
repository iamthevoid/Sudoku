package iam.thevoid.sudoku.bindings

import android.databinding.BindingAdapter
import android.support.annotation.IntDef
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
    @BindingAdapter("visibile", "type", requireAll = false)
    fun setOnClickListener(view : View, boolean: Boolean, type : Int) {
        if (!boolean && type != View.VISIBLE) {
            view.visibility = type
        } else{
            view.visibility = if (boolean) View.VISIBLE else View.GONE
        }
    }
}