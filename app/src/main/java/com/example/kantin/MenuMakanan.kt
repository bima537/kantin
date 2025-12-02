package com.example.kantin

import KantinTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
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

data class FoodItem(
    val name: String,
    val price: String,
    val imageRes: Int
)

val foodList = listOf(
    FoodItem("Mie Goreng/Rebus", "Rp 7.000,00", R.drawable.arah),
    FoodItem("Sempol Ayam", "Rp 5.000,00/3pcs", R.drawable.arah),
    FoodItem("Aneka Gorengan", "Rp 5.000,00/4pcs", R.drawable.arah),
    FoodItem("Seblak Campur", "Rp 10.000,00", R.drawable.arah),
    FoodItem("Bakso", "Rp 13.000,00", R.drawable.arah)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMakananScreen() {
    Scaffold(
        topBar = {
            // Kita bungkus TopAppBar dengan Box agar bisa di-clip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            ) {
                TopAppBar(
                    title = { Text("Menu-Makanan", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFF48FB1), // Warna pink
                        titleContentColor = Color.White,
                    )
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                // Buat BottomAppBar menjadi transparan agar bentuk clip terlihat
                containerColor = Color.Transparent,
                // Gunakan modifier untuk memberikan bentuk, tinggi, dan warna latar belakang
                modifier = Modifier
                    .height(110.dp) // <-- TAMBAHKAN INI untuk mengatur tinggi secara manual
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color(0xFFE6B9C3)),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 28.dp)
            ) {
                Button(
                    onClick = { /* TODO: Tambahkan logika ketika tombol "Pilih" diklik */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFC98C98)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        "Pilih",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp) // Sedikit tambah padding agar lebih tinggi
                    )
                }
            }
        },

        containerColor = Color(0xFFFFF0F1) // Warna latar belakang utama
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            // Menambahkan padding di atas dan bawah list untuk jarak dari Top/Bottom bar
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(foodList) { food ->
                FoodItemRow(food)
            }
        }
    }
}

@Composable
fun FoodItemRow(food: FoodItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Menambah sedikit bayangan pada card
    ) {
        Row(
            modifier = Modifier.padding(12.dp), // Sedikit memperbesar padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = food.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFD81B60) // Warna pink yang lebih gelap agar lebih terbaca
                )
                Text(
                    text = food.price,
                    fontSize = 14.sp,
                    color = Color.DarkGray // Warna abu-abu lebih gelap
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Pilih ${food.name}",
                tint = Color(0xFFF48FB1)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KantinTheme {
        MenuMakananScreen()
    }
}
