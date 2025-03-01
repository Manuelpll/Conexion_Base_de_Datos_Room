package com.example.conexion_base_de_datos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(id: Int, viewModel: ViewModel = viewModel(), navigateToPerson: () -> Unit) {
    val person = viewModel.getPersonById(id)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ID: ${person?.id ?: "No encontrado"}")
                Text("Nombre: ${person?.name ?: "No encontrado"}")
                Text("Edad: ${person?.age ?: "No encontrado"}")
                Text("Dirección: ${person?.address ?: "No encontrado"}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navigateToPerson() }) {
            Text("Volver a la Lista")
        }
    }
}