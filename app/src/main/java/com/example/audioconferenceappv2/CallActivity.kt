package com.example.audioconferenceappv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val APP_ID ="5d1650822f2749c59802c8590ef221ff"

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
    }
}