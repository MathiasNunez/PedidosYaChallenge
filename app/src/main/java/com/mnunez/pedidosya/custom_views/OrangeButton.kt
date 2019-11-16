package com.mnunez.pedidosya.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.mnunez.pedidosya.R


class OrangeButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        Button(context, attrs, defStyleAttr) {

    init {
        background = context.getDrawable(R.drawable.bg_blue_rounded)
        setTextColor(context.getColor(R.color.white))
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER
        isAllCaps = true
    }

}
