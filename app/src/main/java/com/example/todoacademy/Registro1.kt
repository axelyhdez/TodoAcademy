package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.todoacademy.TodoAcademy.Companion.preferencias
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date

class Registro1 : AppCompatActivity() {

    private lateinit var etNombre:TextView
    private lateinit var etApellido:TextView
    private lateinit var etApellidoP:TextView
    private lateinit var etTelefono:TextView
    private lateinit var etEscuela:TextView
    private lateinit var btnSig:Button
    private lateinit var btnIniciaSesionR1:Button
    private lateinit var btnFechaInicio:Button
    private lateinit var btnFechaFin:Button


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
        btnFechaInicio=findViewById(R.id.btnFechaInicio)
        btnFechaFin=findViewById(R.id.btnFechaFin)

        val DateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        DateBuilder.setTitleText("Selecciona una fecha")
        val DatePicker = DateBuilder.build()
        val DatePicker1 = DateBuilder.build()



        btnFechaInicio.setOnClickListener {
            DatePicker.show(supportFragmentManager, DatePicker.toString())
        }
        DatePicker.addOnPositiveButtonClickListener {
            preferencias.setfechaI(DatePicker.headerText)
        }

        btnFechaFin.setOnClickListener {
            DatePicker1.show(supportFragmentManager, DatePicker1.toString())
        }
        DatePicker1.addOnPositiveButtonClickListener {
            preferencias.setfechaF(DatePicker1.headerText)
        }


        btnSig.setOnClickListener{

            val nombre=etNombre.text.toString()
            val apellido=etApellido.text.toString()
            val apellidoP=etApellidoP.text.toString()
            val telefono=etTelefono.text.toString()
            val escuela=etEscuela.text.toString()


            if (nombre.isEmpty()||apellido.isEmpty()||apellidoP.isEmpty()||telefono.isEmpty()||escuela.isEmpty()){
                Toast.makeText(this, "Falta llenar datos", Toast.LENGTH_SHORT).show()
            }else{
                preferencias.setUser(nombre,apellido,apellidoP,telefono,escuela)
                startActivity(Intent(this,Registro2::class.java))
            }
        }

        btnIniciaSesionR1.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }

    }
}