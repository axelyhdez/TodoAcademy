package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_administrador1.*
import org.json.JSONObject

class administrador1 : AppCompatActivity() {

    private lateinit var agregar:Button
    private lateinit var verC:Button
    private lateinit var eliminarC:Button
    private lateinit var verU:Button
    private lateinit var editarU:Button
    private lateinit var categoria:Spinner
    private lateinit var nombreCurso:EditText


    private lateinit var requestQueue: RequestQueue

    var URL = "http://192.168.3.37:3977/TodoAcademy/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador1)

        Toast.makeText(this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show()

        agregar = findViewById(R.id.agregarCurso)
        verC = findViewById(R.id.verCurso)
        eliminarC = findViewById(R.id.eliminarCurso)
        verU = findViewById(R.id.verUsuarios)
        editarU = findViewById(R.id.editarU)

        nombreCurso = findViewById(R.id.nombreCurso)


        //Spinner
        categoria = findViewById<View>(R.id.categoriaSpin) as Spinner
        val colores = arrayOf(
            "ProgramadorenJava",
            "BasededatosOracle",
            "BasedeDatos",
            "DesarrollodeAplicacionesMoviles",
            "ProgramadorenTecnologiasDiversas",
            "ProgramaciondeVideojuegos",
            "AdministraciondeProyectos"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_item, colores)
        categoria.setAdapter(adapter)


        agregar.setOnClickListener {
            val intent1: Intent = Intent(applicationContext, AdminActivity::class.java)
            intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent1)
        }

        verC.setOnClickListener {
            val i = Intent(this, Cursos_Categorias::class.java)
            i.putExtra("name", categoria.selectedItem.toString())
            i.putExtra("title", "Cursos por categoria")
            startActivity(i)
        }

        eliminarC.setOnClickListener {
            requestQueue= Volley.newRequestQueue(this)

            val nombre= nombreCurso.text.toString()

            if (nombre.isEmpty()){
                Toast.makeText(this, "Falta llena el nombre del curso", Toast.LENGTH_SHORT).show()
            }else{
                val arreglo= JSONObject()

                arreglo.put("nombreC", nombre)

                val volley1= JsonObjectRequest(Request.Method.POST, URL+"eliminarC", arreglo, {
                        response ->
                    Toast.makeText(applicationContext,response.get("message").toString(),Toast.LENGTH_LONG).show()
                }, {
                        error ->
                    Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_LONG).show()
                })
                requestQueue.add(volley1)
            }
        }



    }
}