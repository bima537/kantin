package com.example.kantin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// --- Data Model untuk Pengguna ---
data class UserData(
    val name: String,
    val role: String,
    val isActive: Boolean,
    val imageRes: Int
)

@Composable
fun PenggunaScreen(navController: NavController) {
    // Warna Tema (Sesuai gambar)
    val backgroundColor = Color(0xFFFFF5F6) // Background pink sangat muda
    val primaryPink = Color(0xFFD68C9A)      // Warna utama pink
    val textPink = Color(0xFFE57373)         // Warna teks agak kemerahan
    val activeGreen = Color(0xFFC8E6C9)      // Hijau muda untuk badge aktif
    val activeTextGreen = Color(0xFF2E7D32)  // Hijau tua teks
    val offRed = Color(0xFFFFCDD2)           // Merah muda untuk badge off
    val offTextRed = Color(0xFFC62828)       // Merah tua teks

    // Data Dummy Pengguna (Sesuai Gambar)
    val userList = listOf(
        UserData("Mohan", "Mahasiswa", true, android.R.drawable.ic_menu_gallery),
        UserData("JUNGKOOK", "Karyawan", false, android.R.drawable.ic_menu_gallery),
        UserData("Tyla", "Karyawan", true, android.R.drawable.ic_menu_gallery),
        UserData("Daniel", "Mahasiswa", false, android.R.drawable.ic_menu_gallery),
        UserData("Mingyu", "Mahasiswa", false, android.R.drawable.ic_menu_gallery),
        UserData("Ji-hoon", "Dosen", false, android.R.drawable.ic_menu_gallery),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // 1. Header (Tombol Back & Judul)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = primaryPink,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Pengguna",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryPink
            )
            Spacer(modifier = Modifier.weight(1f))
            // Spacer dummy agar text benar-benar di tengah
            Spacer(modifier = Modifier.size(24.dp))
        }

        // Garis pemisah tipis di bawah header (opsional, visual separator)
        HorizontalDivider(color = primaryPink, thickness = 1.dp)

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Search Bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            color = primaryPink.copy(alpha = 0.2f), // Background pink transparan
            border = BorderStroke(1.dp, primaryPink)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pencarian...",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Filter",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Filter",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Judul Section
        Text(
            text = "Semua Pengguna",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textPink
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. List Pengguna
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(userList) { user ->
                UserCardItem(
                    user = user,
                    primaryPink = primaryPink,
                    activeColor = activeGreen,
                    activeTextColor = activeTextGreen,
                    offColor = offRed,
                    offTextColor = offTextRed
                )
            }
            // Tambahan spacer agar item terakhir tidak tertutup bottom bar
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun UserCardItem(
    user: UserData,
    primaryPink: Color,
    activeColor: Color,
    activeTextColor: Color,
    offColor: Color,
    offTextColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, primaryPink.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Titik Merah di kiri (dekorasi sesuai gambar)
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Foto Profil
            Image(
                painter = painterResource(id = user.imageRes),
                contentDescription = user.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Nama dan Status
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryPink
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Status : ${user.role}...",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // Badge Status (Aktif / Off)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (user.isActive) activeColor else offColor,
                modifier = Modifier.height(24.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = if (user.isActive) "Aktif" else "Off",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (user.isActive) activeTextColor else offTextColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PenggunaScreenPreview() {
    val navController = rememberNavController()

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
                PenggunaScreen(navController = navController)
            }
        }
    }
}