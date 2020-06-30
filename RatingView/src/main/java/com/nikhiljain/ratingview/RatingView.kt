package com.nikhiljain.ratingview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView

class RatingView : CardView {
    private val cardDefaultColor = Color.parseColor("#388E3C")

    private val ratingTextView = AppCompatTextView(this.context).apply {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        ).also {
            it.marginEnd = resources.getDimensionPixelSize(
                R.dimen.rating_view_padding
            )
        }
    }.also {
        it.setTextColor(Color.WHITE)
        it.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.rating_text_default_size)
        )
    }

    private val ratingImageView = ImageView(this.context).apply {
        val imageSize = resources.getDimension(R.dimen.rating_image_default_size)
        layoutParams = LayoutParams(
            imageSize.toInt(), LayoutParams.WRAP_CONTENT
        )
        this.imageTintList = ColorStateList.valueOf(Color.WHITE)
    }.also {
        it.setImageDrawable(
            context.getDrawable(R.drawable.ic_star)
        )
    }

    private val childLayout = LinearLayout(this.context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }.also {
        val padding = resources.getDimensionPixelSize(R.dimen.padding_small)
        it.contentDescription = context.getString(R.string.rating_stars)
        it.setPadding(padding, 0, padding, 0)
        it.addView(ratingTextView)
        it.addView(ratingImageView)
    }

    constructor(context: Context) : super(context) {
        initRatingLayout()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initRatingLayout(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initRatingLayout(attrs)
    }

    private fun initRatingLayout(attributeSet: AttributeSet? = null) {
        setCardBackgroundColor(cardDefaultColor)
        addView(childLayout)
        attributeSet?.let {
            val attributesArray = context.theme.obtainStyledAttributes(
                it,
                R.styleable.RatingView,
                0, 0
            )
            val ratingValue: Float
            val tintColor: Int
            try {
                ratingValue = attributesArray.getFloat(
                    R.styleable.RatingView_rating_value,
                    0.0F
                )
                tintColor = attributesArray.getColor(
                    R.styleable.RatingView_tintColor,
                    context.getColor(android.R.color.white)
                )
            } finally {
                attributesArray.recycle()
            }
            setRatingValue(ratingValue)
            setTintColor(tintColor)
        }
    }

    fun setRatingValue(ratingValue: Float) {
        ratingTextView.text = ratingValue.toString()
    }

    fun setTintColor(@ColorInt tintColor: Int) {
        ratingTextView.setTextColor(tintColor)
        ratingImageView.imageTintList = ColorStateList.valueOf(tintColor)
    }
}
