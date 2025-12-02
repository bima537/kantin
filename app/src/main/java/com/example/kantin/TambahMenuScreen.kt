package com.example.kantin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// --- 1. PERUBAHAN DI SINI: TAMBAHKAN PARAMETER NAVCONTROLLER ---
@Composable
fun AddMenuScreen(navController: NavController) {
    // Warna tema
    val backgroundColor = Color(0xFFFFF5F6)
    val primaryPink = Color(0xFFD68C9A)

    // State untuk input form
    var namaMenu by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var statusAktif by remember { mutableStateOf(true) } // true = Aktif, false = Nonaktif

    Scaffold(
        containerColor = backgroundColor,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // --- HEADER ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = primaryPink,
                        modifier = Modifier
                            .size(24.dp)
                            // --- 2. PERUBAHAN DI SINI: PANGGIL popBackStack() ---
                            .clickable { navController.popBackStack() }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Tambah Menu",
                        color = primaryPink,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                }
                HorizontalDivider(color = primaryPink, thickness = 1.dp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- FORM INPUT ---
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {

                // (Kode FormInput tidak berubah...)
                // 1. Nama Menu
                FormRow(label = "Nama Menu", primaryPink = primaryPink) {
                    CustomTextField(value = namaMenu, onValueChange = { namaMenu = it }, placeholder = "Nasi Bakar Kemangi", primaryPink = primaryPink)
                }
                Spacer(modifier = Modifier.height(16.dp))
                // 2. Harga
                FormRow(label = "Harga", primaryPink = primaryPink) {
                    CustomTextField(value = harga, onValueChange = { harga = it }, placeholder = "15.000.00", primaryPink = primaryPink, keyboardType = KeyboardType.Number)
                }
                Spacer(modifier = Modifier.height(16.dp))
                // 3. Unggah Foto
                FormRow(label = "Unggah Foto", primaryPink = primaryPink) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(100.dp, 120.dp).background(Color(0xFFFFF0F0), RoundedCornerShape(8.dp)).border(1.dp, primaryPink.copy(alpha = 0.5f), RoundedCornerShape(8.dp)).clickable { /* Handle Upload Logic */ }, contentAlignment = Alignment.Center) {
                            Text(text = "Unggah", color = primaryPink, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh", tint = primaryPink, modifier = Modifier.size(32.dp).clickable { /* Handle Refresh/Reset */ })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // 4. Status
                FormRow(label = "Status", primaryPink = primaryPink) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { statusAktif = true }) {
                            RadioButton(selected = statusAktif, onClick = { statusAktif = true }, colors = RadioButtonDefaults.colors(selectedColor = primaryPink, unselectedColor = primaryPink.copy(alpha = 0.6f)))
                            Text(text = "Aktif", color = primaryPink, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { statusAktif = false }) {
                            RadioButton(selected = !statusAktif, onClick = { statusAktif = false }, colors = RadioButtonDefaults.colors(selectedColor = primaryPink, unselectedColor = primaryPink.copy(alpha = 0.6f)))
                            Text(text = "Nonaktif", color = primaryPink, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                // --- TOMBOL ACTION ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Tombol Tambah
                    Button(onClick = { /* Handle Tambah */ }, modifier = Modifier.weight(1f).height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = primaryPink), shape = RoundedCornerShape(8.dp)) {
                        Text("Tambah", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Tombol Batal
                    OutlinedButton(
                        // --- 3. PERUBAHAN DI SINI: PANGGIL popBackStack() ---
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                        border = androidx.compose.foundation.BorderStroke(1.dp, primaryPink),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Batal", color = primaryPink, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

// (Komponen FormRow dan CustomTextField tidak berubah...)
@Composable
fun FormRow(label: String, primaryPink: Color, content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Surface(color = primaryPink.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp), modifier = Modifier.width(100.dp).height(36.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
        Box(modifier = Modifier.weight(1f)) { content() }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, primaryPink: Color, keyboardType: KeyboardType = KeyboardType.Text) {
    OutlinedTextField(value = value, onValueChange = onValueChange, placeholder = { Text(text = placeholder, color = primaryPink.copy(alpha = 0.7f), fontSize = 14.sp, fontWeight = FontWeight.Bold) }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(8.dp), colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = primaryPink.copy(alpha = 0.5f), focusedBorderColor = primaryPink, unfocusedContainerColor = Color(0xFFFFF0F0), focusedContainerColor = Color.White, focusedTextColor = primaryPink, unfocusedTextColor = primaryPink), keyboardOptions = KeyboardOptions(keyboardType = keyboardType), singleLine = true, textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = primaryPink))
}


@Preview(showBackground = true)
@Composable
fun AddMenuScreenPreview() {
    // Preview tidak akan error karena kita bisa membuat NavController palsu
    AddMenuScreen(navController = rememberNavController())
}