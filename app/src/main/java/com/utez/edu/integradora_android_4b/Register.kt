package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.EditText

import android.widget.ImageView



class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       val btnCamera = findViewById<Button>(R.id.btnCamera)
       val edtName = findViewById<EditText>(R.id.edtName)
       val edtDesc = findViewById<EditText>(R.id.edtDesc)
       val edtQuantity = findViewById<EditText>(R.id.edtQuantity)
       val btnRegister = findViewById<Button>(R.id.btnRegister)



        btnCamera.setOnClickListener(){

        }
        btnRegister.setOnClickListener(){

        }
    }

}