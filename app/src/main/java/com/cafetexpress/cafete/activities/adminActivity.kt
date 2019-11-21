package com.cafetexpress.cafete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.recycler.Recycler_usuario_Activity
import kotlinx.android.synthetic.main.activity_admin.*

class adminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        btnadministrar.setOnClickListener { startActivity(Intent(this,adminProductoActivity::class.java))}
        btnusuario.setOnClickListener { startActivity(Intent(this,Recycler_usuario_Activity::class.java))}

    }
}
