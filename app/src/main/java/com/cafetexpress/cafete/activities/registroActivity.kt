package com.cafetexpress.cafete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.address.address
import com.cafetexpress.cafete.sql.adminDB
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import org.json.JSONObject


class registroActivity : AppCompatActivity() {

    val wsInsertar = address.IP + "servicescafe/user/InsertarAlumno.php"

    var ncontrol:String=""
    //var carrera:String=""
    private var spinnercarrera: Spinner? = null
    var nombre:String=""
    var pwd:String=""
    var pwd2:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        supportActionBar!!.hide()

        spinnercarrera = findViewById(R.id.spinnercarrera) as Spinner

        etncontrol.requestFocus()
        btnregistro.setOnClickListener {

            val carrera = spinnercarrera?.selectedItem.toString()

            if (etncontrol.text!!.isEmpty()) {
                //Toast.makeText(this, "No. de control requerido", Toast.LENGTH_SHORT).show()
                Snackbar.make(root_layout, "No. de control requerido", Snackbar.LENGTH_SHORT).show()
                etncontrol.requestFocus()

            } else if (etnombre.text!!.isEmpty()) {
                //Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show()
                Snackbar.make(root_layout, "Ingrese nombre", Snackbar.LENGTH_SHORT).show()
                etnombre.requestFocus()

            } else if (carrera=="Carrera") {
                //Toast.makeText(this, "Ingrese carrera", Toast.LENGTH_SHORT).show()
                Snackbar.make(root_layout, "Seleccione carrera", Snackbar.LENGTH_SHORT).show()

                //etcarrera.requestFocus()

            } else if (etpsw.text!!.isEmpty()) {
                //Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
                Snackbar.make(root_layout, "Ingrese una contraseña", Snackbar.LENGTH_SHORT).show()
                etpsw.requestFocus()
            } else if (etpsw2.text!!.isEmpty()) {
                //Toast.makeText(this, "Confirme su contraseña", Toast.LENGTH_SHORT).show()
                Snackbar.make(root_layout, "Confirme su contraseña", Snackbar.LENGTH_SHORT).show()

                etpsw2.requestFocus()
            }else{

            getValues() //funcion que obtiene los valores de las cajas de texto
            if(pwd == pwd2){

                var jsonEntrada = JSONObject()

                jsonEntrada.put("ncontrol", ncontrol)
                jsonEntrada.put("carrera",carrera)
                jsonEntrada.put("nombre", nombre)
                jsonEntrada.put("password",pwd)

                sendRequest(wsInsertar,jsonEntrada)

                clearFields()
                //startActivity(Intent(this, LoginActivity::class.java))
            }
            else {
                Snackbar.make(root_layout, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show()
            }
            }

                /*if (pwd == pw2) {
                    val admin = adminDB(this)
                    val sentencia =
                        "Insert Into usuario(ncontrol, carrera, nombre, password) Values('$ncontrol','$carrera','$nombre','$pwd')"
                    val result = admin.Ejecuta(sentencia)
                    Snackbar.make(root_layout, "Accion exitosa", Snackbar.LENGTH_SHORT).show()
                    //Toast.makeText(this, "Accion exitosa", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    //Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    Snackbar.make(root_layout, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show()

                }*/
            }
        }

    fun sendRequest(wsUrl:String, jsonEntrada: JSONObject) {

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsUrl, jsonEntrada,
            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                Toast.makeText(this, "Success: ${succ} Message:${msg} ", Toast.LENGTH_LONG).show();
                //Snackbar.make(root_layout,"Success: $succ Message:$msg",Snackbar.LENGTH_LONG).show()


                startActivity(Intent(this, LoginActivity::class.java))
            },
            Response.ErrorListener { error ->
                Snackbar.make(root_layout,"${error.message}",Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show();
                Log.d("ERROR", "${error.message}")
                Snackbar.make(root_layout,"Error Capa 8 WS u.u",Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "API: Error de capa 8 WS):", Toast.LENGTH_LONG).show();
            }
        )
        //Es para agregar las peticiones a la cola
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun getValues(){
        ncontrol = etncontrol.text.toString()
        //carrera = etcarrera.text.toString()
        nombre = etnombre.text.toString()
        pwd = etpsw.text.toString()
        pwd2 = etpsw2.text.toString()
    }
    fun clearFields(){
        ncontrol=""
        //carrera=""
        nombre=""
        pwd=""
        pwd2=""
        etncontrol.setText("")
        //etcarrera.setText("")
        etnombre.setText("")
        etpsw.setText("")
        etpsw2.setText("")
    }
}