package com.nikita.mlkit_camera_app.cameraFeature

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.nikita.mlkit_camera_app.Helpers.FrameAnalyzer
import com.nikita.mlkit_camera_app.R

class CameraScanActivity: AppCompatActivity() {
    private lateinit var cameraFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_camera)

        cameraFuture = ProcessCameraProvider.getInstance(this)
        cameraFuture.addListener({
            val provider = cameraFuture.get()
            settingCamera(provider)

        }, ContextCompat.getMainExecutor(this))
    }

    private fun settingCamera(cameraProvider: ProcessCameraProvider){
        val cameraselector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        val preview = Preview.Builder()
            .build()

        val cameraPreview = findViewById<PreviewView>(R.id.Preview_View)
        println("CAMERA : $cameraPreview")
        preview.setSurfaceProvider(cameraPreview.surfaceProvider)

        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(mainExecutor, FrameAnalyzer())


        cameraProvider.bindToLifecycle(this, cameraselector, preview, imageAnalysis)
    }
}