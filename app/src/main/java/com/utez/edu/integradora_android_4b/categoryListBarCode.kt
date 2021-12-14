package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class categoryListBarCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list_bar_code)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}