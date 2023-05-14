package com.nikita.mlkit_camera_app.cameraFeature

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

class LandMarkView(
    context: Context,
    attributes: AttributeSet
): View(context, attributes) {
    private var viewSize = Size(0,0)
    private val mainPaint = Paint(ANTI_ALIAS_FLAG)
    private var detectorPose: Pose? = null
    private var sizeSource = Size(0,0)
    init {
        mainPaint.color = Color.WHITE
        mainPaint.strokeWidth = 5f
        mainPaint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size(w,h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var landmark = detectorPose?.getPoseLandmark(PoseLandmark.NOSE)
        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_MOUTH)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_WRIST)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_WRIST)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_ELBOW)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }
        landmark = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)

        if(landmark != null){
            DrawLendMark(landmark, canvas)
        }

        landmark = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_EYE)

        landmark?.let {
            DrawLendMark(it, canvas)
        }
    }

    fun SetParameters(pose: Pose, sourceSize: Size){
        detectorPose = pose
        sizeSource = sourceSize
        invalidate()
    }

    private fun DrawLendMark(landMark: PoseLandmark, drawCanvas: Canvas? ){
        val position = ConvertPoint(landMark.position3D)
        drawCanvas?.drawCircle(position.x, position.y, 20f, mainPaint)
    }

    private fun ConvertPoint(target:PointF3D,):PointF{
        val x1 = target.x; val y1 = target.y

        val w1 = sizeSource.width; val h1 = sizeSource.height

        val w2 = viewSize.width; val h2 = viewSize.height

        val x2 = x1 * w2/w1
        val y2 = y1 * h2/h1
        return PointF(x2, y2)
    }
}



