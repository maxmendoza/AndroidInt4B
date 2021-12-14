package com.utez.edu.integradora_android_4b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.utez.edu.integradora_android_4b.databinding.ActivityRegisterBinding


class lectorQr : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.btnScanner.setOnClickListener { initScanner() }
//    }
//    private fun initScanner(){
//        val integrator = IntentIntegrator(this)
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//        integrator.setPrompt("ESCANEANDO")
//        integrator.setTorchEnabled(true)
//        integrator.setBeepEnabled(true)
//        integrator.initiateScan()
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        if (result != null) {
//            if (result.contents == null) {
//                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
//            }else if(result.contents == "7500478024827") {
//                Toast.makeText(this, "papas sabritas bien caras alv", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
    }

}