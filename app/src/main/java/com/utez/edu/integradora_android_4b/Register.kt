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


open class Register : AppCompatActivity()  {
    open lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnScanner.setOnClickListener { initScanner() }

        val txtBarCode = findViewById<TextView>(R.id.txtBarCode)
       val edtName = findViewById<EditText>(R.id.edtName)
       val edtDesc = findViewById<EditText>(R.id.edtDesc)
       val edtQuantity = findViewById<EditText>(R.id.edtQuantity)
       val btnRegister = findViewById<Button>(R.id.btnRegister)
        val spCategoria = findViewById<Spinner>(R.id.spCategoria)
        val url2 = "http://172.17.64.1:4000/categoria"
        val cola2 = Volley.newRequestQueue(this)
        val listener = Response.Listener<JSONObject> { response ->
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
        val error = Response.ErrorListener { error ->
            Log.e("ERROR", error.message.toString())
        }
        val peticion = JsonObjectRequest(
            Request.Method.GET,
            url2, null, listener, error
        )

        cola2.add(peticion)


        btnRegister.setOnClickListener(){

        }
    }
    open fun initScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("ESCANEANDO")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }
    ////Lector de Codigo De Barras
    open override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            }
            /*else if(result.contents == "7500478024827") {

                Toast.makeText(this, "papas sabritas bien caras alv", Toast.LENGTH_LONG).show()
            }*/ else {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                txtBarCode.text= result.contents.toString()
                btnRegister.setOnClickListener(){
                   /// peticion
                    val cola = Volley.newRequestQueue(this)
                    var urlRegister = "http://172.17.64.1:4000/producto/create/"
                    val error = Response.ErrorListener { error ->
                        Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                    val listener = Response.Listener<JSONObject> { resultado ->
                        //Indicar al usuario el resultado
                        if("insertId" in resultado.names().toString() && resultado.getInt("insertId") != 0){
                            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show()
                            //Limpiar formulario
                            edtName.setText("")
                            edtDesc.setText("")
                            edtQuantity.setText("")
                            spCategoria.setSelection(0)
                            txtBarCode.setText("")
                        }else {
                            Toast.makeText(this, "No se pudo realizar el registro", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    //Recuperar datos
                    val name = edtName.text.toString()
                    val descripcion = edtDesc.text.toString()
                    val categoria = (spCategoria.selectedItemId+1).toString()
                    val codigo = txtBarCode.text.toString()
                    val cantidad = edtQuantity.text.toString()

                    //Validación
                    if(name != "" && descripcion != "" && categoria != "" && codigo != "" && cantidad != ""){
                        //Crear body
                        val body = JSONObject()
                        body.put("name", name)
                        body.put("descripcion", descripcion)
                        body.put("categoria", categoria)
                        body.put("cantidad", cantidad)
                        body.put("codigo",codigo)

                        //Hacer peticion/Añadir a la cola
                        val peticion = JsonObjectRequest(Request.Method.POST, urlRegister, body, listener, error)
                        cola.add(peticion)
                    }else{
                        Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
                    }
                    urlRegister = "http://172.17.64.1:4000/producto/create/"
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}