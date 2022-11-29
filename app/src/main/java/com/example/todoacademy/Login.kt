package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var etContraseña: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Enlaces
        etCorreo=findViewById(R.id.etCorreo)
        etContraseña=findViewById(R.id.etContraseña)
        btnEntrar=findViewById(R.id.btnEntrar)
        btnRegistrar=findViewById(R.id.btnRegistrar)

        btnEntrar.setOnClickListener {Acceder()}
        btnRegistrar.setOnClickListener { startActivity(Intent(this,Registro1::class.java)) }
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
}