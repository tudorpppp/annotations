package com.example.annotationsandroidsampleapp.ui.main

import android.R.attr.mode
import android.R.attr.spacing
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsoluteLayout
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.annotationsandroidsampleapp.R
import com.otaliastudios.zoom.ZoomImageView


class SecondFragment :Fragment(){

    companion object{
        fun newInstance() = SecondFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    var xDelta = 0f
    var yDelta = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        val zoom = view.findViewById<ZoomImageView>(R.id.zoomImageView)
        zoom.setImageBitmap(BitmapFactory.decodeResource(context?.resources, R.drawable.flower))

        val button = view.findViewById<Button>(R.id.add_circle)

        var imageView :ImageView? = null

        button.setOnClickListener {

            val parentLayout = view.findViewById(R.id.constraint) as ConstraintLayout
            val set = ConstraintSet()

            imageView = ImageView(context)
            // set view id, else getId() returns -1
            // set view id, else getId() returns -1
            imageView!!.setId(View.generateViewId())
            imageView!!.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.my_shape))
            parentLayout.addView(imageView, 0)

            set.clone(parentLayout)
            // connect start and end point of views, in this case top of child to top of parent.
            // connect start and end point of views, in this case top of child to top of parent.
            set.connect(
                imageView!!.getId(),
                ConstraintSet.TOP,
                parentLayout.id,
                ConstraintSet.TOP,
                60
            )
            // ... similarly add other constraints
            // ... similarly add other constraints
            set.applyTo(parentLayout)

        }

//        imageView?.let {
//            it.setOnTouchListener { view, motionEvent ->
//                when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
//                    MotionEvent.ACTION_DOWN -> {
//                        xDelta = motionEvent.getX()
//                        yDelta = motionEvent.getY()
//                    }
//                    MotionEvent.ACTION_POINTER_DOWN -> {
//                        oldDist = spacing(motionEvent)
//                        if (oldDist > 10f) {
//                            mode = ZOOM
//                        }
//                    }
//                    MotionEvent.ACTION_UP -> {
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        x1Delta = motionEvent.getX() - xDelta
//                        y2Delta = motionEvent.getY() - yDelta
//                        m_posX = m_prevX + x1Delta
//                        m_posY = m_prevY + y2Delta
//                        if (m_posX > 0 && m_posY > 0 && m_posX + view.width < mainLayout.getWidth() && m_posY + view.height < mainLayout.getHeight()) {
//                            view.layoutParams = AbsoluteLayout.LayoutParams(
//                                view.measuredWidth,
//                                view.measuredHeight,
//                                m_posX as Int,
//                                m_posY as Int
//                            )
//                            m_prevX = m_posX
//                            m_prevY = m_posY
//                        }
//                    }
//                }
//                mainLayout.invalidate()
//                true
//            }
//        }

    }

}