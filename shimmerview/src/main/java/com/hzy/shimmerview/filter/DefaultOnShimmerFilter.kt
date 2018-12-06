package com.hzy.shimmerview.filter

import android.view.View
import android.view.ViewGroup

/**
 * Default shimmer filter
 * Created by ziye_huang on 2018/12/5.
 */
class DefaultOnShimmerFilter : OnShimmerFilter {
    override fun onFilter(view: View): FilterType {
        return when {
            view == null -> FilterType.IGNORED
            //filter invisible
            view.visibility != View.VISIBLE -> FilterType.IGNORED
            //filter android.view.View
            View::class.java == view::class.java -> FilterType.IGNORED
            view is ViewGroup -> FilterType.CHILDS
            else -> {
                return FilterType.LOADING
            }
        }
    }

}