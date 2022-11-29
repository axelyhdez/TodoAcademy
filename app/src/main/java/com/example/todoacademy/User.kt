package com.example.todoacademy

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.todoacademy.databinding.FragmentUserBinding
import com.example.todoacademy.TodoAcademy.Companion.preferencias


class User : Fragment() {

    private var _binding: FragmentUserBinding? = null
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
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnCerrarSesion.setOnClickListener { listener?.onClickFragmentButton() }
        binding.btnChat.setOnClickListener { listener?.chat() }
        binding.tvCorreo.setText(preferencias.getCorreo())
        binding.tvNombre.setText(preferencias.getNombre())
        binding.tvApellido.setText(preferencias.getApellido())
        binding.tvApellidoP.setText(preferencias.getApellidoP())
        binding.tvTelefono.setText(preferencias.getTelefono())
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}