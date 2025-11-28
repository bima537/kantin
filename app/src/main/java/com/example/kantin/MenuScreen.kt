package com.example.kantin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data Model untuk Menu
data class MenuItemData(
    val id: String,
    val name: String,
    val priceString: String, // Contoh format: "Price :10.000,00"
    val stockString: String, // Contoh format: "Stok : 100"
    val isActive: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    // --- WARNA DARI PROJECT ANDA ---
    val backgroundColor = Color(0xFFFFF5F6) // Background Pink Sangat Muda
    val primaryPink = Color(0xFFD68C9A)     // Pink Utama (Header/BottomBar)

    // Warna tambahan untuk UI elemen
    val searchBarBg = Color(0xFFFFE4E9)     // Pink sedikit lebih gelap dari bg untuk search
    val editBtnColor = Color(0xFFBBDEFB)    // Biru Muda
    val editTextColor = Color(0xFF1976D2)   // Biru Tua
    val deleteBtnColor = Color(0xFFFFCDD2)  // Merah Muda
    val deleteTextColor = Color(0xFFC62828) // Merah Tua
    val activeBtnColor = Color(0xFFC8E6C9)  // Hijau Muda
    val activeTextColor = Color(0xFF2E7D32) // Hijau Tua

    // Dummy Data
    val menuList = remember {
        listOf(
            MenuItemData("1", "Mie Goreng", "Price :10.000,00", "Stok : 100"),
            MenuItemData("2", "Sempol", "Price :5.000,00", "Stok : 150"),
            MenuItemData("3", "Seblak", "Price :10.000,00", "Stok : -"),
            MenuItemData("4", "Es Teh", "Price : 5.000,00", "Stok : 200"),
            MenuItemData("5", "Pop Ice", "Price : 5.000,00", "Stok : 600"),
            MenuItemData("6", "Ayam Geprek", "Price :22.000,00", "Stok : -", isActive = false)
        )
    }

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Menu",
                            color = primaryPink,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle Back Action */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = primaryPink
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )
                // Garis tipis di bawah header mirip desain
                HorizontalDivider(thickness = 1.dp, color = primaryPink.copy(alpha = 0.3f))
            }
        },
        bottomBar = {
            MenuBottomBar(primaryPink)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Area konten utama putih
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 1. SEARCH BAR
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = searchBarBg,
                    focusedContainerColor = searchBarBg,
                    unfocusedBorderColor = primaryPink,
                    focusedBorderColor = primaryPink,
                    cursorColor = primaryPink
                ),
                placeholder = {
                    Text("Search...", color = primaryPink.copy(alpha = 0.7f))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White // Icon putih sesuai gambar
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. TOMBOL TAMBAH MENU
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = { /* Handle Tambah */ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryPink),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("+ Tambah Menu", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. LIST ITEM MENU
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(menuList) { item ->
                    MenuItemCard(
                        item = item,
                        primaryPink = primaryPink,
                        editColor = editBtnColor to editTextColor,
                        deleteColor = deleteBtnColor to deleteTextColor,
                        activeColor = activeBtnColor to activeTextColor
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItemData,
    primaryPink: Color,
    editColor: Pair<Color, Color>,
    deleteColor: Pair<Color, Color>,
    activeColor: Pair<Color, Color>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, primaryPink.copy(alpha = 0.5f)), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Nama Menu
            Text(
                text = item.name,
                color = primaryPink,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Row untuk Detail Harga/Stok dan Tombol Aksi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Kiri: Harga & Stok
                Column {
                    Text(
                        text = item.priceString,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = item.stockString,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                // Kanan: Tombol Aksi (Edit, Status, Hapus)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    // Tombol Edit
                    MenuActionButton(text = "Edit", bgColor = editColor.first, txtColor = editColor.second)

                    // Tombol Status (Nonaktif / Aktifkan)
                    if (item.isActive) {
                        MenuActionButton(text = "Nonaktif", bgColor = deleteColor.first, txtColor = deleteColor.second)
                    } else {
                        MenuActionButton(text = "Aktifkan", bgColor = activeColor.first, txtColor = activeColor.second)
                    }

                    // Tombol Hapus
                    MenuActionButton(text = "Hapus", bgColor = deleteColor.first, txtColor = deleteColor.second)
                }
            }
        }
    }
}

@Composable
fun MenuActionButton(text: String, bgColor: Color, txtColor: Color) {
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(50), // Bentuk pil/capsule
        modifier = Modifier
            .height(24.dp)
            .clickable { /* Handle click */ }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = text,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = txtColor
            )
        }
    }
}

@Composable
fun MenuBottomBar(primaryColor: Color) {
    // Menggunakan NavigationBar yang dimodifikasi agar mirip desain (Rounded Top corners)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        color = primaryColor
    ) {
        NavigationBar(
            containerColor = primaryColor,
            contentColor = Color.White,
            tonalElevation = 0.dp
        ) {
            // Dashboard
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard", modifier = Modifier.size(28.dp)) },
                label = { Text("Dashboard", fontSize = 10.sp) },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
            // Order
            NavigationBarItem(
                icon = {
                    BadgedBox(badge = { Badge(containerColor = Color.Red, contentColor = Color.White) { Text("5") } }) {
                        Icon(Icons.Default.ReceiptLong, contentDescription = "Order", modifier = Modifier.size(28.dp))
                    }
                },
                label = { Text("Order", fontSize = 10.sp) },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
            // Menu (TERPILIH)
            NavigationBarItem(
                icon = { Icon(Icons.Default.Fastfood, contentDescription = "Menu", modifier = Modifier.size(28.dp)) }, // Menggunakan icon Fastfood/MenuBook
                label = { Text("Menu", fontSize = 10.sp) },
                selected = true,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primaryColor, // Icon jadi pink
                    selectedTextColor = Color.White,
                    indicatorColor = Color.White,     // Bulatan belakang jadi putih
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
            // Pengguna
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Pengguna", modifier = Modifier.size(28.dp)) },
                label = { Text("Pengguna", fontSize = 10.sp) },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}
