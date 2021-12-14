package com.utez.edu.integradora_android_4b

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.collections.List

class CustomAdapter (val context: Context, val layout:Int, val  datos : List<Producto>): BaseAdapter(){
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return datos.size
    }

    override fun getItem(position: Int): Any {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(layout,parent,false)
        val txtNombre = view.findViewById<TextView>(R.id.txtName)
        val txtDescripcion = view.findViewById<TextView>(R.id.txtDescripcion)
        val categoria = view.findViewById<TextView>(R.id.txtCat)
        val txtCan = view.findViewById<TextView>(R.id.txtCantidad)
        val txtCodigoBarras = view.findViewById<TextView>(R.id.txtCodigoBarra)

        val data = getItem(position) as Producto

        txtNombre.text = data.name
        txtDescripcion.text = data.descripcion
        categoria.text = data.categoría.toString()
        txtCan.text = data.cantidad.toString()
        txtCodigoBarras.text = data.codigo
        return view
    }

}