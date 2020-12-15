package com.developer.mjc.cutom_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class MyCustomView: View {

    constructor(context: Context,attrs: AttributeSet): super(context,attrs)

    private val paint =  Paint(Paint.ANTI_ALIAS_FLAG)

    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var boarderColor = Color.BLACK

    //boarder width in pixels
    private var boarderWidth = 4.0f
    private var size = 320


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)

    }

    private fun drawMouth(canvas: Canvas?) {

    }

    private fun drawEyes(canvas: Canvas?) {

        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEye = RectF(size * 0.30f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas!!.drawOval(leftEye,paint)

    }

    private fun drawFaceBackground(canvas: Canvas?) {

        paint.color = faceColor
        paint.style = Paint.Style.FILL

        val radius = size/2f

        canvas!!.drawCircle( size/2f,size/2f,radius,paint)

        paint.color = boarderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = boarderWidth

        canvas.drawCircle(size/2f,size/2f,radius - boarderWidth,paint)


    }


}