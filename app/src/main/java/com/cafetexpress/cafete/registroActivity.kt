package com.cafetexpress.cafete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cafetexpress.cafete.sql.adminDB
import kotlinx.android.synthetic.main.activity_registro.*

class registroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        etncontrol.requestFocus()
        btnregistro.setOnClickListener {
            if(etncontrol.text.isEmpty() || etpsw.text.isEmpty()){
                Toast.makeText(this,"No deje campos en blanco",Toast.LENGTH_SHORT).show()
                etncontrol.requestFocus()
            }else {
                val ncontrol = etncontrol.text.toString()
                val nombre = etnombre.text.toString()
                val carrera = etcarrera.text.toString()
                val pwd = etpsw.text.toString()
                val pw2 = etpsw2.text.toString()
                if (pwd == pw2){
                    val admin = adminDB(this)
                    val sentencia = "Insert Into usuario(ncontrol,nombre,carrera,password) Values('$ncontrol','$ncontrol','$carrera','$pwd')"
                    val result = admin.Ejecuta(sentencia)
                    Toast.makeText(this,"Accion exitosa",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }else Toast.makeText(this,"No coincide el pasword",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
