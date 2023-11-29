package com.rolstudio.baraban_custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator

@SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
class CustomBaraban(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val paint = Paint()
    private val startArc = -180f
    var isSpinning: Boolean = false
    private var currentRotation: Float = 0f
//    private var textToShow: String = ""
    private val colorsARc = listOf(
        Color.RED,
        Color.rgb(255, 165, 0),
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.rgb(75, 0, 130),
        Color.CYAN
    )
    private val sweepAngle = 360f / colorsARc.size

    init {
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (!isSpinning) {
                    startSpinAnimation()
                }
            }
            true
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawArcCircle(canvas)
    }

    private fun drawArcCircle(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = width / 2f
        val radius = width.coerceAtMost(height) / 2f

        for (i in colorsARc.indices) {
            paint.color = colorsARc[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startArc + i * sweepAngle,
                sweepAngle,
                true,
                paint
            )
        }
    }

    fun startSpinAnimation() {
        isSpinning = true
        val rotationDegrees = currentRotation + (0..6).random() * 180f
        animate().apply {
            rotation(rotationDegrees)
            duration = 5000
            interpolator = DecelerateInterpolator()
            withEndAction {
                stopSpinAnimation()
//                identifyColorUnderPointer()
            }
            start()
        }
    }

    private fun stopSpinAnimation() {
        currentRotation = (currentRotation + rotation) % 360
        rotation = currentRotation
        isSpinning = false
    }

//    private fun identifyColorUnderPointer() {
//        val sector = (currentRotation / (360 / colorsARc.size)).toInt()
//        textToShow = if (sector % 2 == 0) {
//            "Text for color ${sector + 1}"
//        } else {
//            "Text"
//        }
//        val mainActivity = context as MainActivity
//        mainActivity.updateTextView(textToShow)
//    }

}