package com.nikita.mlkit_camera_app.Helpers

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class FrameAnalyzer:ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        println("RABOTAET")
        image.close()
    }
}