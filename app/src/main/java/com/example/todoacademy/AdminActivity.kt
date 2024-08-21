package com.example.todoacademy

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_admin.*
import org.json.JSONObject

private lateinit var nombreC: EditText
    private lateinit var sesiones: EditText
    private lateinit var duracion: EditText
    private lateinit var modalidad: EditText
    private lateinit var certificador: EditText
    private lateinit var aprenderas: EditText
    private lateinit var objetivo: EditText
    private lateinit var spinner: Spinner
    private lateinit var dirigido:EditText

    private lateinit var agregarCurso:Button

    private lateinit var requestQueue: RequestQueue

    var URL = "http://192.168.3.37:3977/TodoAcademy/"

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


        nombreC=findViewById(R.id.etnombreC)
        sesiones=findViewById(R.id.sesionesC)
        duracion=findViewById(R.id.duracionC)
        modalidad=findViewById(R.id.modalidadC)
        certificador=findViewById(R.id.certificadorC)
        aprenderas=findViewById(R.id.aprenderasC)
        objetivo=findViewById(R.id.objetivoC)
        spinner=findViewById(R.id.categoriaSpinner)
        dirigido=findViewById(R.id.dirigidoC)

        agregarCurso=findViewById(R.id.agregarC)


        //Spinner
        spinner = findViewById<View>(R.id.categoriaSpinner) as Spinner
        val colores = arrayOf("ProgramadorenJava", "BasededatosOracle", "BasedeDatos", "DesarrollodeAplicacionesMoviles","ProgramadorenTecnologiasDiversas","ProgramaciondeVideojuegos","AdministraciondeProyectos")
        val adapter = ArrayAdapter(this, R.layout.spinner_item, colores)
        spinner.setAdapter(adapter)

        agregarCurso.setOnClickListener { registrarCurso() }

    }

    fun registrarCurso(){

        requestQueue= Volley.newRequestQueue(this)

        var nombre= nombreC.text.toString()
        var sesiones=sesiones.text.toString()
        var duracion= duracion.text.toString()
        var modalidad= modalidad.text.toString()
        var certificador= certificador.text.toString()
        var aprendera=aprenderas.text.toString()
        var objetivo= objetivo.text.toString()
        var dirigido= dirigido.text.toString()
        var categoria= spinner.selectedItem.toString()

        if (nombre.isNotEmpty()&&sesiones.isNotEmpty()&&duracion.isNotEmpty()&&modalidad.isNotEmpty()&&certificador.isNotEmpty()
            &&aprendera.isNotEmpty()&&objetivo.isNotEmpty()&&dirigido.isNotEmpty()&&categoria.isNotEmpty()){

            val arreglo= JSONObject()


            arreglo.put("nombreC", nombre)
            arreglo.put("sesiones", sesiones)
            arreglo.put("duracion",duracion)
            arreglo.put("modalidad", modalidad)
            arreglo.put("aprenderas", aprendera)
            arreglo.put("certificador", certificador)
            arreglo.put("objetivo", objetivo)
            arreglo.put("categoria", categoria)
            arreglo.put("dirigido", dirigido)
            arreglo.put("imageFile","empty.png")

            val volley1= JsonObjectRequest(Request.Method.POST, URL+"registroC", arreglo, Response.Listener {
                    response ->
                Toast.makeText(applicationContext,response.get("message").toString(),Toast.LENGTH_LONG).show()
            }, Response.ErrorListener {
                    error ->
                Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_LONG).show()
            })

            requestQueue.add(volley1)
        }else{
            Toast.makeText(this, "Faltan datos por llenar", Toast.LENGTH_SHORT).show()
        }
    }

}