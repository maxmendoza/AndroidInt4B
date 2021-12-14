package com.utez.edu.integradora_android_4b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_registerData = findViewById<Button>(R.id.btn_registerData)
        val bnt_list = findViewById<Button>(R.id.btn_list)

        btn_registerData.setOnClickListener(){
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
        bnt_list.setOnClickListener(){
            val intent = Intent(this@MainActivity, List::class.java)
            startActivity(intent)
        }
    }


}