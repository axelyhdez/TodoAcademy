package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class Login : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var etContraseña: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnRegistrar: Button
    private lateinit var admin:Button

    //URL de la API
    var URL = "http://192.168.3.37:3977/TodoAcademy/accesoU"
    private lateinit var requestQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        requestQueue = Volley.newRequestQueue(applicationContext);

        //Enlaces
        etCorreo=findViewById(R.id.etCorreo)
        etContraseña=findViewById(R.id.etContraseña)
        btnEntrar=findViewById(R.id.btnEntrar)
        btnRegistrar=findViewById(R.id.btnRegistrar)
        admin=findViewById(R.id.btnAdmin)

        btnEntrar.setOnClickListener {Acceder()}
        btnRegistrar.setOnClickListener { startActivity(Intent(this,Registro1::class.java)) }
        btnAdmin.setOnClickListener { admin() }
    }

    private fun Acceder(){
        var email= etCorreo.text.toString()
        var pass= etContraseña.text.toString()

        if(email.isNotEmpty()&& pass.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        TodoAcademy.preferencias.setSesion(email)
                        val intent1: Intent = Intent(applicationContext, MainActivity::class.java).apply {
                            putExtra("email", it.result?.user?.email?: "")
                        }
                        loginVolley()
                        intent1.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent1)
                    }else{
                        verDialog("Error", "Error de autenticación de usuario")
                    }
                }
        }else{
            verDialog("Advertencia", "Falta algún campo")
        }
    }


    private fun verDialog(titulo:String, mensaje:String){
        var builder= AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }


    private fun loginVolley(){

        val correo=TodoAcademy.preferencias.getCorreo()
        val arreglo= JSONObject()
        arreglo.put("correo", correo)

        val volley1= JsonObjectRequest(Request.Method.POST, URL, arreglo, {
                response ->

            val objid=response.get("id").toString()
            val nombre=response.get("nombre").toString()
            val apellido=response.get("apellido").toString()
            val apellidoP=response.get("apellidoM").toString()
            val telefono=response.get("telefono").toString()
            val escuela=response.get("escuela").toString()
            val fechaI=response.get("fechaI").toString()
            val fechaF=response.get("fechaF").toString()

            TodoAcademy.preferencias.setObjId(objid)
            TodoAcademy.preferencias.setUser(nombre,apellido,apellidoP,telefono,escuela)
            TodoAcademy.preferencias.setfechaI(fechaI)
            TodoAcademy.preferencias.setfechaF(fechaF)
            Toast.makeText(applicationContext,"Sesión Iniciada", Toast.LENGTH_SHORT).show()
        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }

    private fun admin(){
        var email= etCorreo.text.toString()
        var pass= etContraseña.text.toString()

        /*
        if(email.isNotEmpty()&& pass.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent1: Intent = Intent(applicationContext, AdminActivity::class.java)
                        intent1.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent1)
                    }else{
                        verDialog("Error", "Error de autenticación de usuario")
                    }
                }
        }else{
            verDialog("Advertencia", "Falta algún campo")
        }*/

        if(email=="develop@gmail.com"&&pass=="qwerty1"){
            val intent1: Intent = Intent(applicationContext, administrador1::class.java)
            intent1.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent1)
        }else{
            verDialog("Error", "No eres administrador")
        }
    }


}