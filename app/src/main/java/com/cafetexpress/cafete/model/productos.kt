package com.cafetexpress.cafete.model

class productos (id_producto:Int,nombre:String,precio:Double,cantidad: Int,tiempo:String){
    var id :Int = 0
    var nombre :String = ""
    var precio :Double = 0.0
    var cantidad:Int = 0
    var tiempo:String = ""

    init {
        this.id = id_producto
        this.nombre = nombre
        this.precio = precio
        this.cantidad = cantidad
        this.tiempo = tiempo
    }
}