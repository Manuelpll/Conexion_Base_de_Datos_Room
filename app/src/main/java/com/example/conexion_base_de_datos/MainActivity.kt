package com.example.conexion_base_de_datos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.conexion_base_de_datos.Navegation.NavegationWrapper
import com.example.conexion_base_de_datos.screens.PersonScreen
import com.example.conexion_base_de_datos.ui.theme.Conexion_Base_de_DatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Conexion_Base_de_DatosTheme {
               NavegationWrapper()
            }
        }
    }
}