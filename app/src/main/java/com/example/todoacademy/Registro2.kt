package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Registro2 : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var etPass: EditText
    private lateinit var btnRegistro: Button
    private lateinit var btnIniciaSesionR2: Button

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

    }

    private fun Registrar(){
        var email= etCorreo.text.toString()
        var pass= etPass.text.toString()

        if(email.isNotEmpty()&&pass.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent1: Intent = Intent(applicationContext, Registro::class.java).apply {
                            putExtra("email", it.result?.user?.email?: "")
                        }
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


}