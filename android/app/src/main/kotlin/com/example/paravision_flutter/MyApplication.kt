package com.example.paravision_flutter
import android.app.Application
import ai.paravision.sdk.android.ParavisionFaceSDK


val GlobalApp: MyApplication by lazy {
    MyApplication.app
}

class MyApplication:  Application() {
    companion object {
        lateinit var app: MyApplication
    }
    lateinit var faceSDKInstance: ParavisionFaceSDK

    override fun onCreate() {
        super.onCreate()
        app = this
        faceSDKInstance = ParavisionFaceSDK.Builder(app.applicationContext)
            .sdkMode(ParavisionFaceSDK.SDKMode.BENCHMARK)
            .build()
    }
}
