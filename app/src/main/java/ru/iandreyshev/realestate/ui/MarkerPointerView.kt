package ru.iandreyshev.realestate.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import ru.iandreyshev.realestate.extension.uiLazy

class MarkerPointerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var isActive = false

    private val mPaint by uiLazy { Paint() }
    private val mPath by uiLazy { Path() }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = when (isActive) {
            true -> Color.BLACK
            else -> Color.WHITE
        }
        mPaint.style = Paint.Style.FILL

        mPath.reset()
        mPath.moveTo(0f, 0f)
        mPath.lineTo(width.toFloat(), 0f)
        mPath.lineTo(width.toFloat() / 2, height.toFloat())
        mPath.lineTo(0f, 0f)
        mPath.close()

        canvas?.drawPath(mPath, mPaint)
    }

}