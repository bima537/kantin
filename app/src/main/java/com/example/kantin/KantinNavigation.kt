package com.example.kantin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun KantinNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginKantin(
                onUserLogin = {
                    navController.navigate("menu_user")
                },
                onAdminLogin = {
                    navController.navigate("menu_admin")
                }
            )
        }
        composable("menu_user") {
            HomeKantin()
        }
    }
}