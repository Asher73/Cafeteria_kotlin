package com.cafetexpress.cafete.model

class usuarios(ncontrol: String, carrera: String, nombre: String, password: String) {
    var noc: String = ""
    var nomb: String = ""
    var car: String = ""
    var pwd: String = ""
    //var tiempo: String = ""

    init {
        this.noc = ncontrol
        this.nomb = nombre
        this.car = carrera
        this.pwd = password
        //this.tiempo = tiempo
    }
}