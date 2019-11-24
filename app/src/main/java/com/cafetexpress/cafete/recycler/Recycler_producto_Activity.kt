package com.cafetexpress.cafete.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.adapters.AdapterProducto
import com.cafetexpress.cafete.model.productos
import com.cafetexpress.cafete.sql.adminDB

class Recycler_producto_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_producto_)

        val rview=findViewById<RecyclerView>(R.id.rv_prod_list)
        val adapter= AdapterProducto(this)
        rview.adapter=adapter

        rview.layoutManager= LinearLayoutManager(this) //Muestra los elementos en lista

        adapter.setList(ListaProd())
    }
    
    fun ListaProd():List<productos>{
        var prod = ArrayList<productos>()
        val database = adminDB(this)
        val cursor = database.consulta("select id_producto, nombre, precio, cantidad, tiempo from producto")
        if(cursor!!.moveToFirst())do{
            val stu = productos(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getInt(3),
                cursor.getString(4)
            )

            prod.add(stu)
        }
        while(cursor!!.moveToNext())
        cursor.close()
        return prod
    }
}
