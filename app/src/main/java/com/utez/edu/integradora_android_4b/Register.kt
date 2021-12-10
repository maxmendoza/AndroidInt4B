package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.content.Intent
import android.provider.MediaStore


class Register : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       val btnCamera = findViewById<Button>(R.id.btnCamera)

        btnCamera.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val cameraIntent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        )
    }
}