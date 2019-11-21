package com.cafetexpress.cafete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cafetexpress.cafete.R
import com.cafetexpress.cafete.recycler.Recycler_producto_Activity
import com.cafetexpress.cafete.sql.adminDB
import kotlinx.android.synthetic.main.activity_admin_producto.*
import kotlinx.android.synthetic.main.activity_registro.*

class adminProductoActivity : AppCompatActivity() {
    var id: Int = 0
    var nombre: String = ""
    var precio: Double = 0.0
    var cantidad: Int = 0
    var tiempo: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_producto)
        etID.requestFocus()
    }

    fun buscar(v: View) {
        if (etID.text!!.isEmpty()) {// funcion que nos carga la informacion del producto a traves de su id
            Toast.makeText(this, "Ingrese el id del producto a buscar", Toast.LENGTH_SHORT).show()
            etID.requestFocus()
        } else {
            id = etID.text.toString().toInt()
            val admin = adminDB(this)
            val tupla =
                admin.consulta("Select id_producto, nombre, precio, cantidad, tiempo From producto WHERE id_producto=$id")
            if (tupla!!.moveToFirst()) {
                etNombre.setText(tupla.getString(1))
                etPrecio.setText(tupla.getString(2))
                etCantidad.setText(tupla.getString(3))
                etTiempo.setText(tupla.getString(4))
                btnAgregar.isEnabled = false
                btnActualizar.isEnabled = true
                btnEliminar.isEnabled = true

            } else {
                Toast.makeText(this, "No se exite el producto", Toast.LENGTH_SHORT).show()
                etID.requestFocus()
            }
        }
    }

    fun insertproducto(v: View) {
        if (etNombre.text!!.isEmpty()) {
            Toast.makeText(this, "Ingrese nombre del producto", Toast.LENGTH_SHORT).show()
            etNombre.requestFocus()

        } else if (etPrecio.text!!.isEmpty()) {
            Toast.makeText(this, "Ingrese el precio", Toast.LENGTH_SHORT).show()
            etPrecio.requestFocus()

        } else if (etCantidad.text!!.isEmpty()) {
            Toast.makeText(this, "Ingrese la cantidad disponible", Toast.LENGTH_SHORT).show()
            etCantidad.requestFocus()

        } else if (etTiempo.text!!.isEmpty()) {
            Toast.makeText(this, "Ingrese el tiempo de espera", Toast.LENGTH_SHORT).show()
            etTiempo.requestFocus()
        } else {
            //readbox()
            nombre = etNombre.text.toString()
            precio = etPrecio.text.toString().toDouble()
            cantidad = etCantidad.text.toString().toInt()
            tiempo = etTiempo.text.toString()
            val sentencia =
                "Insert Into producto(nombre, precio, cantidad, tiempo) Values('$nombre',$precio,$cantidad,'$tiempo')"
            val admin = adminDB(this)
            if (admin.Ejecuta(sentencia)) {
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
                clearbox()
            } else {
                Toast.makeText(this, "No se agrego el producto", Toast.LENGTH_SHORT).show();
                etNombre.requestFocus()
            }

        }
    }

    fun updateproduct(v: View) {
        if (etID.text!!.isEmpty() || etNombre.text!!.isEmpty() || etPrecio.text!!.isEmpty() || etCantidad.text!!.isEmpty() || etTiempo.text!!.isEmpty()) {
            Toast.makeText(this, "Falta informacion del producto", Toast.LENGTH_SHORT).show()
            etID.requestFocus()
        } else {
            readbox()
            val sentencia =
                "UPDATE producto SET nombre='$nombre',precio=$precio, cantidad=$cantidad, tiempo='$tiempo' WHERE id_producto=$id"
            val admin = adminDB(this)
            if (admin.Ejecuta(sentencia)) {
                Toast.makeText(this, "Producto Actualizado", Toast.LENGTH_SHORT).show()
                clearbox()
            } else {
                Toast.makeText(this, "No se actualizó el producto", Toast.LENGTH_SHORT).show()
                etID.requestFocus()
            }
        }
    }

    fun deleteproduct(v: View) {
        if (etID.text!!.isEmpty() || etNombre.text!!.isEmpty() || etPrecio.text!!.isEmpty() || etCantidad.text!!.isEmpty() || etTiempo.text!!.isEmpty()) {
            Toast.makeText(this, "Producto no registrado o falta información", Toast.LENGTH_SHORT).show();
            etID.requestFocus()
        } else {
            readbox()
            val sentencia = "DELETE FROM producto WHERE id_producto=$id"
            val admin = adminDB(this)
            if (admin.Ejecuta(sentencia)) {
                Toast.makeText(this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                clearbox()
            } else {
                Toast.makeText(this, "No se eliminó el producto", Toast.LENGTH_SHORT).show();
                etID.requestFocus()
            }

        }
    }

    fun Consultar(v: View){
        var actividad = Intent(this,Recycler_producto_Activity::class.java)
        startActivity(actividad)
    }


    fun readbox() {
        id = etID.text.toString().toInt()
        nombre = etNombre.text.toString()
        precio = etPrecio.text.toString().toDouble()
        cantidad = etCantidad.text.toString().toInt()
        tiempo = etTiempo.text.toString()
    }

    fun clearbox() {

        id = 0
        nombre = ""
        precio = 0.0
        cantidad = 0
        tiempo = ""
        etID.setText("")
        etNombre.setText("")
        etPrecio.setText("")
        etCantidad.setText("")
        etTiempo.setText("")
        btnAgregar.isEnabled = true
        btnActualizar.isEnabled = false
        btnEliminar.isEnabled = false
        etID.requestFocus()

    }
}
