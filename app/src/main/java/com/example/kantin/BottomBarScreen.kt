package com.example.kantin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean = false,
    val badgeCount: Int = 0
) {
    data object Dashboard : BottomBarScreen(
        route = "dashboard",
        title = "Dashboard",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    data object Order : BottomBarScreen(
        route = "pesanan",
        title = "Order",
        selectedIcon = Icons.Filled.ReceiptLong,
        unselectedIcon = Icons.Outlined.ReceiptLong,
        hasBadge = true,
        badgeCount = 5
    )
    data object Menu : BottomBarScreen(
        route = "menu",
        title = "Menu",
        selectedIcon = Icons.Filled.Fastfood,
        unselectedIcon = Icons.Outlined.Fastfood
    )
    data object Pengguna : BottomBarScreen(
        route = "pengguna",
        title = "Pengguna",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
}