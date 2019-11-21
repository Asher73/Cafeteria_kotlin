package com.cafetexpress.cafete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.recycler.Recycler_producto_Activity
import com.cafetexpress.cafete.sql.adminDB

class MainActivity : AppCompatActivity() {
    private lateinit var  scontrol: String
    private lateinit var snip: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actividad = intent
        if(actividad != null && actividad.hasExtra("ncontrol") && actividad.hasExtra("password")){
            scontrol = actividad.getStringExtra("ncontrol")
            snip = actividad.getStringExtra("password")
        }else{
            val admin = adminDB(this)
            val result = admin.consulta("Select ncontrol, password From usuario")
            if(result!!.moveToFirst()){
                scontrol = result.getString(0)
                snip = result.getString(1)
                result.close()
                admin.close()
            }else startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
