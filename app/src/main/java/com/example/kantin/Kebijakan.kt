package com.example.kantin

import KantinTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KebijakanScreen() {
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
        containerColor = lightPinkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            KebijakanTopBar()

            // Konten Kebijakan yang baru
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp) // Sedikit perbesar padding horizontal
                    .verticalScroll(rememberScrollState())
            ) {
                // Judul Utama
                Text(
                    text = "Syarat dan Ketentuan Layanan Kantin",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC98C98),
                    // --- PERUBAHAN ADA DI SINI ---
                    modifier = Modifier
                        .fillMaxWidth() // 1. Penuhi lebar layar
                        .padding(top = 24.dp, bottom = 20.dp),
                    textAlign = TextAlign.Center // 2. Atur teks ke tengah
                )

                // --- MULAI TEKS KEBIJAKAN YANG BARU ---

                KebijakanSubJudul("1. Ketentuan Umum")
                KebijakanParagraf(
                    "Layanan Kantin Kampus ini dioperasikan oleh pengelola resmi yang ditunjuk oleh pihak universitas. Dengan menggunakan layanan ini, pengguna dianggap telah membaca, memahami, dan menyetujui seluruh syarat dan ketentuan yang berlaku."
                )

                KebijakanSubJudul("2. Pemesanan dan Pembayaran")
                KebijakanParagraf(
                    "Seluruh transaksi, termasuk pemesanan dan pembayaran, wajib dilakukan melalui aplikasi resmi Kantin Kampus. Metode pembayaran yang diterima adalah yang terintegrasi di dalam aplikasi. Transaksi di luar aplikasi tidak menjadi tanggung jawab pengelola."
                )

                KebijakanSubJudul("3. Pembatalan Pesanan")
                KebijakanParagraf(
                    "Pesanan yang telah berhasil dibayar dan sedang dalam proses persiapan oleh pihak kantin tidak dapat dibatalkan. Pengecualian dapat diberikan jika terjadi kesalahan teknis pada sistem atau kelalaian dari pihak kantin yang telah terverifikasi."
                )

                KebijakanSubJudul("4. Pengambilan Pesanan")
                KebijakanParagraf(
                    "Pengambilan pesanan harus dilakukan sesuai dengan jam operasional kantin yang tertera di aplikasi. Pesanan yang tidak diambil hingga akhir jam operasional pada hari yang sama akan dianggap hangus dan pembayaran tidak dapat dikembalikan."
                )

                KebijakanSubJudul("5. Menu dan Harga")
                KebijakanParagraf(
                    "Informasi mengenai menu, harga, dan ketersediaan produk dapat berubah sewaktu-waktu tanpa pemberitahuan terlebih dahulu. Pengelola berhak menyesuaikan daftar menu berdasarkan ketersediaan bahan baku dan kebijakan internal lainnya."
                )

                KebijakanSubJudul("6. Penanganan Keluhan")
                KebijakanParagraf(
                    "Setiap keluhan terkait kualitas produk, pelayanan, atau ketidaksesuaian pesanan wajib disampaikan melalui fitur layanan bantuan (help desk) yang tersedia di aplikasi. Keluhan akan ditindaklanjuti selambat-lambatnya 2x24 jam kerja."
                )

                KebijakanSubJudul("7. Privasi dan Keamanan Data")
                KebijakanParagraf(
                    "Kami menjamin kerahasiaan data pribadi pengguna. Data yang dikumpulkan hanya akan digunakan untuk keperluan operasional layanan Kantin Kampus, seperti verifikasi pesanan dan analisis internal, serta tidak akan dibagikan kepada pihak ketiga tanpa persetujuan pengguna."
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// Composable untuk Top Bar kustom (Tidak ada perubahan)
@Composable
fun KebijakanTopBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6B9C3))
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
                text = "Kebijakan",
                color = Color(0xFFC98C98),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// --- COMPOSABLE BARU UNTUK STRUKTUR TEKS ---

@Composable
fun KebijakanSubJudul(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun KebijakanParagraf(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.Gray,
        textAlign = TextAlign.Justify,
        lineHeight = 22.sp, // Jarak antar baris agar nyaman dibaca
        modifier = Modifier.padding(bottom = 16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun KebijakanScreenPreview() {
    KantinTheme {
        KebijakanScreen()
    }
}
