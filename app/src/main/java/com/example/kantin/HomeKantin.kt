package com.example.kantin

import KantinTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeKantin(navController: NavHostController) {
    // Definisi Warna sesuai desain
    val backgroundColor = Color(0xFFFFF5F6)
    val primaryColor = Color(0xFFD68C9A)
    val searchBarColor = Color(0xFFF5F5F5)
    val textColor = Color(0xFFE57373)

    var query by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // --- KONTEN SCROLL UTAMA ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Padding bawah agar konten tidak tertutup Bottom Bar
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Search Bar
            OutlinedTextField(
                value = query,
                onValueChange = { newText -> query = newText },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                placeholder = {
                    Text(
                        "Cari",
                        color = primaryColor,
                        fontWeight = FontWeight.Medium
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.pencarian), // Pastikan icon ada
                        contentDescription = "Search Icon",
                        tint = primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = searchBarColor,
                    unfocusedContainerColor = searchBarColor,
                    disabledContainerColor = searchBarColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Menu Navigasi Atas (Favorit, Riwayat, Pesanan)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopMenuItem(iconRes = R.drawable.hati, text = "Favorit", color = textColor)
                TopMenuItem(iconRes = R.drawable.riwayat, text = "Riwayat", color = textColor)
                TopMenuItem(iconRes = R.drawable.pesanan, text = "Pesanan", color = textColor)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 4. Banner Pojok Kampus
            Image(
                painter = painterResource(id = R.drawable.benner), // Pastikan gambar benner ada
                contentDescription = "Banner Kantin",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(160.dp) // Tinggi disesuaikan
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 5. Bagian Menu Makanan
            SectionHeader(title = "Menu - Makanan", color = primaryColor)
            Spacer(modifier = Modifier.height(12.dp))

            // List Horizontal Makanan
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Ganti drawable 'benner' dengan gambar makanan asli Anda (misal: R.drawable.sempol)
                items(listOf("Sempol", "Gorengan", "Mie Goreng", "Seblak")) { item ->
                    FoodDrinkItem(imageRes = R.drawable.benner, name = item)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 6. Bagian Menu Minuman
            SectionHeader(title = "Menu - Minuman", color = primaryColor)
            Spacer(modifier = Modifier.height(12.dp))

            // List Horizontal Minuman
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Ganti drawable 'benner' dengan gambar minuman asli Anda
                items(listOf("Pop Ice", "Es Teh", "Air Mineral", "Boba")) { item ->
                    FoodDrinkItem(imageRes = R.drawable.benner, name = item)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // --- BOTTOM NAVIGATION BAR (Floating) ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp) // Memberi jarak dari tepi bawah layar
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(primaryColor),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Home
                Icon(
                    painter = painterResource(id = R.drawable.rumah),
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                // Icon Search
                Icon(
                    painter = painterResource(id = R.drawable.pencarian),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                // Icon Plus (Tengah)
                Icon(
                    painter = painterResource(id = R.drawable.tambah),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                // Icon Notif
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.lonceng),
                        contentDescription = "Notification",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    // Badge notifikasi merah
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(10.dp)
                            .background(Color.Red, CircleShape)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
                // Icon Profil
                Image(
                    painter = painterResource(id = R.drawable.benner), // Ganti dengan foto profil
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        }
    }
}

@Composable
fun TopMenuItem(iconRes: Int, text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SectionHeader(title: String, color: Color) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.8f)) // Sedikit transparan atau solid
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.arah), // Icon panah
            contentDescription = "Arrow",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun FoodDrinkItem(imageRes: Int, name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFE57373),
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun HomeKantinPreview() {
    KantinTheme {
        HomeKantin(navController = rememberNavController())
    }
}
