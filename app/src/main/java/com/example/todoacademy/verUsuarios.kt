package com.example.todoacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue

class verUsuarios : AppCompatActivity() {

    //Volley
    var URL = "http://192.168.3.37:3977/TodoAcademy/"
    private lateinit var requestQueue: RequestQueue

    //RecyclerView2
    var datosList= arrayListOf<Usuarios_datos>()
    var recyclerView: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_usuarios)

        recyclerView=findViewById(R.id.listaU)
    }
}