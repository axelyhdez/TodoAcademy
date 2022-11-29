package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.todoacademy.TodoAcademy.Companion.preferencias

class Registro1 : AppCompatActivity() {

    private lateinit var etNombre:TextView
    private lateinit var etApellido:TextView
    private lateinit var etApellidoP:TextView
    private lateinit var etTelefono:TextView
    private lateinit var etEscuela:TextView
    private lateinit var btnSig:Button
    private lateinit var btnIniciaSesionR1:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro1)

        etNombre=findViewById(R.id.etNombre)
        etApellido=findViewById(R.id.etApellido)
        etApellidoP=findViewById(R.id.etApellidoP)
        etTelefono=findViewById(R.id.etTelefono)
        etEscuela=findViewById(R.id.etEscuela)

        btnSig=findViewById(R.id.btnSig)
        btnIniciaSesionR1=findViewById(R.id.btnIniciaSesionR1)


        btnSig.setOnClickListener{

            val nombre=etNombre.text.toString()
            val apellido=etApellido.text.toString()
            val apellidoP=etApellidoP.text.toString()
            val telefono=etTelefono.text.toString()

            if (nombre.isEmpty()||apellido.isEmpty()||apellidoP.isEmpty()||telefono.isEmpty()){
                Toast.makeText(this, "Falta llenar datos", Toast.LENGTH_SHORT).show()
            }else{
                preferencias.setUser(nombre,apellido,apellidoP,telefono)
                startActivity(Intent(this,Registro2::class.java))
            }
        }

        btnIniciaSesionR1.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
}