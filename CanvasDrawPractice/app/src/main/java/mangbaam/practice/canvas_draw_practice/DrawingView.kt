package mangbaam.practice.canvas_draw_practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var currentBox: Box? = null
    private val boxes = mutableListOf<Box>()
    private val boxPaint = Paint().apply {
        color = 0x222c3e50.toInt()
    }
    private val backgroundColor = Paint().apply {
        color = 0xf8efe0.toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPaint(backgroundColor)

        boxes.forEach { box ->
            canvas?.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        val current = PointF(event.x, event.y)
        var action = ""

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"
                currentBox = Box(current).also {
                    boxes.add(it)
                }
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"
                updateBoxPos(current)
                currentBox = null
            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"
                updateBoxPos(current)
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
                currentBox = null
            }
        }
        Log.i("로그", "$action at x=${current.x}, y=${current.y}")

        return true
    }

    private fun updateBoxPos(current: PointF) {
        currentBox?.let {
            it.end = current
            invalidate()
        }
    }
}
