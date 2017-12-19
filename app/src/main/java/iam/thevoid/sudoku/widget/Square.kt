package iam.thevoid.sudoku.widget

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import iam.thevoid.sudoku.R

/**
 * Created by iam on 06.11.17.
 */
class SquareFrameLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val width = MeasureSpec.getSize(widthSpec)
        val height = MeasureSpec.getSize(heightSpec)

        var ws = widthSpec
        var hs = heightSpec

        if (height != 0 && (height < width || width == 0)) {
            ws = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        } else if (width != 0 && (width < height || height == 0)) {
            hs = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        }
        super.onMeasure(ws, hs)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}

// Small hack to fix overscroll
// TODO need check on other resolutions
class BoardRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {


    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val width = MeasureSpec.getSize(widthSpec)
        val height = MeasureSpec.getSize(heightSpec)

        var ws = widthSpec
        var hs = heightSpec

        val offset = Math.ceil(context.resources.getDimension(R.dimen.recycler_hack).toDouble()).toInt()
        if (height != 0 && (height < width || width == 0)) {
            ws = MeasureSpec.makeMeasureSpec(height + offset, MeasureSpec.EXACTLY)
        } else if (width != 0 && (width < height || height == 0)) {
            hs = MeasureSpec.makeMeasureSpec(width + offset, MeasureSpec.EXACTLY)
        }
        super.onMeasure(ws, hs)
    }
}