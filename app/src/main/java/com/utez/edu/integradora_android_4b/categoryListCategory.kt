package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class categoryListCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}