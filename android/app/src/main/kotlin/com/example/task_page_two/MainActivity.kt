package com.example.task_page_two
import android.widget.Toast
import androidx.annotation.NonNull
import com.squareup.moshi.Moshi

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import okhttp3.*
import okio.IOException

class MainActivity: FlutterActivity() {
    private val client = OkHttpClient()

    private val CHANNEL = "flutter.native/helper"

    @ExperimentalStdlibApi
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler{
                call, result ->
            when {
                call.method.equals("api") -> {
                    run("https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow")
                    Toast.makeText(this@MainActivity, "Get All Data Success!", Toast.LENGTH_LONG).show()
                    result.success(true)
                }
            }
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body?.string())
        })
    }
}


