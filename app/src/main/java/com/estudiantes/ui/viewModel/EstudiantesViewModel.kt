package com.estudiantes.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.repository.EstudiantesRepository

class EstudiantesViewModel: ViewModel() {

    private val repository = EstudiantesRepository()
    private var listEstudiantes = MutableLiveData<List<EstudianteDtos>>()


    fun sendEstudianteToFirebase(estudiante: EstudianteDtos){
        val list = mutableListOf<EstudianteDtos>()
        list.addAll(listEstudiantes.value!!)
        list.add(estudiante)
        repository.sendToFirebase(estudiante)
        listEstudiantes.value = list
    }

    fun changeEstudianteToFirebase(estudiante: EstudianteDtos){
        val list = mutableListOf<EstudianteDtos>()
        list.addAll(listEstudiantes.value!!)
        list[searchPosition(estudiante)] = estudiante
        repository.sendToFirebase(estudiante)
        listEstudiantes.value = list
    }

    private fun searchPosition(estudiante: EstudianteDtos): Int {
        var position = 0
        if(!listEstudiantes.value.isNullOrEmpty()){
            for(i in listEstudiantes.value!!.indices){
                if(listEstudiantes.value!![i].id == estudiante.id){
                    position = i
                }
            }
        }
        return position
    }

    fun searchEstudianteFirebase(){
        listEstudiantes.value = repository.searchFirebase()
    }

    fun getSearchEstudianteFirebase(): MutableLiveData<List<EstudianteDtos>> {
        return listEstudiantes
    }

    fun nextID(): String {
        var masGrande = 0

        if(!listEstudiantes.value.isNullOrEmpty()){
            for (i in listEstudiantes.value!!.indices){
                if(listEstudiantes.value!![i].id.toInt()>masGrande)
                    masGrande = listEstudiantes.value!![i].id.toInt()
            }
        }
        return (masGrande+1).toString()

    }
    fun deleteEstudianteToFirebase(estudiante: EstudianteDtos){
        val list = mutableListOf<EstudianteDtos>()

        if (listEstudiantes.value!!.size > 1) {

            list.addAll(listEstudiantes.value!!)
            list.removeAt(searchPosition(estudiante))
            //list.remove(estudiante)

        }
        repository.deleteEstudiante(estudiante.id)
        listEstudiantes.value = list
    }

}