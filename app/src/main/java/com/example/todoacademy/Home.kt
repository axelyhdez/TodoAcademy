package com.example.todoacademy

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.example.todoacademy.databinding.FragmentHomeBinding
import com.example.todoacademy.databinding.FragmentUserBinding

class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var listener: OnFragmentActionsListener? = null

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        mContext=context
        super.onAttach(context)

        if (context is OnFragmentActionsListener){
            listener=context
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.java.setOnClickListener{ listener?.categoriaJava()}
        binding.oracle.setOnClickListener { listener?.categoriaOracle() }
        binding.basedatos.setOnClickListener { listener?.categoriaBaseDatos() }
        binding.moviles.setOnClickListener { listener?.categoriaMoviles() }
        binding.programador.setOnClickListener { listener?.categoriaProgramador() }
        binding.videojuegos.setOnClickListener { listener?.categoriaVideojuegos() }
        binding.administracion.setOnClickListener { listener?.categoriaAdministracion() }
        binding.ofimatica.setOnClickListener { Toast.makeText(context, "Proximamente...", Toast.LENGTH_SHORT).show() }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}