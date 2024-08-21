package com.example.todoacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.todoacademy.databinding.FragmentChatBinding
import com.example.todoacademy.databinding.FragmentUserBinding
import org.json.JSONObject


class Chat : Fragment() {

    private var _binding: FragmentChatBinding? = null
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
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pago()

        binding.buttonSeleccionar.setOnClickListener { listener?.seleccionar() }
        binding.ultimaFoto.setOnClickListener { listener?.verPago() }
        binding.Confirmacion.setText(TodoAcademy.preferencias.getPago())
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun pago(){
        requestQueue= Volley.newRequestQueue(context)
        var URL = "http://192.168.3.37:3977/TodoAcademy/resPago"

        val id= TodoAcademy.preferencias.getObjId()
        val arreglo= JSONObject()
        arreglo.put("id", id)


        val volley1= JsonObjectRequest(Request.Method.POST, URL, arreglo, {
                response ->
            TodoAcademy.preferencias.setPago(response.get("pago").toString())
            Log.d("volley-ResPago", response.get("pago").toString())
        }, {
                error -> Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show()
        })

        requestQueue.add(volley1)


    }

}