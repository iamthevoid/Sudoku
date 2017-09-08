package iam.thevoid.sudoku.bindings

import android.databinding.BindingAdapter
import android.graphics.Point
import android.view.View
import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.util.IntentUtils


/**
 * Created by iam on 07/09/2017.
 */
object CellBinding {

    @JvmStatic
    @BindingAdapter("cell_height")
    fun setHeight(view: View, enable: Boolean) {
        if (enable) {
            val display = IntentUtils.getActivity(view.context).getWindowManager().getDefaultDisplay()
            val size = Point()
            display.getSize(size)
            val lp = view.layoutParams
            lp.height = size.y / view.context.resources.getInteger(R.integer.board_size)
            view.layoutParams = lp
        }
    }

}