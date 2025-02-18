package com.example.conexion_base_de_datos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Tabla de la base de datos
@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    //Sentencia para asignar la clave primaria
    val id:Int,
    val name : String,
    val age:Int,
    val address:String
)
