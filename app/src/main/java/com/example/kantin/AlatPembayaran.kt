package com.example.kantin

import KantinTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Data class untuk opsi pembayaran
data class PaymentOption(
    val title: String,
    val icon: Int // Menggunakan Int untuk resource drawable
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlatPembayaranScreen() {
    // Pengaturan warna status bar
    val systemUiController = rememberSystemUiController()
    val pinkColor = Color(0xFFE6B9C3)
    val lightPinkBackground = Color(0xFFFEF7F8)

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = pinkColor,
            darkIcons = true
        )
    }

    Scaffold(
        bottomBar = {
            // Bungkus dengan Box agar bisa di-clip
            Box(
                modifier = Modifier
                    .background(lightPinkBackground) // Beri warna latar belakang yang sama dengan layar utama
            ) {
                BottomAppBar(
                    modifier = Modifier
                        .height(70.dp) // Sedikit tambah tinggi agar lengkungan terlihat bagus
                        // Beri lengkungan di sudut atas
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    containerColor = pinkColor,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {

                }
            }
        },

        containerColor = lightPinkBackground // Warna latar utama layar
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding untuk bottom bar
        ) {
            // 1. Top Bar Kustom
            PaymentTopBar()

            // 2. Opsi Pembayaran
            PaymentOptionButton(
                title = "Metode\nPembayaran",
                iconRes = R.drawable.arah // GANTI dengan ikon Anda
            ) {
                // TODO: Aksi saat klik Metode Pembayaran
            }

            Spacer(modifier = Modifier.height(16.dp))

            PaymentOptionButton(
                title = "Informasi Tagihan",
                iconRes = R.drawable.arah // GANTI dengan ikon Anda
            ) {
                // TODO: Aksi saat klik Informasi Tagihan
            }

            // Spacer untuk mendorong tombol "Keluar" ke bawah
            Spacer(modifier = Modifier.weight(1f))

            // 3. Tombol Keluar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* TODO: Aksi saat klik Keluar */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = pinkColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("KELUAR")
                }
            }
        }
    }
}

// Composable untuk Top Bar kustom
@Composable
fun PaymentTopBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Kembali",
                tint = Color(0xFFC98C98),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { /* TODO: Aksi tombol kembali */ }
            )
            Text(
                text = "Alat Pembayaran",
                color = Color(0xFFC98C98),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Composable untuk tombol opsi
@Composable
fun PaymentOptionButton(title: String, iconRes: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE6B9C3),
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp // Atur jarak antar baris jika teksnya 2 baris
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlatPembayaranScreenPreview() {
    KantinTheme {
        AlatPembayaranScreen()
    }
}
