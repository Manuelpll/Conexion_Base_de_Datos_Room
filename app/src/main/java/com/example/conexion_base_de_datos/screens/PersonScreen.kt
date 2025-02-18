package com.example.conexion_base_de_datos.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PersonScreen(viewModel: PersonViewModel = viewModel()) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = viewModel.name, onValueChange = { viewModel.name = it }, label = { Text("Nombre") })
        TextField(value = viewModel.age, onValueChange = { viewModel.age = it }, label = { Text("Edad") })
        TextField(value = viewModel.address, onValueChange = { viewModel.address = it }, label = { Text("Dirección") })
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
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ID: ${person.id}")
                        Text("Nombre: ${person.name}")
                        Text("Edad: ${person.age}")
                        Text("Dirección: ${person.address}")
                        Button(onClick = {
                            viewModel.name = person.name
                            viewModel.age = person.age.toString()
                            viewModel.address = person.address
                            viewModel.selectedPerson = person
                        }) {
                            Text("Editar")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPersonScreen() {
    PersonScreen()
}