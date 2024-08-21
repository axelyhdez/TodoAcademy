package com.example.todoacademy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue

class UsuariosAdapter(private val datosList:ArrayList<Usuarios_datos>,private val context: Context):RecyclerView.Adapter<UsuariosAdapter.ViewHolder>(){

    private lateinit var requestQueue: RequestQueue

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val Correo=itemView.findViewById<TextView>(R.id.correo)
        val Nombre=itemView.findViewById<TextView>(R.id.nombre)
        val ApellidoP=itemView.findViewById<TextView>(R.id.apellidoP)
        val ApellidoM=itemView.findViewById<TextView>(R.id.apellidoM)
        val Telefono=itemView.findViewById<TextView>(R.id.telefono)
        val Escuela=itemView.findViewById<TextView>(R.id.escuela)
        val FechaI=itemView.findViewById<TextView>(R.id.fechaF)
        val FechaF=itemView.findViewById<TextView>(R.id.fechaI)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carta_usuarios, parent, false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario=datosList[position]

        holder.Correo.text=usuario.correo
        holder.Nombre.text=usuario.nombre
        holder.ApellidoP.text=usuario.apellido
        holder.ApellidoM.text=usuario.apellidoM
        holder.Telefono.text=usuario.telefono
        holder.Escuela.text=usuario.escuela
        holder.FechaI.text=usuario.fechaI
        holder.FechaF.text=usuario.fechaF


    }

    override fun getItemCount(): Int {
        return datosList.size
    }

}