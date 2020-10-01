package ru.iandreyshev.realestate.ui.apartment

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ru.iandreyshev.realestate.R
import ru.iandreyshev.realestate.extension.color
import ru.iandreyshev.realestate.extension.pixels
import ru.iandreyshev.realestate.extension.pixelsF

class CostTileShadowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mHeight by pixels(R.dimen.cost_tile_shadow_height)
    private val mHeightF by pixelsF(R.dimen.cost_tile_shadow_height)
    private val mVerticalOffset by pixels(R.dimen.cost_tile_shadow_horizontal_offset)

    private val mCornerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mSidePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mColor by color(R.color.gray_3_10)

    private lateinit var mLeftCornerGradient: RadialGradient
    private lateinit var mRightCornerGradient: RadialGradient
    private lateinit var mSideGradient: LinearGradient

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mLeftCornerGradient = RadialGradient(
            mHeightF,
            mHeightF,
            mHeightF,
            intArrayOf(mColor, mColor, Color.TRANSPARENT),
            floatArrayOf(0f, mVerticalOffset.toFloat() / mHeight, 1f),
            Shader.TileMode.CLAMP
        )

        mRightCornerGradient = RadialGradient(
            width - mHeightF,
            mHeightF,
            mHeightF,
            intArrayOf(mColor, mColor, Color.TRANSPARENT),
            floatArrayOf(0f, mVerticalOffset.toFloat() / mHeight, 1f),
            Shader.TileMode.CLAMP
        )

        mSideGradient = LinearGradient(
            mHeightF,
            mHeightF,
            mHeightF,
            0f,
            intArrayOf(mColor, mColor, Color.TRANSPARENT),
            floatArrayOf(0f, mVerticalOffset.toFloat() / mHeight, 1f),
            Shader.TileMode.CLAMP
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width + mVerticalOffset * 2, mHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCornerPaint.shader = mLeftCornerGradient

        canvas?.drawArc(
            0f,
            0f,
            mHeightF * 2,
            mHeightF * 2,
            180f,
            90f,
            true,
            mCornerPaint
        )

        mCornerPaint.shader = mRightCornerGradient

        canvas?.drawArc(
            width - mHeightF * 2,
            0f,
            width.toFloat(),
            mHeightF * 2,
            270f,
            90f,
            true,
            mCornerPaint
        )

        mSidePaint.shader = mSideGradient

        canvas?.drawRect(
            mHeightF,
            0f,
            width.toFloat() - mHeight,
            mHeightF,
            mSidePaint
        )
    }

}