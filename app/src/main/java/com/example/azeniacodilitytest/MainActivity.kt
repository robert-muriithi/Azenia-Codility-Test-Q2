package com.example.azeniacodilitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokenStorage = TokenStorageImpl()
        val interceptor = TestInterceptor(tokenStorage)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        Thread {
            val request = Request.Builder()
                .url("")
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            runOnUiThread {
                println(responseBody)
            }
        }.start()

    }
}