package com.nikhiljain.ratingview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatTextView

class RatingView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        initRatingView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initRatingView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initRatingView(attrs)
    }

    private fun initRatingView(attributeSet: AttributeSet? = null) {
        attributeSet?.let {
            val obtainStyledAttributes =
                context.theme.obtainStyledAttributes(
                    it, R.styleable.RatingView, 0, 0
                )

            val ratingValue: Float
            @ColorRes val tintColor: Int
            @Dimension(unit = Dimension.DP) val viewSize: Int
            val defaultValue = resources.getDimensionPixelSize(R.dimen.rating_content_size)
            try {
                ratingValue =
                    obtainStyledAttributes.getFloat(
                        R.styleable.RatingView_rating_value, 0.0F
                    )
                tintColor = obtainStyledAttributes.getColor(
                    R.styleable.RatingView_tintColor,
                    Color.WHITE
                )
                viewSize = obtainStyledAttributes.getDimensionPixelSize(
                    R.styleable.RatingView_viewContentSize,
                    defaultValue
                )
            } finally {
                obtainStyledAttributes.recycle()
            }

            text = resources.getString(R.string.space_string, ratingValue.toString())
            setTextColor(tintColor)
            textSize = viewSize.toFloat()
            val resourceId = R.drawable.ic_star
            val drawable = if (viewSize == 16) context.getDrawable(
                resourceId
            ) else context.getDrawable(resourceId, viewSize, viewSize)
            setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                drawable, null
            )
            compoundDrawableTintList = ColorStateList.valueOf(tintColor)
            val padding = resources.getDimensionPixelSize(R.dimen.rating_view_padding)
            setPadding(padding.times(2), padding, padding.times(2), padding)
        }
    }

    fun setRatingValue(ratingValue: Float) {
        text = resources.getString(R.string.space_string, ratingValue.toString())
    }

    fun setTintColor(@ColorInt tintColor: Int) {
        setTextColor(tintColor)
        compoundDrawableTintList = ColorStateList.valueOf(tintColor)
    }
}
