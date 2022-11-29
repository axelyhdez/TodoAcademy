package com.example.todoacademy

import android.content.Context

class Preferencias(val context:Context) {

    val SharedName="pref1"

    val sp=context.getSharedPreferences(SharedName, 0)

    fun setSesion(correo:String){
        sp.edit().putString("correo", correo).apply()
        sp.edit().putBoolean("sesion", true).apply()
    }

    fun setUser(nombre:String, apellido:String, apellidop: String, telefono:String){
        sp.edit().putString("nombre", nombre).apply()
        sp.edit().putString("apellido", apellido).apply()
        sp.edit().putString("apellidop", apellidop).apply()
        sp.edit().putString("telefono", telefono).apply()
    }


    fun exitSesion(){
        sp.edit().putBoolean("sesion", false).apply()
    }

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
}