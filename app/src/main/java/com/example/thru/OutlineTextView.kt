package com.example.thru

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat

class OutlineTextView( context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatTextView( context, attrs )
{
    private val strokeWidth : Float
    private val strokeColor : Int

    init
    {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.OutlineTextView,
            0, 0
        )
            .apply{
                try
                {
                    strokeWidth = getFloat( R.styleable.OutlineTextView_TextStrokeWidth, 5f )
                    strokeColor = getColor( R.styleable.OutlineTextView_TextStrokeColor, ContextCompat.getColor(context, R.color.background) )
                }
                finally
                {
                    recycle()
                }
            }
    }

    override fun onDraw(canvas: Canvas)
    {
        val textColor : Int = currentTextColor

        setTextColor( strokeColor )
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        super.onDraw(canvas)

        setTextColor( textColor )
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
        super.onDraw(canvas)
    }
}