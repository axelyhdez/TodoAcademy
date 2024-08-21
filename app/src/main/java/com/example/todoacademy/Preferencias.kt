package com.example.todoacademy

import android.content.Context

class Preferencias(val context:Context) {

    val SharedName="pref1"

    val sp=context.getSharedPreferences(SharedName, 0)

    //Guardar valores
    fun setSesion(correo:String){
        sp.edit().putString("correo", correo).apply()
        sp.edit().putBoolean("sesion", true).apply()
    }
    fun setUser(nombre:String, apellido:String, apellidop: String, telefono:String, escuela:String){
        sp.edit().putString("nombre", nombre).apply()
        sp.edit().putString("apellido", apellido).apply()
        sp.edit().putString("apellidop", apellidop).apply()
        sp.edit().putString("telefono", telefono).apply()
        sp.edit().putString("escuela", escuela).apply()
    }
    fun setfechaI(fechaI:String){
        sp.edit().putString("fechaI",fechaI).apply()
    }
    fun setfechaF(fechaF:String){
        sp.edit().putString("fechaF",fechaF).apply()
    }
    fun setCorreo(email:String){
        sp.edit().putString("correo", email).apply()
    }
    fun setObjId(objId:String){
        sp.edit().putString("ObjId",objId).apply()
    }

    //Consultar valores
    fun getCorreo():String{
        val correo=sp.getString("correo", "")!!
        return correo
    }
    fun getSesion():Boolean{
        val sesion=sp.getBoolean("sesion", false)!!
        return sesion
    }
    fun getNombre():String{
        val nombre=sp.getString("nombre", "")!!
        return nombre
    }
    fun getApellido():String{
        val apellido=sp.getString("apellido", "")!!
        return apellido
    }
    fun getApellidoP():String{
        val apellidop=sp.getString("apellidop", "")!!
        return apellidop
    }
    fun getTelefono():String{
        val telefono=sp.getString("telefono", "")!!
        return telefono
    }
    fun getEscuela():String{
        val escuela=sp.getString("escuela", "")!!
        return escuela
    }
    fun getfechaI():String{
        val fechaIn=sp.getString("fechaI", "")!!
        return fechaIn
    }
    fun getfechaF():String{
        val fechaF=sp.getString("fechaF", "")!!
        return fechaF
    }
    fun getObjId():String{
        val Objid=sp.getString("ObjId","")!!
        return Objid
    }


    //Preferencias foto de perfil
    fun setFoto(name: String?){
        sp.edit().putString("fotoPerfil",name).apply()
    }
    fun getFoto(): String? {
        val name=sp.getString("fotoPerfil","empty.jpg")!!
        return name
    }

    //Preferencias foto pago
    fun setFotoP(nameP: String?){
        sp.edit().putString("fotoPago",nameP).apply()
    }
    fun getFotoP(): String? {
        val nameP=sp.getString("fotoPago","vacio.pjg")!!
        return nameP
    }

    //Preferencias pago
    fun setPago(nameP: String?){
        sp.edit().putString("Pago",nameP).apply()
    }
    fun getPago(): String? {
        val nameP=sp.getString("Pago","Sin confirmar")!!
        return nameP
    }


    //Valor de Sesión
    fun exitSesion(){
        sp.edit().putBoolean("sesion", false).apply()
    }

}