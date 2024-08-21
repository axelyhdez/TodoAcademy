package com.example.todoacademy

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Curso : AppCompatActivity() {

    private lateinit var tvnombreC:TextView
    private lateinit var tvSesiones:TextView
    private lateinit var tvDuracion:TextView
    private lateinit var tvModalidad:TextView
    private lateinit var tvCertificador:TextView
    private lateinit var tvDirigido:TextView
    private lateinit var tvAprenderas:TextView
    private lateinit var tvObjetivo:TextView

    //URL de la API
    var URL = "http://192.168.3.37:3977/TodoAcademy/getU"
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso)

        requestQueue = Volley.newRequestQueue(applicationContext);

        tvnombreC=findViewById(R.id.tvnombreC)
        tvSesiones=findViewById(R.id.tvSesiones)
        tvDuracion=findViewById(R.id.tvDuracion)
        tvModalidad=findViewById(R.id.tvModalidad)
        tvCertificador=findViewById(R.id.tvCertificador)
        tvDirigido=findViewById(R.id.tvDirigido)
        tvAprenderas=findViewById(R.id.tvAprenderas)
        tvObjetivo=findViewById(R.id.tvObjetivo)

        datosVolley()
    }

    fun datosVolley(){

        val intent1 = intent
        val nombreC: String = intent1.getStringExtra("name").toString()

        val arreglo= JSONObject()
        arreglo.put("nombreC", nombreC)

        val volley1= JsonObjectRequest(Request.Method.POST, URL, arreglo, {
                response ->

            val objid=response.get("id").toString()
            val sesiones=response.get("sesiones").toString()
            val duracion=response.get("duracion").toString()
            val modalidad=response.get("modalidad").toString()
            val dirigido=response.get("dirigido").toString()
            val certificador=response.get("certificador").toString()
            val aprenderas=response.get("aprenderas").toString()
            val objetivo=response.get("objetivo").toString()

            tvnombreC.setText(nombreC)
            tvSesiones.setText(sesiones)
            tvDuracion.setText(duracion)
            tvModalidad.setText(modalidad)
            tvDirigido.setText(dirigido)
            tvCertificador.setText(certificador)
            tvAprenderas.setText(aprenderas)
            tvObjetivo.setText(objetivo)

        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }

}