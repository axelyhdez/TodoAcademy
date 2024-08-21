package com.example.todoacademy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

class CursosAdapter(private val datosList:ArrayList<Cursos_datos>,private val context:Context):RecyclerView.Adapter<CursosAdapter.ViewHolder>(){

    private lateinit var requestQueue: RequestQueue

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val labelNombreC=itemView.findViewById<TextView>(R.id.LabelNombreC)
        val labelDuracion=itemView.findViewById<TextView>(R.id.LabelDuracion)
        val labelModalidad=itemView.findViewById<TextView>(R.id.LabelModalidad)
        val fotoCurso=itemView.findViewById<ImageView>(R.id.fotoCurso)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carta_curso, parent, false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curso=datosList[position]

        holder.labelNombreC.text=curso.nombreC
        holder.labelDuracion.text=curso.duracion
        holder.labelModalidad.text=curso.modalidad

            requestQueue= Volley.newRequestQueue(context)
            var URL= "http://192.168.3.37:3977/TodoAcademy/getPhotoCurso/"+curso.imagen
            val volley2= ImageRequest(
                URL,
                { response ->
                    holder.fotoCurso.setImageBitmap(response)
                },0,0,null,null
            ){

            }
            requestQueue.add(volley2)

    }

    override fun getItemCount(): Int {
        return datosList.size
    }

}