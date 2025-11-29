package com.example.kantin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
// Penting: Tambahkan import ini untuk Preview
import androidx.navigation.compose.rememberNavController

// Data class
data class UserOrder(
    val name: String,
    val date: String,
    val totalAmount: String,
    val imageRes: Int
)

@Composable
fun HomeAdmin(navController: NavController) {
    // Warna
    val backgroundColor = Color(0xFFFFF5F6)
    val primaryPink = Color(0xFFD68C9A)

    // Data Dummy
    val orders = listOf(
        UserOrder("JUNGKOOK", "23-07-2025 02:00", "Rp50.000,00", android.R.drawable.ic_menu_gallery),
        UserOrder("MINGYU", "14-09-2025 03:00", "Rp75.000,00", android.R.drawable.ic_menu_gallery),
        UserOrder("Carlo Emilion", "11-10-2025 15:01", "Rp40.000,00", android.R.drawable.ic_menu_gallery),
        UserOrder("Daniel Jay Park", "12-10-2025 08:00", "Rp35.000,00", android.R.drawable.ic_menu_gallery),
        UserOrder("Mark Rojas", "12-10-2025 10:00", "Rp10.000,00", android.R.drawable.ic_menu_gallery)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(40.dp))

            Text(
                text = "POJOK KAMPUS",
                color = primaryPink,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )

            Surface(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                color = Color.Gray
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Admin Profile",
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatCard(
                title = "Pendapatan",
                value = "Rp5.000.000,00",
                color = primaryPink,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            StatCard(
                title = "Pesanan",
                value = "100",
                color = primaryPink,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            StatCard(
                title = "Paling Laris",
                value = "Bakso",
                color = primaryPink,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Recent Orders List
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = primaryPink)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(orders) { order ->
                    OrderItem(order)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// --- FUNGSI HELPER ---

@Composable
fun StatCard(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.8f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
fun OrderItem(order: UserOrder) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                color = Color.Gray
            ) {
                Image(
                    painter = painterResource(id = order.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = order.name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.date,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
        }

        Text(
            text = order.totalAmount,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// FUNGSI AdminBottomBar SUDAH DIHAPUS KARENA TIDAK DIPERLUKAN DI SINI

@Preview(showBackground = true)
@Composable
fun HomeAdminPreview() {
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
                HomeAdmin(navController = navController)
            }
        }
    }
}