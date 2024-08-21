package com.example.todoacademy

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.todoacademy.TodoAcademy.Companion.preferencias
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import org.json.JSONObject

import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(), OnFragmentActionsListener{

    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var requestQueue: RequestQueue


    val pickMedia=registerForActivityResult(PickVisualMedia()){ uri->
        if (uri!=null){
            val bitmap: Bitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
            if (bitmap!=null){

                subirFotoPago(encodeImage(bitmap))
                getNombreFotoPago()
                //setFotoPago(preferencias.getFotoP().toString())

            }else{
                Toast.makeText(this, "Error, intente de nuevo", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Error, intente de nuevo", Toast.LENGTH_SHORT).show()
        }
    }

    val pickPerfil=registerForActivityResult(PickVisualMedia()){uri->
        if (uri!=null){
            val bitmap: Bitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
            if (bitmap!=null){

                Toast.makeText(this, "La imagen tardará en cargar", Toast.LENGTH_SHORT).show()
                subirFotoPerfil(encodeImage(bitmap))
                getNombreFotoPerfil()
                setFotoPerfil(preferencias.getFoto().toString())

            }else{
                Toast.makeText(this, "Error, intente de nuevo", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Error, intente de nuevo", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!preferencias.getSesion()){
            val intent1: Intent = Intent(applicationContext, Login::class.java).apply {
            }
            startActivity(intent1)
            finish()
        }else{

            loadUserFragment()

            bottomNavigationView=findViewById(R.id.bottom_navigation)

            bottomNavigationView.setOnItemSelectedListener { menuItem->
                when(menuItem.itemId){
                    R.id.home_menu->{
                        loadHomeFragment()
                    }
                    R.id.user_menu->{
                        getNombreFotoPerfil()
                        loadUserFragment()
                    }
                    R.id.chat_menu->{
                        loadChatFragment()
                    }
                }

                return@setOnItemSelectedListener true
            }

        }

    }

    private fun loadChatFragment(){
        title="Chat"
        val chatFragment=Chat()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, chatFragment, "chatFragment")
        fragmentTransaction.commit()
    }

    private fun loadHomeFragment(){
        title="Home"
        val HomeFragment=Home()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, HomeFragment, "HomeFragment")
        fragmentTransaction.commit()
    }

    private fun loadUserFragment(){
        title="User"
        val UserFragment=User()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, UserFragment, "HomeFragment")
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton() {
        preferencias.exitSesion()
        preferencias.setFotoP("vacio.jpg")
        preferencias.setFoto("empty.jpg")
        startActivity(Intent(this,Login::class.java))
    }

    override fun chat() {
        startActivity(Intent(this,ChatActivity::class.java))
    }

    override fun editarDatos(){
        startActivity(Intent(this,Datos::class.java))
    }


    //Metodos de botones fragment Home
    override fun categoriaJava(){
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "ProgramadorenJava")
        i.putExtra("title", "Programador en Java")
        startActivity(i)
    }
    override fun categoriaOracle(){
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "BasededatosOracle")
        i.putExtra("title", "Base de datos Oracle")
        startActivity(i)
    }
    override fun categoriaBaseDatos() {
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "BasedeDatos")
        i.putExtra("title", "Base de Datos")
        startActivity(i)
    }
    override fun categoriaMoviles() {
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "DesarrollodeAplicacionesMoviles")
        i.putExtra("title", "Desarrollo de Aplicaciones Moviles")
        startActivity(i)
    }
    override fun categoriaProgramador() {
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "ProgramadorenTecnologiasDiversas")
        i.putExtra("title", "Programador en Tecnologias Diversas")
        startActivity(i)
    }
    override fun categoriaVideojuegos() {
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "ProgramaciondeVideojuegos")
        i.putExtra("title", "Programacion de Videojuegos")
        startActivity(i)
    }
    override fun categoriaAdministracion() {
        val i = Intent(this, Cursos_Categorias::class.java)
        i.putExtra("name", "AdministraciondeProyectos")
        i.putExtra("title", "Administracion de Proyectos")
        startActivity(i)
    }

    //Convierte la imagen de Bitmap a base64
    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    //Button Seleccionar Fotografía Fragment chat(Pagos)
    override fun seleccionar(){
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }
    override fun verPago() {
        getNombreFotoPago()
        setFotoPago(preferencias.getFotoP().toString())
    }


    //Button actualizar foto de perfil Fragment user
    override fun perfil() {
        pickPerfil.launch(PickVisualMediaRequest())
        loadUserFragment()
    }

    //Métodos para Actualizar foto de perfil
    fun subirFotoPerfil(base: String?){
        requestQueue= Volley.newRequestQueue(this)

        val URL = "http://192.168.3.37:3977/TodoAcademy/fotoPerfil/"
        val id=preferencias.getObjId()

        val newUrl=URL+id

        val arreglo=JSONObject()
        arreglo.put("imagen","data:image/png;base64,"+base)

        val volleyfoto= JsonObjectRequest(Request.Method.PUT, newUrl, arreglo, {
                response ->
            Toast.makeText(applicationContext,"Cargando", Toast.LENGTH_LONG).show()
        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volleyfoto)
    }
    fun getNombreFotoPerfil(){

        requestQueue= Volley.newRequestQueue(this)
        var URL = "http://192.168.3.37:3977/TodoAcademy/accesoP"

        val id=preferencias.getObjId()
        val arreglo= JSONObject()
        arreglo.put("id", id)

        val volley1= JsonObjectRequest(Request.Method.POST, URL, arreglo, {
                response ->
            var nombreFoto=response.get("fotoPerfil").toString()
            preferencias.setFoto(nombreFoto)

        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }
    fun setFotoPerfil(name: String){

        requestQueue= Volley.newRequestQueue(this)

        val fotoPerfil=findViewById<ShapeableImageView>(R.id.fotoPerfil)

        var URL= "http://192.168.3.37:3977/TodoAcademy/getPhotoPerfil/"+ name

        val volley2= ImageRequest(
            URL,
            { response ->
                Log.d("volley-Perfil",URL)
                Toast.makeText(applicationContext,"Cargando fotografía...", Toast.LENGTH_LONG).show()
                fotoPerfil.setImageBitmap(response)
            },0,0,null,null
        ){

        }
        requestQueue.add(volley2)

    }

    //Métodos para pagos
    fun subirFotoPago(base: String?){
        requestQueue= Volley.newRequestQueue(this)

        val URL = "http://192.168.3.37:3977/TodoAcademy/fotoPago/"
        val id=preferencias.getObjId()

        val newUrl=URL+id

        val arreglo=JSONObject()
        arreglo.put("imagen","data:image/png;base64,"+base)

        val volleyfoto= JsonObjectRequest(Request.Method.PUT, newUrl, arreglo, {
                response ->
            Toast.makeText(applicationContext,"Subiendo pago...", Toast.LENGTH_SHORT).show()
        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volleyfoto)
    }
    fun getNombreFotoPago(){

        requestQueue= Volley.newRequestQueue(this)
        var URL = "http://192.168.3.37:3977/TodoAcademy/accesoPago"

        val id=preferencias.getObjId()
        val arreglo= JSONObject()
        arreglo.put("id", id)

        val volley1= JsonObjectRequest(Request.Method.POST, URL, arreglo, {
                response ->
            var nombreFoto=response.get("image").toString()
            preferencias.setFotoP(nombreFoto)
        }, {
                error ->
            Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)

    }
    fun setFotoPago(name: String){

        requestQueue= Volley.newRequestQueue(this)

        val fotoPago=findViewById<ImageView>(R.id.fotoSubida)

        var URL= "http://192.168.3.37:3977/TodoAcademy/getPhotoPago/"+ name

        val volley2= ImageRequest(
            URL,
            { response ->
                fotoPago.setImageBitmap(response)
            },0,0,null,null
        ){
            Toast.makeText(this, "No existe una foto", Toast.LENGTH_SHORT).show()
        }
        requestQueue.add(volley2)

    }


}