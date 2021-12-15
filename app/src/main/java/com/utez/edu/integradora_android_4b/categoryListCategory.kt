package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_category_list.*
import org.json.JSONObject

class categoryListCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val spCategoryList = findViewById<Spinner>(R.id.spCategoryList)
        val btnListCategory = findViewById<Button>(R.id.btnListCategory)
        val testeoId = findViewById<TextView>(R.id.testeoId)
        val cola3 = Volley.newRequestQueue(this)
        val url2 = "http://172.17.64.1:4000/categoria"
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
                spCategoryList.adapter = adaptador
            }
            val error = Response.ErrorListener { error ->
                Log.e("ERROR", error.message.toString())
            }
            val peticion = JsonObjectRequest(
                Request.Method.GET,
                url2, null, listener, error
            )

            cola3.add(peticion)

        btnListCategory.setOnClickListener(){
            val testeoTesteo = spCategoryList.selectedItemId + 1
            val cola = Volley.newRequestQueue(this)
            var url3 = "http://172.17.64.1:4000/categoria/categoria/" + testeoTesteo
            val listener = Response.Listener<JSONObject> {response ->

                val listProducts = response.getJSONArray("cat")
                var datos = mutableListOf<Producto>()

                for (i in 0 until listProducts.length()){
                    datos.add(
                        Producto(
                            listProducts.getJSONObject(i).getInt("id"),
                            listProducts.getJSONObject(i).getString("name"),
                            listProducts.getJSONObject(i).getString("descripcion"),
                            listProducts.getJSONObject(i).getInt("categoria"),
                            listProducts.getJSONObject(i).getInt("cantidad"),
                            listProducts.getJSONObject(i).getString("codigo")
                        )
                    )
                }

                val adaptador = CustomAdapter(this,R.layout.activity_categoria, datos)
                listView.adapter = adaptador;
            }

            val error = Response.ErrorListener { error ->
                Toast.makeText(this,error.message.toString(),Toast.LENGTH_SHORT).show()
            }

            val peticion = JsonObjectRequest(Request.Method.GET,url3,null,listener,error)
            cola.add(peticion)
            url3 = "http://172.17.64.1:4000/categoria/categoria/"
            }


        }
//            val listProducts = response.getJSONArray("products")
//            var datosCat = mutableListOf<Categoria>()
//
//            for (i in 0 until listProducts.length()){
//                datosCat.add(
//                    Categoria(
//                        listProducts.getJSONObject(i).getInt("id"),
//                        listProducts.getJSONObject(i).getString("name")
//                    )
//                )
//            }
//
//            val adaptador = CustomAdapter(this@categoryListCategory,R.layout.layout_productos, )
//            listView.adapter = adaptador;
//        }
//
//        val error = Response.ErrorListener { error ->
//            Toast.makeText(this@categoryListCategory,error.message.toString(),Toast.LENGTH_SHORT).show()
//        }
//
//        val peticion = JsonObjectRequest(Request.Method.GET,url,null,listener,error)
//        cola.add(peticion)
//

    }