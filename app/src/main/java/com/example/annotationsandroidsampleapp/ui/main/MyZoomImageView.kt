package com.example.annotationsandroidsampleapp.ui.main

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import com.otaliastudios.zoom.ZoomImageView


class MyZoomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ZoomImageView(context, attrs, defStyleAttr) {

    var circleX = (context.display!!.width / 2).toFloat()
    var circleY = (context.display!!.height / 4).toFloat()
    val circleRadius = 140f

    val myRect = RectF()
    var myRectWidth = 250f
    var myRectHeight = 250f
    var posX = 100f
    var posY = 100f

    val path = Path()

    val points : ArrayList<Pair<Float,Float>> = arrayListOf()

    val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 25f
    }

    var time = System.currentTimeMillis()
    var stop = false
    override fun onFinishInflate() {
        super.onFinishInflate()

        postInvalidate()

    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                if (System.currentTimeMillis() - time > 10000){
                    stop = true
                }else {
                    points.add(ev.x to ev.y)
                   path.moveTo(ev.x, ev.y)
                }
                //return super.onTouchEvent(ev)
                if (stop == true){
                    return super.onTouchEvent(ev)
                }else {
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (stop == true){
                    return super.onTouchEvent(ev)
                }else {
                    points.add(ev.x to ev.y)
                    path.lineTo(ev.x, ev.y)
                }
//                val x = ev.x
//                val y = ev.y
//
//                val dx = Math.pow((x - circleX).toDouble(), 2.toDouble())
//                val dy = Math.pow((y - circleY).toDouble(), 2.toDouble())
//
//                if (dx + dy < Math.pow((circleRadius).toDouble(), 2.toDouble())) {
//                    circleX = x
//                    circleY = y
//
//                    postInvalidate()
//                    return true
//                } else {
//                    return super.onTouchEvent(ev)
//                }
            }
        }
        invalidate()
        //return super.onTouchEvent(ev)
        if (stop == true) {
            return super.onTouchEvent(ev)
        }else {
            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
//
//        Log.d("pan", "zoom " + zoom.toString())
//        Log.d("pan", "realZoom" + realZoom.toString())
//
//        myRect.set(panX, panY, myRect.left + myRectHeight * zoom, myRect.right + myRectWidth * zoom)
//
//        //canvas.drawRect(myRect, Paint(Color.BLUE))
//
//        canvas.drawCircle(
//            circleX * zoom + scaledPanX,
//            circleY * zoom + scaledPanY,
//            circleRadius * zoom,
//            Paint(Color.BLUE))
        if (points.isNotEmpty()) {
            val pat = Path().also {  it.moveTo(points[0].first * zoom + scaledPanX ,points[0].second * zoom + scaledPanY)}
            points.forEach {
                pat.lineTo(it.first * zoom +scaledPanX,it.second * zoom + scaledPanY)
            }
            canvas.drawPath(pat,paint)
        }

        //canvas.drawPath(path, paint)

        canvas.restore()
    }

}