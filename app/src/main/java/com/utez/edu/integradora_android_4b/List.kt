package com.utez.edu.integradora_android_4b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class List : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val btnListAll = findViewById<Button>(R.id.btnListAll)
        val btnListCat = findViewById<Button>(R.id.btnListCat)
        val btnListBarCode = findViewById<Button>(R.id.btnListBarCode)




        btnListAll.setOnClickListener(){
            val intent = Intent(this@List,categoryList::class.java)
            startActivity(intent)
        }
        btnListCat.setOnClickListener(){
            val intent = Intent(this@List,categoryListCategory::class.java)
            startActivity(intent)
        }
        btnListBarCode.setOnClickListener(){
        val intent = Intent(this@List,categoryListBarCode::class.java)
            startActivity(intent)
        }
    }
}