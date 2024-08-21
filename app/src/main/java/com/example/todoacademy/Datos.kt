package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.datepicker.MaterialDatePicker
import org.json.JSONObject

class Datos : AppCompatActivity() {

    private lateinit var etNombre: TextView
    private lateinit var etApellido: TextView
    private lateinit var etApellidoP: TextView
    private lateinit var etTelefono: TextView
    private lateinit var etEscuela: TextView
    private lateinit var btnSig: Button
    private lateinit var btnFechaInicio: Button
    private lateinit var btnFechaFin: Button

    //URL de la API
    var URL = "http://192.168.3.37:3977/TodoAcademy/actualizarU/"
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        requestQueue = Volley.newRequestQueue(applicationContext);

        etNombre=findViewById(R.id.etNombre1)
        etApellido=findViewById(R.id.etApellido1)
        etApellidoP=findViewById(R.id.etApellidoP1)
        etTelefono=findViewById(R.id.etTelefono1)
        etEscuela=findViewById(R.id.etEscuela1)

        btnSig=findViewById(R.id.btnSigD)
        btnFechaInicio=findViewById(R.id.btnFechaInicio1)
        btnFechaFin=findViewById(R.id.btnFechaFin1)

        val DateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        DateBuilder.setTitleText("Selecciona una fecha")
        val DatePicker = DateBuilder.build()
        val DatePicker1 = DateBuilder.build()

        btnFechaInicio.setOnClickListener {
            DatePicker.show(supportFragmentManager, DatePicker.toString())
        }
        DatePicker.addOnPositiveButtonClickListener {
            TodoAcademy.preferencias.setfechaI(DatePicker.headerText)
        }

        btnFechaFin.setOnClickListener {
            DatePicker1.show(supportFragmentManager, DatePicker1.toString())
        }
        DatePicker1.addOnPositiveButtonClickListener {
            TodoAcademy.preferencias.setfechaF(DatePicker1.headerText)
        }

        btnSig.setOnClickListener{

            val nombre=etNombre.text.toString()
            val apellido=etApellido.text.toString()
            val apellidoP=etApellidoP.text.toString()
            val telefono=etTelefono.text.toString()
            val escuela=etEscuela.text.toString()

            val name:String
            val lastname:String
            val Slastname:String
            val tel:String
            val school:String

            if (nombre==TodoAcademy.preferencias.getNombre()||nombre.isEmpty()){
                name=TodoAcademy.preferencias.getNombre();
            }else{
                name=nombre
            }

            if (apellido==TodoAcademy.preferencias.getApellido()||apellido.isEmpty()){
                lastname=TodoAcademy.preferencias.getApellido();
            }else{
                lastname=apellido
            }

            if (apellidoP==TodoAcademy.preferencias.getApellidoP()||apellidoP.isEmpty()){
                Slastname=TodoAcademy.preferencias.getApellidoP();
            }else{
                Slastname=apellidoP
            }

            if (telefono==TodoAcademy.preferencias.getTelefono()||telefono.isEmpty()){
                tel=TodoAcademy.preferencias.getTelefono();
            }else{
                tel=telefono
            }

            if (escuela==TodoAcademy.preferencias.getEscuela()||escuela.isEmpty()){
                school=TodoAcademy.preferencias.getEscuela();
            }else{
                school=escuela
            }

            TodoAcademy.preferencias.setUser(name,lastname,Slastname,tel,school)

            val intent1: Intent = Intent(applicationContext, MainActivity::class.java).apply {
                actualizarVolley()
            }
            intent1.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent1)

        }

    }

    private fun actualizarVolley(){

        val id=TodoAcademy.preferencias.getObjId()

        val newURL=URL+id

        val nombre=TodoAcademy.preferencias.getNombre()
        val apellido=TodoAcademy.preferencias.getApellido()
        val apellidoP=TodoAcademy.preferencias.getApellidoP()
        val telefono=TodoAcademy.preferencias.getTelefono()
        val escuela=TodoAcademy.preferencias.getEscuela()

        val inicio=TodoAcademy.preferencias.getfechaI()
        val fin=TodoAcademy.preferencias.getfechaF()

        val arreglo= JSONObject()
        arreglo.put("nombre", nombre)
        arreglo.put("apellido", apellido)
        arreglo.put("apellidoP", apellidoP)
        arreglo.put("telefono", telefono)
        arreglo.put("escuela", escuela)
        arreglo.put("fechaI", inicio)
        arreglo.put("fechaF", fin)


        val volley1= JsonObjectRequest(Request.Method.PUT, newURL, arreglo, {
                response ->
            Toast.makeText(applicationContext,"Datos actualizados", Toast.LENGTH_LONG).show()
        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }
}