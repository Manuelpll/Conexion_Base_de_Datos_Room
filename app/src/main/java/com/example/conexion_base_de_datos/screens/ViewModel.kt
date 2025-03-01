package com.example.conexion_base_de_datos.screens

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.conexion_base_de_datos.data.PeopleDB
import com.example.conexion_base_de_datos.data.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel(application: Application) : AndroidViewModel(application) {
    //Creacion de la base de datos
    private val db = Room.databaseBuilder(
        application, PeopleDB::class.java, "person_db"
    ).build()
//Objeto DAO para trabajar con la base de datos
    private val personDao = db.personDao()
//Variables de la app
    val peopleList = mutableStateListOf<Person>()
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var address by mutableStateOf("")
    var selectedPerson: Person? by mutableStateOf(null)
//Carga las personas en la base de datos
    init {
        loadPeople()
    }
//Metodo que actualiza la lista
    private fun loadPeople() {
        viewModelScope.launch {
            val people = withContext(Dispatchers.IO) { personDao.getAll() }
            peopleList.clear()
            peopleList.addAll(people)
        }
    }
//Metodo que añade las personas
    fun addPerson() {
        if (name.isNotEmpty() && age.isNotEmpty() && address.isNotEmpty()) {
            viewModelScope.launch {
                val newPerson = Person(id = 0, name = name, age = age.toInt(), address = address)
                withContext(Dispatchers.IO) {
                    personDao.insert(listOf(newPerson))
                }
                loadPeople()
                clearFields()
            }
        }
    }
    //Metodo que acualiza el nombre
    fun updateNombre(nuevoNombre: String) {
        name= nuevoNombre
    }
    //Metodo que acualiza la edad
    fun updateEdad(nuevaEdad: String) {
        age = nuevaEdad
    }
    //Metodo que actualiza la dirreccion
    fun updateDireccion(nuevaDirecion: String){
        address = nuevaDirecion
    }
//Metodo para actualizar la personas
fun updatePerson() {
    selectedPerson?.let {
        viewModelScope.launch {
            val updatedPerson = it.copy(name = name, age = age.toInt(), address = address)
            withContext(Dispatchers.IO) {
                personDao.update(updatedPerson)
            }
            loadPeople()
            clearFields()
            selectedPerson = null
        }
    }
}
    //Metodo que elimina una persona
    fun deletePerson(person: Person) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                personDao.delete(person)
            }
            loadPeople()
        }
    }
//Metodo que resetea las variables para volver a usarlas
    private fun clearFields() {
        name = ""
        age = ""
        address = ""
    }
    //Metodo que obtiene el id de la persona elegida
    fun getPersonById(id: Int): Person? {
        return peopleList.find { it.id == id }
    }
}