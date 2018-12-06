package com.hzy.shimmer

import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.hzy.shimmerview.util.ShimmerUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ll = findViewById<LinearLayout>(R.id.ll)
        ShimmerUtil.with(ll).renderChilds()

        Handler().postDelayed({ShimmerUtil.with(ll).removeChilds()},5000)
    }
}
