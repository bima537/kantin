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

// 1. Nama data class diubah menjadi DrinkItem
data class DrinkItem(
    val name: String,
    val price: String,
    val imageRes: Int
)

// 2. Daftar diubah menjadi daftar minuman
val drinkList = listOf(
    DrinkItem("Es Teh", "Rp 3.000,00", R.drawable.arah),
    DrinkItem("Es Jeruk", "Rp 4.000,00", R.drawable.arah),
    DrinkItem("Kopi Hitam", "Rp 5.000,00", R.drawable.arah),
    DrinkItem("Jus Alpukat", "Rp 8.000,00", R.drawable.arah),
    DrinkItem("Air Mineral", "Rp 2.000,00", R.drawable.arah)
)

// 3. Nama fungsi utama diubah menjadi MenuMinumanScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMinumanScreen() {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            ) {
                TopAppBar(
                    // 4. Judul diubah menjadi "Menu Minuman"
                    title = { Text("Menu Minuman", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFF48FB1), // Warna pink
                        titleContentColor = Color.White,
                    )
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
                modifier = Modifier
                    .height(110.dp)
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
                        modifier = Modifier.padding(vertical = 8.dp)
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
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // 5. Menggunakan `drinkList` dan memanggil `DrinkItemRow`
            items(drinkList) { drink ->
                DrinkItemRow(drink)
            }
        }
    }
}

// 6. Nama fungsi Composable untuk baris item diubah menjadi DrinkItemRow
@Composable
fun DrinkItemRow(drink: DrinkItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drink.imageRes),
                contentDescription = drink.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = drink.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFD81B60)
                )
                Text(
                    text = drink.price,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Pilih ${drink.name}",
                tint = Color(0xFFF48FB1)
            )
        }
    }
}

// 7. Preview diubah untuk menampilkan MenuMinumanScreen
@Preview(showBackground = true)
@Composable
fun MenuMinumanPreview() {
    KantinTheme {
        MenuMinumanScreen()
    }
}
