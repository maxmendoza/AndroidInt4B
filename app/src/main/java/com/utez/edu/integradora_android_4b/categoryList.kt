package com.utez.edu.integradora_android_4b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_category_list.*
import org.json.JSONObject

class categoryList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cola = Volley.newRequestQueue(this@categoryList)
        val url = "http://172.17.64.1:4000/producto"
        val listener = Response.Listener<JSONObject> {response ->
            val listProducts = response.getJSONArray("products")
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

            val adaptador = CustomAdapter(this@categoryList,R.layout.layout_productos, datos)
            listView.adapter = adaptador;
        }

        val error = Response.ErrorListener { error ->
            Toast.makeText(this@categoryList,error.message.toString(),Toast.LENGTH_SHORT).show()
        }

        val peticion = JsonObjectRequest(Request.Method.GET,url,null,listener,error)
        cola.add(peticion)



    }
}