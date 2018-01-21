package iam.thevoid.sudoku

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * Created by iam on 16/09/2017.
 */

/**
 * VIEW BINDING
 */
@BindingAdapter("click")
fun setOnClickListener(view: View, listener: View.OnClickListener) {
    view.setOnClickListener(listener)
}

@BindingAdapter("visibile", "type", requireAll = false)
fun setOnClickListener(view: View, boolean: Boolean, type: Int) {
    if (!boolean && type != View.VISIBLE) {
        view.visibility = type
    } else {
        view.visibility = if (boolean) View.VISIBLE else View.GONE
    }
}

/**
 * TEXT VIEW BINDING
 */
@BindingAdapter("intText")
fun setIntText(view: TextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("hour_time")
fun setIntText(view: TextView, time: Long) {
    var seclen = time / 1000
    var hour = seclen / 3600
    var min = (seclen - hour * 3600) / 60
    var sec = (seclen - hour * 3600 - min * 60)
    view.text = String.format("%02d:%02d:%02d", hour, min, sec)
}


/**
 * RECYCLER VIEW BINDING
 */
@BindingAdapter("decoration")
fun setIntText(view: RecyclerView, decoration: RecyclerView.ItemDecoration) {
    view.addItemDecoration(decoration)
}