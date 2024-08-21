package com.example.todoacademy

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.todoacademy.databinding.ActivityCursosCategoriasBinding
import org.json.JSONObject

class Cursos_Categorias : AppCompatActivity() {

    private lateinit var titulo:TextView


    //Volley
    var URL = "http://192.168.3.37:3977/TodoAcademy/getCat/"
    private lateinit var requestQueue: RequestQueue

    //RecyclerView2
    var datosList= arrayListOf<Cursos_datos>()
    var recyclerView: RecyclerView?=null


    //RecyclerView
    private lateinit var binding:ActivityCursosCategoriasBinding
    private var listaCursos: MutableList<Cursos_datos> = mutableListOf()
    private lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCursosCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView=findViewById(R.id.lista)

        titulo=findViewById(R.id.tituloCategoria)

        requestQueue = Volley.newRequestQueue(applicationContext);
        datosVolley()
    }

    fun datosVolley(){

        val intent1 = intent
        val categoria: String = intent1.getStringExtra("name").toString()
        val title: String=intent1.getStringExtra("title").toString()

        titulo.setText(title)

        val volley1 = JsonObjectRequest(Request.Method.GET, URL+categoria, null, { res->

            Log.d("volley",res.toString())
            val array=res.getJSONArray("curso")
            for (i in 0 until  array.length()){
                val obj=array.getJSONObject(i)

                val datos=Cursos_datos(
                    obj.getString("nombreC"),
                    obj.getString("duracion"),
                    obj.getString("modalidad"),
                    obj.getString("imageFile")
                )

                datosList.add(datos)
                recyclerView?.layoutManager=LinearLayoutManager(this)
                recyclerView?.adapter=CursosAdapter(datosList, this)

                var adapter=CursosAdapter(datosList, this)
                recyclerView?.adapter=adapter
                adapter.setOnItemClickListener(object : CursosAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val i = Intent(this@Cursos_Categorias, Curso::class.java)
                        i.putExtra("name", datosList[position].nombreC)
                        startActivity(i)
                    }

                })

            }

        },{err->
            Log.d("Volley Error", err.message.toString())
        })

        requestQueue.add(volley1)
    }
}