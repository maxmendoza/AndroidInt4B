package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button

import android.widget.ImageView



class Register : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       val btnCamera = findViewById<Button>(R.id.btnCamera)
       val cam_visor = findViewById<ImageView>(R.id.cam_visor)



    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    /*override fun onClick(p0: View?) {
        val cameraIntent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        )
    }
    */
}