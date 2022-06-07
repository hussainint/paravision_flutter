package com.example.paravision_flutter




import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES


import androidx.annotation.NonNull 
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import android.os.Bundle
import android.graphics.BitmapFactory
import android.util.Log
import android.graphics.Bitmap
import com.example.paravision_flutter.GlobalApp
import io.flutter.embedding.android.FlutterActivity
import ai.paravision.sdk.android.ParavisionFaceSDK
import ai.paravision.sdk.android.ParavisionFaceSDK.FaceDetectionOption.*
import com.example.paravision_flutter.R





class MainActivity: FlutterActivity() {
    private val CHANNEL = "comminicationNative/Flutter"
    private val TAG = "MainActivity"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
      // Note: this method is invoked on the main thread.
      call, result ->
      if (call.method == "callPARAVISION") {
          val response = startParavision()
       result.success(response)
        
      } else {
        result.notImplemented()
      }
    }

  }

  private fun startParavision(): String {
     
     

        // get faceSdk instance
        val faceSdkInstance = GlobalApp.faceSDKInstance

        // get sample image from resource
        val testFaceImage: Bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.testface
        )

        // process the sample image
        val paravisionFaceResult = faceSdkInstance.processFullImage(
            testFaceImage,
            arrayOf(BOUNDING_BOX, QUALITY, LANDMARKS, EMBEDDING),
            true)

        // show processed result
        val detectedFaces = paravisionFaceResult?.faces
        if(detectedFaces!= null && detectedFaces.isNotEmpty()){
            Log.i(TAG, "There are ${detectedFaces.size} face(s) detected:")
            for((index, face) in detectedFaces.withIndex()){
             //  val k =  faceSdkInstance.getMatchScore(face.embedding, face.embedding)
                Log.i(TAG, "Result for face ${index+1}:")
                Log.i(TAG, "boundingBox=${face.boundingBox.toString()}")
                Log.i(TAG, "quality=${face.quality}")
                Log.i(TAG, "landmarks=${face.landmarks.toString()}")
               // Log.i(TAG, "int=${k.toString()}")
                Log.i(TAG, "embeddings=${face.embedding.toString()}")
                return face.embedding.toString()
            }
        }



    return "pass"
  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
