package com.example.todoacademy

import android.app.Application

class TodoAcademy:Application() {
    //Con el sig metodo todos pueden acceder
    companion object{
        lateinit var preferencias:Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias=Preferencias(applicationContext)
    }
}