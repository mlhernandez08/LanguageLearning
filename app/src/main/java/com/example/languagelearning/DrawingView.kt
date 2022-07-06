package com.example.languagelearning

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import com.example.languagelearning.WritingQuizFragment.Companion.paintBrush
import com.example.languagelearning.WritingQuizFragment.Companion.path
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.digitalink.*
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager



class DrawingView : View{

    var params: ViewGroup.LayoutParams? = null
    private var inkBuilder = Ink.Builder()
    private lateinit var strokeBuilder: Ink.Stroke.Builder

    companion object {

        var pathList = ArrayList<Path>()
        var brushColor = Color.BLACK
        var translated = String()
    }

    constructor(context: Context) : this(context, null) {
        init()
    }
    constructor(context: Context, attrs:AttributeSet?) : this(context, attrs, 0) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,defStyleAttr) {
        init()
    }

    private fun init(){
        paintBrush.isAntiAlias = true
        paintBrush.color = brushColor
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val x = event.x
        val y = event.y
        val t = System.currentTimeMillis()

        when(action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                strokeBuilder = Ink.Stroke.builder()
                strokeBuilder.addPoint(Ink.Point.create(x,y,t))
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y)
                pathList.add(path)
                strokeBuilder.addPoint(Ink.Point.create(x, y, t))
            }
            MotionEvent.ACTION_UP -> {
                strokeBuilder.addPoint(Ink.Point.create(x,y,t))
                inkBuilder.addStroke(strokeBuilder.build())
            }
        }
        invalidate()
        return true

    }
    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){
            paintBrush.setColor(brushColor)
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }

    fun getInk(): Ink{
        val ink = inkBuilder.build()
        return ink
    }

    fun clear(){
        path.reset()
        inkBuilder = Ink.builder()
        invalidate()
    }

    //var inkBuilder = Ink.builder()
    //private var strokeBuilder = Ink.Stroke.builder()

    private fun addNewTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val x = event.x
        val y = event.y
        val t = System.currentTimeMillis()

        // A new event happened -> clear all pending timeout messages.
        when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> strokeBuilder.addPoint(
                Ink.Point.create(
                    x,
                    y,
                    t
                )
            )
            MotionEvent.ACTION_UP -> {
                strokeBuilder.addPoint(Ink.Point.create(x, y, t))
                inkBuilder.addStroke(strokeBuilder.build())
                strokeBuilder = Ink.Stroke.builder()
            }
            else -> // Indicate touch event wasn't handled.
                return false
        }
        return true
    }

   /* fun recognize(): String {
        recognizer.recognize(newInk).addOnSuccessListener { result: RecognitionResult ->
            translated = result.candidates[0].text
            Log.e(ContentValues.TAG, result.candidates[0].text);
        }
        return translated
    }

    */

}

/*override fun onTouchEvent(event: MotionEvent): Boolean {
    val action = event.actionMasked
    val x = event.x
    val y = event.y
    when (action) {
        MotionEvent.ACTION_DOWN -> {
            path.moveTo(x,y)
            return true
        }
        MotionEvent.ACTION_MOVE -> {
            path.lineTo(x,y)
            DrawingView.pathList.add(path)
        }
        else -> {
        }
    }
    addNewTouchEvent(event)
    invalidate()
    return true

}

 */