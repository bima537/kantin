package com.example.kantin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- Data Model ---
data class PesananData(
    val name: String,
    val time: String,
    val imageRes: Int
)

@Composable
fun PesananScreen(navController: NavController) { // Terima parameter NavController
    val backgroundColor = Color(0xFFFFF5F6)
    val primaryPink = Color(0xFFD68C9A)
    val buttonPink = Color(0xFFE59CA8)
    val activeRed = Color(0xFFFF1744)

    val pesananList = listOf(
        PesananData("JUNGKOOK", "1d", android.R.drawable.ic_menu_gallery),
        PesananData("ARYA MOHAN", "5d", android.R.drawable.ic_menu_gallery),
        PesananData("MINGYUU", "1ya", android.R.drawable.ic_menu_gallery),
        PesananData("TAEHYUNG", "1d", android.R.drawable.ic_menu_gallery),
        PesananData("DUAN JIAXU", "1d", android.R.drawable.ic_menu_gallery),
        PesananData("ZHANG LINGHE", "1d", android.R.drawable.ic_menu_gallery),
    )

    // HAPUS SCAFFOLD DI SINI. Langsung Column utama.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Set background di sini
            .padding(horizontal = 16.dp)
    ) {
        // 1. Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) { // Fungsikan tombol back
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = primaryPink.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Notifikasi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryPink
            )
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(24.dp))
        }

        HorizontalDivider(color = primaryPink.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // 2. Filter Kategori
        Surface(
            shape = RoundedCornerShape(50),
            color = buttonPink,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = "Kategori",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 3. List Pesanan
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pesananList) { item ->
                PesananItem(
                    data = item,
                    primaryPink = primaryPink,
                    buttonColor = buttonPink,
                    dotColor = activeRed
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// ... (Kode PesananScreen & PesananItem Anda di atas tetap sama)

@Preview(showBackground = true)
@Composable
fun PesananScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()

    MaterialTheme {
        Scaffold(
            containerColor = Color(0xFFFFF5F6), // Warna background screen
            bottomBar = {
                KantinBottomBar(
                    navController = navController,
                    primaryColor = Color(0xFFD68C9A)
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                PesananScreen(navController = navController)
            }
        }
    }
}