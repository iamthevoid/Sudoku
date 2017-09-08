package iam.thevoid.sudoku.bindings

import android.databinding.BindingAdapter
import android.widget.TextView

/**
 * Created by iam on 07/09/2017.
 */
object TextViewBinding {

    @JvmStatic
    @BindingAdapter("intText")
    fun setIntText(view: TextView, text: Int) {
        view.text = text.toString()
    }
}