package com.hzy.shimmerview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.IntRange
import com.hzy.shimmerview.util.ShimmerUtil

/**
 * Display loading status view
 * Created by ziye_huang on 2018/12/5.
 */
class ShimmerView(context: Context) : View(context) {
    @ColorInt
    private var mColor = 0
    @Dimension
    private var mRadius = 0
    @IntRange(from = 0, to = 255)
    private var mAlpha = 0
    private lateinit var mDrawRectF: RectF
    private var mPaint = Paint()
    private lateinit var mShimmerUtil: ShimmerUtil

    constructor(shimmerUtil: ShimmerUtil) : this(shimmerUtil.getContext()) {
        mShimmerUtil = shimmerUtil
        mPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.setMeasuredDimension(mDrawRectF.width().toInt(), mDrawRectF.height().toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = mColor
        mPaint.alpha = mAlpha
        canvas?.drawRoundRect(mDrawRectF, mRadius.toFloat(), mRadius.toFloat(), mPaint)
    }

    fun getShimmerUtil(): ShimmerUtil {
        return mShimmerUtil
    }

    fun color(@ColorInt color: Int): ShimmerView {
        mColor = color
        return this
    }

    fun radius(@Dimension radius: Int): ShimmerView {
        mRadius = radius
        return this
    }

    fun alpha(@IntRange(from = 0, to = 255) alpha: Int): ShimmerView {
        mAlpha = alpha
        return this
    }

    fun drawRect(drawRect: RectF): ShimmerView {
        mDrawRectF = drawRect
        return this
    }

}