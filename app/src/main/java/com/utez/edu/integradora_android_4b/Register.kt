package com.utez.edu.integradora_android_4b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.zxing.integration.android.IntentIntegrator
import com.utez.edu.integradora_android_4b.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject


class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtDescripcion = findViewById<EditText>(R.id.edtDesc)
        val spCategory = findViewById<Spinner>(R.id.spCategoria)
        val edtCantidad = findViewById<EditText>(R.id.edtQuantity)
        val txtBarCode = findViewById<TextView>(R.id.txtBarCode)
        val queue = Volley.newRequestQueue(this)
        val url = "http://172.17.64.1:4000/producto/create/"
         val url2 = "http://172.17.64.1:4000/categoria"
        val cola2 = Volley.newRequestQueue(this)
        val listener2 = Response.Listener<JSONObject> { response ->
            val listCategory = response.getJSONArray("listcategory")
            var datos = mutableListOf<String>()
            for (i in 0 until listCategory.length()) {
                datos.add(
                    listCategory.getJSONObject(i).getString("name")
                )

            }
            val adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                datos
            )
            spCategoria.adapter = adaptador
        }
        val error2 = Response.ErrorListener { error ->
            Log.e("ERROR", error.message.toString())
        }
        val peticion = JsonObjectRequest(
            Request.Method.GET,
            url2, null, listener2, error2
        )
        cola2.add(peticion)
        val listener = Response.Listener<JSONObject> { response ->
            val estado = response.getJSONObject("products").getString("name")
            if (estado == edtName.text.toString()) {
                Toast.makeText(
                    this,
                    "Se ha insertado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "No se ha insertado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val error = Response.ErrorListener { error ->
            Toast.makeText(
                this,
                "Error, algo ha pasao ajeno a la aplicación, vuelve a intentar mas tarde",
                Toast.LENGTH_SHORT).show()
        }
        queue.add(peticion)
        binding.btnScanner.setOnClickListener { initScanner() }
        btnRegister.setOnClickListener {
            if (edtName.text.toString() == "" || edtDesc.text.toString() == "" || edtQuantity.text.toString() == "") {
                Toast.makeText(
                    this,
                    "Tienes que rellenar todos los campos solicitados",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val testeo = "2020"
                val objeto = JSONObject()
                objeto.put("name", edtName.text.toString())
                objeto.put("descripcion", edtDescripcion.text.toString())
                objeto.put("categoria", (spCategory.selectedItemId+1).toString())
                objeto.put("cantidad", edtCantidad.text.toString())
                objeto.put("codigo", txtBarCode.text.toString())
                val peticion = JsonObjectRequest(
                    Request.Method.POST,
                    url, objeto, listener, error
                )
                queue.add(peticion)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }


    //Funcion para el lector de codigo de barras

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Acerque el codigo a la camara")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    // Para recuperar los datos previamente ingresados

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                binding.txtBarCode.setText(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}