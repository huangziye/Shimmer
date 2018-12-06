package com.hzy.shimmerview.filter

import android.view.View

/**
 * Shimmer filter
 * Created by ziye_huang on 2018/12/5.
 */
interface OnShimmerFilter {
    fun onFilter(view: View): FilterType
}