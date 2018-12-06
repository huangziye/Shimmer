package com.hzy.shimmerview.util

import android.content.Context
import android.graphics.Color
import android.graphics.RectF
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.IntRange
import com.hzy.shimmerview.R
import com.hzy.shimmerview.filter.DefaultOnShimmerFilter
import com.hzy.shimmerview.filter.FilterType
import com.hzy.shimmerview.filter.OnShimmerFilter
import com.hzy.shimmerview.view.ShimmerView

/**
 * Display loading view util
 * Created by ziye_huang on 2018/12/5.
 */
class ShimmerUtil {

    private var mWrapperView: View
    @IntRange(from = 0, to = 255)
    private var mAlpha = 255
    @Dimension
    private var mRadius = 0
    @ColorInt
    private var mColor = Color.parseColor("#E9E9E9")
    private var mDrawRect: RectF
    private var mShimmerView: ShimmerView? = null
    private val DEFAULT_SHIMMER_VIEW_FILTER = DefaultOnShimmerFilter()
    private var mOnShimmerFilter: OnShimmerFilter = DEFAULT_SHIMMER_VIEW_FILTER

    constructor(view: View, drawRect: RectF) {
        mWrapperView = view
        mDrawRect = drawRect
        mRadius = dp2px(view.context, 4f)
    }

    companion object {
        fun with(view: View): ShimmerUtil {
            if (view is ShimmerView) {
                return view.getShimmerUtil()
            }
            var width = view.width
            var height = view.height
            if (0 == width) {
                var spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                view.measure(spec, spec)
                width = view.measuredWidth
                height = view.measuredHeight
            }
            var drawRect = RectF(
                0f,
                0f,
                (width - view.paddingLeft - view.paddingRight).toFloat(),
                (height - view.paddingTop - view.paddingBottom).toFloat()
            )
            var shimmerUtil = view.getTag(R.id.shimmer_view) as? ShimmerUtil
            if (null == shimmerUtil) {
                shimmerUtil = ShimmerUtil(view, drawRect)
                view.setTag(R.id.shimmer_view, shimmerUtil)
            }
            return shimmerUtil
        }
    }

    fun color(@ColorInt color: Int): ShimmerUtil {
        mColor = color
        return this
    }

    fun radius(@Dimension radius: Int): ShimmerUtil {
        mRadius = radius
        return this
    }

    fun alpha(@IntRange(from = 0, to = 255) alpha: Int): ShimmerUtil {
        mAlpha = alpha
        return this
    }

    fun drawRect(drawRect: RectF): ShimmerUtil {
        mDrawRect = drawRect
        return this
    }

    fun drawRect(width: Int, height: Int): ShimmerUtil {
        mDrawRect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        return this
    }

    fun filter(filter: OnShimmerFilter): ShimmerUtil {
        mOnShimmerFilter = filter
        return this
    }

    fun getContext(): Context {
        return mWrapperView.context
    }

    fun render(): View {
        if (null == mShimmerView) {
            var parent = mWrapperView.parent as ViewGroup
            mShimmerView = ShimmerView(this)
            mShimmerView!!.id = mWrapperView.id
            mShimmerView!!.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect)
            var params = mWrapperView.layoutParams as ViewGroup.LayoutParams
            var index = parent.indexOfChild(mWrapperView)
            parent.removeView(mWrapperView)
            parent.addView(mShimmerView, index, params)
        } else {
            mShimmerView!!.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect)
        }
        return mShimmerView!!
    }

    fun render(view: View) {
        var filterType = mOnShimmerFilter.onFilter(view)
        if (filterType == FilterType.IGNORED) {
            return
        } else if (filterType == FilterType.CHILDS) {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    render(view.getChildAt(i))
                }
            }
        } else if (filterType == FilterType.LOADING) {
            ShimmerUtil.with(view).color(mColor).radius(mRadius).alpha(mAlpha).render()
        }
    }

    fun renderChilds() {
        render(mWrapperView)
    }

    fun remove() {
        if (null == mShimmerView) return
        var parent = mShimmerView!!.parent as ViewGroup
        var params = mShimmerView!!.layoutParams as ViewGroup.LayoutParams
        var index = parent.indexOfChild(mShimmerView)
        parent.removeView(mShimmerView)
        parent.addView(mWrapperView, index, params)
        mShimmerView = null
    }

    fun remove(view: View) {
        var filterType = mOnShimmerFilter.onFilter(view)
        if (filterType == FilterType.IGNORED) {
            return
        } else if (filterType == FilterType.CHILDS) {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    remove(view.getChildAt(i))
                }
            }
        } else if (filterType == FilterType.LOADING) {
            ShimmerUtil.with(view).color(mColor).radius(mRadius).alpha(mAlpha).remove()
        }
    }

    fun removeChilds() {
        remove(mWrapperView)
    }

    private fun dp2px(context: Context, dp: Float): Int {
        val dm = DisplayMetrics()
        val manger = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manger.defaultDisplay.getMetrics(dm)
        return (dp * dm.density + 0.5f).toInt()
    }
}