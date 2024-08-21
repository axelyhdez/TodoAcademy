package com.example.todoacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONObject

class Registro2 : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var etPass: EditText
    private lateinit var btnRegistro: Button
    private lateinit var btnIniciaSesionR2: Button


    private lateinit var requestQueue: RequestQueue
    //URL de la API
    var URL = "http://192.168.3.37:3977/TodoAcademy/registroU"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro2)

        //Enlaces
        etCorreo=findViewById(R.id.etCorreo)
        etPass=findViewById(R.id.etContraseña)

        btnRegistro=findViewById(R.id.btnRegistro)
        btnIniciaSesionR2=findViewById(R.id.btnIniciaSesionR2)

        btnRegistro.setOnClickListener {Registrar()}
        btnIniciaSesionR2.setOnClickListener { startActivity(Intent(this,Login::class.java)) }

        requestQueue=Volley.newRequestQueue(this)
    }

    private fun Registrar(){
        var correo= etCorreo.text.toString()
        var pass= etPass.text.toString()
        if(correo.isNotEmpty()&&pass.isNotEmpty()){
            TodoAcademy.preferencias.setCorreo(correo)
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(correo,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent1: Intent = Intent(applicationContext, Login::class.java).apply {
                            putExtra("correo", it.result?.user?.email?: "")//Revisar funcionamiento
                        }
                        intent1.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        registroVolley()
                        startActivity(intent1)
                    }else{
                        verDialog("Error", "Error de autenticación de usuario")
                    }
                }
        }else{
            verDialog("Advertencia", "Se deben llenar todos los campos")
        }
    }

    private fun verDialog(titulo:String, mensaje:String){
        var builder= AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    private fun registroVolley(){
        val correo=TodoAcademy.preferencias.getCorreo()
        val nombre=TodoAcademy.preferencias.getNombre()
        val apellido=TodoAcademy.preferencias.getApellido()
        val apellidoP=TodoAcademy.preferencias.getApellidoP()
        val telefono=TodoAcademy.preferencias.getTelefono()
        val escuela=TodoAcademy.preferencias.getEscuela()
        val fechaI=TodoAcademy.preferencias.getfechaI()
        val fechaF=TodoAcademy.preferencias.getfechaF()

        val arreglo=JSONObject()

        arreglo.put("correo", correo)
        arreglo.put("nombre", nombre)
        arreglo.put("apellido", apellido)
        arreglo.put("apellidoM",apellidoP)
        arreglo.put("telefono", telefono)
        arreglo.put("escuela", escuela)
        arreglo.put("fechaI", fechaI)
        arreglo.put("fechaF", fechaF)

        val volley1=JsonObjectRequest(Request.Method.POST, URL, arreglo,Response.Listener {
            response ->
            Toast.makeText(applicationContext,response.get("message").toString(),Toast.LENGTH_LONG).show()
        },Response.ErrorListener {
            error ->
            Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }
}