package com.nikita.mlkit_camera_app.Helpers

import android.media.Image
import android.util.Size
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.interfaces.Detector
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import com.nikita.mlkit_camera_app.cameraFeature.LandMarkView
import java.lang.Integer.min
import java.lang.Integer.max


@ExperimentalGetImage
class FrameAnalyzer(
    private val viewPoint: LandMarkView

):ImageAnalysis.Analyzer {

    private val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()

    private var detection = PoseDetection.getClient(options)

    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if(mediaImage != null){
           val imageForDetector = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
            detection.process(imageForDetector)
                .addOnSuccessListener {resultPose ->
                    val size = Size(
                        min(image.width, image.height),
                        max(image.width, image.height)

                    )
                    viewPoint.SetParameters(resultPose, size)
                    image.close()
                }
                .addOnFailureListener{
                    println("NE UDALOS RASPOZNAT")
                    image.close()
                }
        }
    }
}