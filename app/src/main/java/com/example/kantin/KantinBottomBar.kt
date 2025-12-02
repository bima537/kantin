package com.example.kantin

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun KantinBottomBar(
    navController: NavController,
    primaryColor: Color = Color(0xFFD68C9A)
) {
    val screens = listOf(
        BottomBarScreen.Dashboard,
        BottomBarScreen.Order,
        BottomBarScreen.Menu,
        BottomBarScreen.Pengguna
    )



    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = primaryColor,
        contentColor = Color.White,
        modifier = Modifier.clip(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ),
    ) {
        screens.forEach { screen ->
            val isSelected = currentRoute == screen.route

            NavigationBarItem(
                icon = {
                    // Pilih icon berdasarkan status seleksi
                    val currentIcon = if (isSelected) screen.selectedIcon else screen.unselectedIcon

                    if (screen.hasBadge && screen.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ) { Text(screen.badgeCount.toString()) }
                            }
                        ) {
                            Icon(imageVector = currentIcon, contentDescription = screen.title)
                        }
                    } else {
                        Icon(imageVector = currentIcon, contentDescription = screen.title)
                    }
                },
                label = { Text(screen.title) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            // Pop up ke "dashboard" agar back stack bersih
                            popUpTo("dashboard") {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primaryColor,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
        }
    }
}
