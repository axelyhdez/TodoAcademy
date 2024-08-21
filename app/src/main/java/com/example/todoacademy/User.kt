package com.example.todoacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.todoacademy.databinding.FragmentUserBinding
import com.example.todoacademy.TodoAcademy.Companion.preferencias
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.fragment_user.*
import org.json.JSONObject


class User : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private var listener: OnFragmentActionsListener? = null

    private lateinit var mContext: Context

    private lateinit var requestQueue: RequestQueue


    override fun onAttach(context: Context) {
        mContext=context
        super.onAttach(context)

        if (context is OnFragmentActionsListener){
            listener=context
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("volley",preferencias.getObjId())
        getNombreFotoPerfil()
        foto(preferencias.getFoto().toString())


        binding.btnCerrarSesion.setOnClickListener { listener?.onClickFragmentButton() }
        binding.btnChat.setOnClickListener { listener?.chat() }
        binding.btnEditar.setOnClickListener { listener?.editarDatos() }
        binding.ActualizarFoto.setOnClickListener { listener?.perfil() }


        binding.tvCorreo.setText(preferencias.getCorreo())
        binding.tvNombre.setText(preferencias.getNombre())
        binding.tvApellido.setText(preferencias.getApellido())
        binding.tvApellidoP.setText(preferencias.getApellidoP())
        binding.tvTelefono.setText(preferencias.getTelefono())
        binding.tvEscuela.setText(preferencias.getEscuela())
        binding.tvfechaI.setText(preferencias.getfechaI())
        binding.tvfechaF.setText(preferencias.getfechaF())

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    fun getNombreFotoPerfil(){
        requestQueue= Volley.newRequestQueue(context)
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
            Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)
    }
    fun foto(name: String){
        Toast.makeText(context,"Cargando fotografía...", Toast.LENGTH_LONG).show()
        requestQueue= Volley.newRequestQueue(context)
        var URL= "http://192.168.3.37:3977/TodoAcademy/getPhotoPerfil/"+ name

        val volley2= ImageRequest(
            URL,
            { response ->

                binding.fotoPerfil.setImageBitmap(response)
            },0,0,null,null
        ){
            Toast.makeText(context, "La foto no se cargó correctamente", Toast.LENGTH_SHORT).show()
        }
        requestQueue.add(volley2)
    }
}