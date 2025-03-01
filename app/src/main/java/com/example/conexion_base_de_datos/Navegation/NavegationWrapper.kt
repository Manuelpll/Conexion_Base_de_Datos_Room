package com.example.conexion_base_de_datos.Navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.conexion_base_de_datos.screens.DetailScreen
import com.example.conexion_base_de_datos.screens.PersonScreen

@Composable
fun NavegationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PersonScreen) {
        composable<PersonScreen> {
            PersonScreen(
                navigateToDetail = { id -> navController.navigate(DetailScreen(id = id)) }
            )
        }
        composable<DetailScreen> { backStackEntry ->
            val detail: DetailScreen = backStackEntry.toRoute()
            DetailScreen(
                id = detail.id, // Se pasa el ID a la pantalla de detalle
                navigateToPerson = { navController.navigate(PersonScreen) }
            )
        }
    }
}



@Preview
@Composable
fun NavegacionPreview(){
    NavegationWrapper()
}