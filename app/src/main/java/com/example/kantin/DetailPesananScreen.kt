package com.example.kantin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

// --- Warna Tema Baru ---
val backgroundColor = Color(0xFFFFF5F6) // Warna Latar Belakang (Pink sangat muda)
val primaryPink = Color(0xFFD68C9A)     // Warna Utama (Pink lebih gelap)
val TextGray = Color(0xFF888888)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPesananScreen(
    onBackClick: () -> Unit = {} // Callback untuk tombol kembali
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Bukti Pembayaran",
                        color = primaryPink, // Menggunakan primaryPink
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = primaryPink // Menggunakan primaryPink
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor // Menggunakan backgroundColor
                )
            )
        },
        bottomBar = {
            // Bottom Navigation Bar dummy (Visual saja)
//            AdminBottomBar(primaryPink)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor) // Menggunakan backgroundColor
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()) // Agar bisa discroll
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // --- Section 1: Detail Pembayaran (User Info) ---
            SectionHeader("Detail Pembayaran")

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Placeholder Gambar Profil
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray) // Placeholder background
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text("Jungkook", fontWeight = FontWeight.Bold, color = primaryPink) // Menggunakan primaryPink
                    Text("ID Pesanan: INV-2025-0034", fontSize = 12.sp, color = TextGray)
                    Text("Tanggal: 11 Nov 2025 - 18:10", fontSize = 12.sp, color = TextGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Status Status
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = primaryPink), // Menggunakan primaryPink
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Text("Pembayaran Berhasil", color = Color.White, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Section 2: Informasi Pembayaran ---
            SectionHeader("Informasi Pembayaran")
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(label = "Metode:", value = "Cash")
            InfoRow(label = "Waktu Pembayaran:", value = "12 Nov 2025 - 09:38")
            InfoRow(label = "Nominal yang Dibayar:", value = "Rp25.000.00")
            InfoRow(label = "Total Akhir:", value = "Rp25.100")

            Spacer(modifier = Modifier.height(32.dp))

            // --- Section 3: Detail Pesanan ---
            SectionHeader("Detail Pesanan")
            Spacer(modifier = Modifier.height(16.dp))

            Text("Ayam Geprek Level 3", fontWeight = FontWeight.Bold, color = primaryPink) // Menggunakan primaryPink
            Spacer(modifier = Modifier.height(8.dp))
            InfoRow(label = "Jumlah:", value = "1 Porsi")
            InfoRow(label = "Harga:", value = "Rp25.000.00")
        }
    }
}

// Komponen Helper untuk Judul Section
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = primaryPink // Menggunakan primaryPink
    )
}

// Komponen Helper untuk Baris Informasi (Label kiri, Value kanan)
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = primaryPink, fontWeight = FontWeight.Medium, fontSize = 14.sp) // Menggunakan primaryPink
        Text(value, color = TextGray, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPesananScreenPreview() {
    MaterialTheme {
        DetailPesananScreen()
    }
}