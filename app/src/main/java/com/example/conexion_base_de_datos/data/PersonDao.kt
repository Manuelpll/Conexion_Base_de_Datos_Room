package com.example.conexion_base_de_datos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Objeto que sirve para interactuar con la base de datos
@Dao
interface PersonDao {
    @Query("SELECT * FROM person_table")
    fun getAll():List<Person>

    @Query("SELECT * FROM person_table WHERE id=:id")
    fun getById(id:Int):Person

    @Update
    fun update(person: Person)

    @Insert
    fun insert(people: List<Person>)

    @Delete
    fun delete(person: Person)
}