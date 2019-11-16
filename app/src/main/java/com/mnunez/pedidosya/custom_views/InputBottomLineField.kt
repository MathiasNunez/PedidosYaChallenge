package com.mnunez.pedidosya.custom_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.EditText
import com.mnunez.core.extensions.shake
import com.mnunez.pedidosya.R


open class InputBottomLineField
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) :
    EditText(context, attrs, defStyleAttr) {

    private var errorBackground: Drawable? = null
    private var normalStateBackground: Drawable? = null
    private var hasError: Boolean = false

    init {
        background = context.getDrawable(R.drawable.bg_input_bottom_line)
        normalStateBackground = background
        errorBackground = context.getDrawable(R.drawable.bg_input_error_bottom_line)
    }

    override fun setError(message: CharSequence) {
        super.setError(message, null)
        shake()
        hasError = true
        background = errorBackground
    }

    fun setErrorIconOnly() {
        shake()
        hasError = true
        background = errorBackground
    }

    fun setErrorLineOnly() {
        background = errorBackground
    }

    fun removeError() {
        super.setError(null)
        hasError = false
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        background = normalStateBackground

    }
}