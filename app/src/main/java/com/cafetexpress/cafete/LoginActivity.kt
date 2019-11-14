package com.cafetexpress.cafete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cafetexpress.cafete.sql.adminDB
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    private lateinit var  scontrol: String
    private lateinit var snip: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etNoControl.requestFocus()
        btnLogin.setOnClickListener {
            if (etNoControl.text.isEmpty() || etPwd.text.isEmpty()){
                Toast.makeText(this,"complete",Toast.LENGTH_SHORT).show()
                etNoControl.requestFocus()
            }else{
                val nc = etNoControl.text.toString()
                val psw = etPwd.text.toString()
                val admin = adminDB(this)
                val result = admin.consulta("Select ncontrol,password From usuario")
                if(result!!.moveToFirst()){
                    scontrol = result.getString(0)
                    snip = result.getString(1)
                    result.close()
                    admin.close()
                    if (nc == scontrol && snip == psw) {
                        if (nc == "0") startActivity(Intent(this,MainActivity::class.java))
                        else startActivity(Intent(this,Recycler_usuario_Activity::class.java))
                    }
                }else Toast.makeText(this,"Error en las credenciales",Toast.LENGTH_SHORT).show()
            }
        }
        tvNoRegistro.setOnClickListener {
            startActivity(Intent(this,registroActivity::class.java))
        }
    }
}
