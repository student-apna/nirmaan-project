package org.example.project.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(onNavHostReady: NavHostController = rememberNavController()) {
    val navController = rememberNavController()
    MaterialTheme {
        Navigation()
    }

}