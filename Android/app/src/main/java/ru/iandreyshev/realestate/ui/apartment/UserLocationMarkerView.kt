package ru.iandreyshev.realestate.ui.apartment

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.extension.pixelsF
import ru.iandreyshev.realestate.extension.uiLazy
import kotlin.math.min

class UserLocationMarkerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by uiLazy { Paint() }
    private val mPointerShadowPaint by uiLazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
            .apply { shader = mPointerShadow }
    }
    private lateinit var mPointerShadow: RadialGradient

    private var mCenterX = (width / 2).toFloat()
    private var mCenterY = (height / 2).toFloat()
    private var mRadius = min(width, height).toFloat()

    private val mStrokeWidth by pixelsF(R.dimen.user_location_marker_stroke_width)
    private val mPointerStrokeWidth by pixelsF(R.dimen.user_location_marker_pointer_stroke_width)
    private val mPointerRadius by pixelsF(R.dimen.user_location_marker_pointer_radius)
    private val mPointerShadowOffset by pixelsF(R.dimen.user_location_marker_pointer_shadow_offset)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mCenterX = (width / 2).toFloat()
        mCenterY = (height / 2).toFloat()
        mRadius = min(width, height) / 2f

        mPointerShadow = RadialGradient(
            mCenterX,
            mCenterY + mPointerShadowOffset,
            mPointerRadius + mPointerStrokeWidth,
            Color.BLACK,
            Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.WHITE
        mPaint.alpha = 102
        mPaint.style = Paint.Style.FILL
        canvas?.drawCircle(mCenterX, mCenterY, mRadius, mPaint)

        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeWidth
        canvas?.drawCircle(mCenterX, mCenterY, mRadius - mStrokeWidth / 2, mPaint)

        canvas?.drawCircle(
            mCenterX,
            mCenterY + mPointerShadowOffset,
            mPointerRadius + mPointerStrokeWidth,
            mPointerShadowPaint
        )

        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL
        canvas?.drawCircle(mCenterX, mCenterY, mPointerRadius, mPaint)

        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mPointerStrokeWidth
        canvas?.drawCircle(mCenterX, mCenterY, mPointerRadius + mPointerStrokeWidth / 2, mPaint)
    }

}
