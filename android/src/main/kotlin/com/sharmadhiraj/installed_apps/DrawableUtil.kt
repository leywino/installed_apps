package com.sharmadhiraj.installed_apps

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream

class DrawableUtil {

    companion object {
        fun drawableToByteArray(drawable: Drawable): ByteArray {
            val bitmap = drawableToBitmap(drawable)
            ByteArrayOutputStream().use { stream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                return stream.toByteArray()
            }
        }

        private fun drawableToBitmap(drawable: Drawable): Bitmap {
            // Check if the drawable is already a BitmapDrawable and return the bitmap directly.
            if (drawable is BitmapDrawable && drawable.bitmap != null) {
                return drawable.bitmap
            }

            // Safely retrieve width and height. Use fallback values if they are <= 0.
            val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 1
            val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 1

            // Create a mutable bitmap with the computed width and height.
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            // Create a canvas to draw the drawable on the bitmap.
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            return bitmap
        }

    }
}