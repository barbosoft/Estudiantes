package com.estudiantes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.estudiantes.R
import com.estudiantes.inicializatorFirebase
import com.estudiantes.ui.dialog.dialogNewEstudiante
import com.estudiantes.ui.viewModel.EstudiantesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListEstudiantesActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(EstudiantesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_estudiantes_activity)

        inicializatorFirebase(this)
        viewModel.searchEstudianteFirebase()

        val fab = findViewById<FloatingActionButton>(R.id.list_estudiantes_activity_fab)

        fab.setOnClickListener {
            //viewModel.sendEstudianteToFirebase(
               // EstudianteDtos(
                   // "3", "Pedro","Zaragoza", 56
               // )
           // )

            dialogNewEstudiante(this, viewModel)

            // Comprovation in LOGcat
           /* var list = viewModel.getSearchEstudianteFirebase().value
            for(i in list!!.indices){
                val estudiante = list[i].name

                Log.i("onCreateTest", "oncreate: Estudiante Nombre: $estudiante")
            }*/
        }


    }
}