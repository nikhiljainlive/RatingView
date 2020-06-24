package com.nikhiljain.ratingview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

internal fun Context.getDrawable(@DrawableRes resourceId: Int, height: Int, width: Int): Drawable? {
    val drawable = getDrawable(resourceId)!!
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)
    val originalWidth = bitmap.width
    val originalHeight = bitmap.height
    val scaleWidth = width.toFloat() / originalWidth
    val scaleHeight = height.toFloat() / originalHeight
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    val resizedBitmap = Bitmap.createBitmap(
        bitmap, 0, 0, originalWidth,
        originalHeight, matrix, true
    )
    return BitmapDrawable(resources, resizedBitmap)
}