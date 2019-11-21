package com.cafetexpress.cafete.recycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.adapters.AdapterProducto
import com.cafetexpress.cafete.adapters.AdapterUsuario
import com.cafetexpress.cafete.model.productos
import com.cafetexpress.cafete.model.usuarios
import com.cafetexpress.cafete.sql.adminDB

class Recycler_usuario_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_usuario_)

        val rview=findViewById<RecyclerView>(R.id.rv_usr_list)
        val adapter= AdapterUsuario(this)
        rview.adapter=adapter

        rview.layoutManager= LinearLayoutManager(this) //Muestra los elementos en lista

        adapter.setList(ListaUsr())
    }
    fun ListaUsr():List<usuarios>{
        var usr = ArrayList<usuarios>()
        val database = adminDB(this)
        val cursor = database.consulta("select ncontrol, nombre, carrera, password from usuario")
        if(cursor!!.moveToFirst())do{
            val user = usuarios(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
                //cursor.getString(4)
            )

            usr.add(user)
        }
        while(cursor!!.moveToNext())
        cursor.close()
        return usr
    }
}
