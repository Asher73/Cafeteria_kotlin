package com.cafetexpress.cafete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.recycler.Recycler_usuario_Activity
import com.cafetexpress.cafete.sql.adminDB
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var  scontrol: String
    private lateinit var snip: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etNoControl.requestFocus()

        supportActionBar!!.hide()

        btnLogin.setOnClickListener {
            if (etNoControl.text!!.isEmpty() || etPwd.text!!.isEmpty()){
                //Toast.makeText(this,"complete los campos",Toast.LENGTH_SHORT).show()
                Snackbar.make(it,"Complete los campos",Snackbar.LENGTH_LONG).show()
                //snack.show()
                etNoControl.requestFocus()
            }else{
                val nc = etNoControl.text.toString()
                val psw = etPwd.text.toString()
                if(nc == "0" && psw == "000") startActivity(Intent(this,
                    adminActivity::class.java))
                else{

                    val admin = adminDB(this)
                    val result = admin.consulta("Select ncontrol, password From usuario")
                    if(result!!.moveToFirst()){
                        scontrol = result.getString(0)
                        snip = result.getString(1)
                        result.close()
                        admin.close()

                        if (nc == scontrol && snip == psw)  startActivity(Intent(this,
                            Recycler_usuario_Activity::class.java))
                    }else Snackbar.make(it,"Error en las credenciales",Snackbar.LENGTH_LONG).show()
                }
            }
        }
        tvNoRegistro.setOnClickListener {
            startActivity(Intent(this, registroActivity::class.java))
        }
    }
}

