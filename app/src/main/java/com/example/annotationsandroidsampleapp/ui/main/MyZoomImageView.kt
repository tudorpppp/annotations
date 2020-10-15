package com.example.annotationsandroidsampleapp.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.otaliastudios.zoom.ZoomImageView

class MyZoomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ZoomImageView(context, attrs, defStyleAttr) {

    var circleX = 100f
    var circleY = 100f
    val circleRadius = 140f

    val myRect = RectF()
    var myRectWidth = 250f
    var myRectHeight = 250f
    var posX = 100f
    var posY = 100f

    override fun onTouchEvent(ev: MotionEvent): Boolean {

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                return super.onTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                val x = ev.x
                val y = ev.y

                val dx = Math.pow((x - circleX).toDouble(), 2.toDouble())
                val dy = Math.pow((y - circleY).toDouble(), 2.toDouble())

                if (dx + dy < Math.pow((circleRadius * zoom).toDouble(), 2.toDouble())) {
                    circleX = x
                    circleY = y

                    postInvalidate()
                    return true
                } else {
                    return super.onTouchEvent(ev)
                }
            }
        }

        return super.onTouchEvent(ev)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        Log.d("pan", "PanX " + panX.toString())
        Log.d("pan", "ScaledX " + scaledPanX.toString())
        Log.d("pan", "Scale factor " + scaleX.toString())

        Log.d("pan", width.toString())

        myRect.set(panX, panY, myRect.left + myRectHeight * zoom, myRect.right + myRectWidth * zoom)

        //canvas.drawRect(myRect, Paint(Color.BLUE))

        val screenWidth =

            canvas.drawCircle(
                circleX + panX * 4,
                circleY + panY * 4,
                circleRadius * zoom,
                Paint(Color.BLUE)
            )

    }

}