package com.example.cameraapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.cameraapp.ui.theme.CameraAppTheme

class MainActivity : ComponentActivity() {
    private var permissionDeniedMessage: String? by mutableStateOf(null)
    private var capturedImage: Bitmap? by mutableStateOf(null)

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            capturedImage = imageBitmap
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            openCamera()
        } else {
            permissionDeniedMessage = "Sorry, no permission to access camera"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CameraCapture(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClick = { checkCameraPermission() },
                        capturedImage = capturedImage,
                        permissionDeniedMessage = permissionDeniedMessage
                    )
                }
            }
        }
    }
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }
}

@Composable
fun CameraCapture(modifier: Modifier = Modifier, onButtonClick: () -> Unit, capturedImage: Bitmap?, permissionDeniedMessage: String?) {
    Column (modifier = modifier) {
        capturedImage?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.padding(16.dp))
        }
        permissionDeniedMessage?.let {
            Text(text = it, modifier = Modifier.padding(16.dp))
        }
        Button (
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors()
        ) {
            Text("Open camera")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CameraAppTheme {
        CameraCapture(onButtonClick = {}, capturedImage = null, permissionDeniedMessage = null)
    }
}