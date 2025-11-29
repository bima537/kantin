package com.example.kantin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMenuScreen(
    onNavigateBack: () -> Unit = {}
) {
    // Definisi Warna sesuai request
    val backgroundColor = Color(0xFFFFF5F6)
    val primaryPink = Color(0xFFD68C9A)
    val inputBackgroundPink = Color(0xFFFFF0F2) // Warna latar input field yang sedikit lebih terang

    // State untuk input fields
    var menuName by remember { mutableStateOf("Mie Goreng") }
    var price by remember { mutableStateOf("Rp. 10.000,00") }
    var stock by remember { mutableStateOf("100") }

    Scaffold(
        containerColor = Color.White, // Bagian tengah konten putih seperti di gambar
        topBar = {
            // Custom Top Bar
            Column {
                // Status bar area filler (optional visual match)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(primaryPink)
                )
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Edit Menu",
                            color = primaryPink,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Kembali",
                                tint = primaryPink
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.shadow(4.dp)
                )
            }
        },
        bottomBar = {
            // Bottom Bar Admin yang sudah ada (menggunakan kembali AdminBottomBar dari HomeAdmin.kt)
//            AdminBottomBar(primaryColor = primaryPink)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .background(Color.White), // Background konten utama putih
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // 1. Input Nama Menu
            CustomInputField(
                value = menuName,
                onValueChange = { menuName = it },
                borderColor = primaryPink,
                backgroundColor = inputBackgroundPink,
                textStyle = TextStyle(
                    color = primaryPink,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.height(60.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Input Harga
            CustomLabeledInput(
                label = "Harga",
                value = price,
                onValueChange = { price = it },
                borderColor = primaryPink,
                backgroundColor = inputBackgroundPink,
                textColor = primaryPink
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Input Persediaan
            CustomLabeledInput(
                label = "Persediaan",
                value = stock,
                onValueChange = { stock = it },
                borderColor = primaryPink,
                backgroundColor = inputBackgroundPink,
                textColor = primaryPink
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 4. Bagian Gambar
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd // Align gambar ke kanan seperti referensi
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .border(1.dp, primaryPink, RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    ) {
                        // Placeholder Image - Ganti dengan logic upload gambar/load gambar asli
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                            contentDescription = "Menu Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }

                    IconButton(onClick = { /* Logic ganti gambar */ }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Ganti Gambar",
                            tint = primaryPink,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Dorong tombol ke bawah

            // 5. Tombol Aksi (Perbarui, Hapus, Batalkan)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionButton(
                    text = "Perbarui",
                    backgroundColor = primaryPink,
                    onClick = { /* Logic Perbarui */ }
                )

                ActionButton(
                    text = "Hapus",
                    backgroundColor = primaryPink,
                    onClick = { /* Logic Hapus */ }
                )

                ActionButton(
                    text = "Batalkan",
                    backgroundColor = primaryPink,
                    onClick = onNavigateBack
                )
            }
        }
    }
}

// --- Komponen Pembantu ---

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    borderColor: Color,
    backgroundColor: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomLabeledInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    borderColor: Color,
    backgroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    backgroundColor: Color,
    TextColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.width(100.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditMenuScreenPreview() {
    EditMenuScreen()
}
