package iam.thevoid.sudoku.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by iam on 12.11.17.
 */
class BoardItemDecoration(thick: Float, thin: Float) : RecyclerView.ItemDecoration() {

    val thickPaint: Paint
    val thinPaint: Paint

    val thickLine : Float;
    val thinLine : Float;

    init {
        this.thickLine = thick
        this.thinLine = thin
        thickPaint = Paint()
        thickPaint.color = Color.BLACK
        thickPaint.strokeWidth = thick
        thinPaint = Paint()
        thinPaint.color = Color.BLACK
        thinPaint.strokeWidth = thin
    }


    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        for (i in 0..9) {
            val vertX = canvas.width * (1f / 9f) * i.toFloat()
            val horY = canvas.width * (1f / 9f) * i.toFloat()
            val h = canvas.height
            val w = canvas.width

            var dim = 0f
            if (h != 0 && (h < w || w == 0)) {
                dim = h.toFloat()
            } else if (w != 0 && (w < h || h == 0)) {
                dim = w.toFloat()
            }



            val offset = thickLine / 2

            if (i != 0 && i != 9) {
                if (i % 3 == 0) {
                    canvas.drawLine(vertX, 0f, vertX, dim, thickPaint);
                    canvas.drawLine(0f, horY, dim, horY, thickPaint);
                } else {
                    canvas.drawLine(vertX, 0f, vertX, dim, thinPaint);
                    canvas.drawLine(0f, horY, dim, horY, thinPaint)
                }
            } else if (i == 0) {
                canvas.drawLine(vertX + offset, 0f, vertX + offset, dim, thickPaint);
                canvas.drawLine(0f, horY + offset, dim, horY + offset, thickPaint);
            } else if (i == 9) {
                canvas.drawLine(vertX - offset, 0f, vertX - offset, dim, thickPaint);
                canvas.drawLine(0f, horY - offset, dim, horY - offset, thickPaint);
            }
        }

//       canvas.drawLine(0f, 1f, 0f, canvas.height.toFloat(), paint)
        super.onDraw(canvas, parent, state)
    }
}