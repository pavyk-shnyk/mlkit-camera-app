package com.nikita.mlkit_camera_app.start_Scan


import android.Manifest
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nikita.mlkit_camera_app.R
import com.nikita.mlkit_camera_app.cameraFeature.CameraScanActivity

class StartScan: Fragment() {
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        {isGranted:Boolean ->
            if(isGranted == true){
                startScan()
            }else{
            }
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_scan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startScanButton = view.findViewById<Button>(R.id.start_camera_button)
        startScanButton.setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            when (permission){
                PackageManager.PERMISSION_GRANTED -> {
                    startScan()
                }else -> {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
            
        }
    }
    private fun startScan(){
        val intent = Intent(requireContext(), CameraScanActivity::class.java)
        startActivity(intent)
    }



}