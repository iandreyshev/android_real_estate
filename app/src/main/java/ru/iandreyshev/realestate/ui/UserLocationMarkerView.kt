package ru.iandreyshev.realestate.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.extension.uiLazy
import kotlin.math.min

class UserLocationMarkerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by uiLazy { Paint() }
    private val mPath by uiLazy { Path() }

    private var mCenterX = (width / 2).toFloat()
    private var mCenterY = (height / 2).toFloat()
    private var mRadius = min(width, height).toFloat()

    private val mStrokeWidth1 by uiLazy {
        resources.getDimensionPixelSize(R.dimen.user_location_marker_stroke_width_1).toFloat()
    }
    private val mStrokeWidth2 by uiLazy {
        resources.getDimensionPixelSize(R.dimen.user_location_marker_stroke_width_2).toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mCenterX = (width / 2).toFloat()
        mCenterY = (height / 2).toFloat()
        mRadius = min(width, height) / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.WHITE
        mPaint.alpha = 102
        mPaint.style = Paint.Style.FILL
        canvas?.drawCircle(mCenterX, mCenterY, mRadius, mPaint)

        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeWidth1
        canvas?.drawCircle(mCenterX, mCenterY, mRadius - mStrokeWidth1 / 2, mPaint)
    }

}
