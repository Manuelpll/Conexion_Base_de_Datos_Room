package com.example.conexion_base_de_datos.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PersonScreen(navigateToDetail: (Int) -> Unit, viewModel: ViewModel = viewModel()) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = viewModel.name, onValueChange = { viewModel.updateNombre(it)  }, label = { Text("Nombre") })
        TextField(value = viewModel.age, onValueChange = { viewModel.updateEdad(it) }, label = { Text("Edad") })
        TextField(value = viewModel.address, onValueChange = { viewModel.updateDireccion(it) }, label = { Text("Dirección") })
        Row {
            Button(onClick = { viewModel.addPerson() }) {
                Text("Agregar Persona")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.updatePerson() }, enabled = viewModel.selectedPerson != null) {
                Text("Actualizar Persona")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(viewModel.peopleList) { person ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navigateToDetail(person.id) } // Navega al detalle al hacer clic
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ID: ${person.id}")
                        Text("Nombre: ${person.name}")
                        Text("Edad: ${person.age}")
                        Text("Dirección: ${person.address}")
                    }
                    Row ( modifier =  Modifier.size(150.dp) , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = {
                            viewModel.name = person.name
                            viewModel.age = person.age.toString()
                            viewModel.address = person.address
                            viewModel.selectedPerson = person
                        }) {
                            Text("Editar", fontWeight = FontWeight.Bold)
                        }
                        Button(onClick = {
                            viewModel.deletePerson(person)
                        },colors = ButtonDefaults.buttonColors(Color.Red)) {
                            Text("Eliminar", fontWeight = FontWeight.Bold , modifier = Modifier.size(270.dp,40.dp))
                        }
                    }
                }
            }
        }
    }
}

