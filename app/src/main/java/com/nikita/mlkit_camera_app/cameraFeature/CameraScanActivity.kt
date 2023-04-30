package com.nikita.mlkit_camera_app.cameraFeature

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.nikita.mlkit_camera_app.R

class CameraScanActivity: AppCompatActivity() {
    private lateinit var cameraFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_camera)

        cameraFuture = ProcessCameraProvider.getInstance(this)
        cameraFuture.addListener({


        }, ContextCompat.getMainExecutor(this))
    }
}