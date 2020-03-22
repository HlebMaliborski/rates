package com.gangofdevelopers.currencyrates.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.gangofdevelopers.currencyrates.R

class NoInternetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        initRootLayoutParams()
    }

    private fun initRootLayoutParams() {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        orientation = VERTICAL
        gravity = Gravity.CENTER

        LayoutInflater.from(context).inflate(R.layout.view_no_internet, this, true)
    }
}