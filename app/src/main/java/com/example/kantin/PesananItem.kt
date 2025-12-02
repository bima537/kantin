package com.example.kantin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PesananItem(
    data: PesananData,
    primaryPink: Color,
    buttonColor: Color,
    dotColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, primaryPink.copy(alpha = 0.6f)), // Border pink tipis
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Indikator Titik Merah
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 2. Foto Profil
            Image(
                painter = painterResource(id = data.imageRes),
                contentDescription = data.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray) // Placeholder jika gambar loading
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 3. Nama & Waktu
            // Menggunakan Row agar nama dan waktu sejajar horizontal
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f) // Mengisi ruang kosong agar tombol terdorong ke kanan
            ) {
                Text(
                    text = data.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryPink
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = data.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // 4. Tombol Deskripsi
            Button(
                onClick = { /* TODO: Handle Click */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp), // Padding dalam tombol
                modifier = Modifier.height(32.dp) // Tinggi tombol dibuat kecil
            ) {
                Text(
                    text = "Deskripsi",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}
