package com.mvvm.ati.mail.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.mvvm.ati.mail.R

class TokenTextView : AppCompatTextView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            if (selected) R.drawable.ic_close_round else 0,
            0
        )
        compoundDrawablePadding = 10
    }
}