package com.estudiantes.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.estudiantes.R
import com.estudiantes.ui.dialog.dialogModifyEstudiante

import com.estudiantes.ui.viewModel.EstudiantesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListEstudiantesAdapter(
    private val context: Context,
    private var viewModel: EstudiantesViewModel
): RecyclerView.Adapter<ListEstudiantesAdapter.ViewHolder>(){

    private val list = viewModel.getSearchEstudianteFirebase()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombre: TextView = itemView.findViewById(R.id.list_estudiantes_activity_item_nombre)
        val edad: TextView = itemView.findViewById(R.id.list_estudiantes_activity_item_edad)
        val ciudad: TextView = itemView.findViewById(R.id.list_estudiantes_activity_item_ciudad)
        val cambiar: FloatingActionButton = itemView.findViewById(R.id.list_estudiantes_activity_item_fab_cambiar)
        val borrar: FloatingActionButton = itemView.findViewById(R.id.list_estudiantes_activity_item_fab_borrar)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListEstudiantesAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_estudiantes_activity_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try{
            var listAdapter = list?.value?.get(position)!!
            holder.nombre.text = listAdapter.name
            holder.ciudad.text = listAdapter.city
            holder.edad.text = listAdapter.age.toString()

            holder.itemView.setOnClickListener{
                //Toast.makeText(context, "${listAdapter.name} ${listAdapter.city} ${listAdapter.age}",
                //Toast.LENGTH_LONG).show()
                dialogModifyEstudiante(context, viewModel, this, listAdapter, position)
            }

            holder.cambiar.setOnClickListener {
                dialogModifyEstudiante(context, viewModel, this, listAdapter, position)
            }

            holder.borrar.setOnClickListener {

            }

        } catch (e: Exception) {
            Log.e("ListEstudiantesAdapter", e.message.toString())
        }
    }

    override fun getItemCount(): Int {
        return list?.value?.count() ?: 0
    }

}

