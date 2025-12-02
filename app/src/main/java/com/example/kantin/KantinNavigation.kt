package com.example.kantin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun KantinNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Daftar route yang akan menampilkan BottomBar
    val bottomBarRoutes = listOf(
        BottomBarScreen.Dashboard.route, // "dashboard"
        BottomBarScreen.Order.route,     // "pesanan"
        BottomBarScreen.Menu.route,      // "menu"
        BottomBarScreen.Pengguna.route   // "pengguna"
    )

    Scaffold(
        bottomBar = {
            // Tampilkan BottomBar hanya jika route saat ini ada di daftar
            if (currentRoute in bottomBarRoutes) {
                // Panggil file BottomBar yang sudah Anda buat
                KantinBottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login", // Mulai dari Login
            modifier = Modifier.padding(innerPadding)
        ) {
            // --- LOGIN ---
            composable("login") {
                LoginKantin(
                    onUserLogin = {
                        navController.navigate("home_user") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onAdminLogin = {
                        // KETIKA ADMIN LOGIN -> ARAHKAN KE "dashboard"
                        navController.navigate(BottomBarScreen.Dashboard.route) {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            // --- Menu User ---
            composable("home_user") {
                HomeKantin(navController = navController)
            }

            // --- MENU ADMIN ---

            //Dashboard (Route: "dashboard") -> Buka HomeAdmin
            composable(BottomBarScreen.Dashboard.route) {
                // Pastikan HomeAdmin sudah BERSIH (Tanpa Scaffold)
                HomeAdmin(navController = navController)
            }

            //Order (Route: "pesanan") -> Buka Layar Pesanan Admin
            composable(BottomBarScreen.Order.route) {
                // Contoh: AdminOrderScreen(navController)
                // Untuk sementara pakai placeholder atau PesananScreen yang ada
                PesananScreen(navController = navController)
            }

            //Menu (Route: "menu") -> Buka Layar Kelola Menu
            composable(BottomBarScreen.Menu.route) {
                MenuScreen(navController = navController) // Ganti dengan AdminMenuScreen Anda
            }

            composable("tambah_menu") {
                AddMenuScreen(navController = navController)
            }

            //Pengguna (Route: "pengguna") -> Buka Layar Kelola Pengguna
            composable(BottomBarScreen.Pengguna.route) {
                PenggunaScreen(navController = navController) // Ganti dengan AdminPenggunaScreen Anda
            }
        }
    }
}