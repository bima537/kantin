package com.example.kantin

import KantinTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

// Nama variabel warna unik untuk file ini
private val checkoutBackgroundColor = Color(0xFFFFF6F4)
private val checkoutPrimaryColor = Color(0xFFDE97A5)
private val checkoutTextColor = Color(0xFF888888)
private val checkoutTitleColor = Color(0xFFAD5E6D)

// Data class unik untuk file ini
data class CheckoutItem(
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int
)

// Data dummy (nama gambar tidak diubah)
val sampleCheckoutItems = listOf(
    CheckoutItem("Sempol Ayam", "Deskripsi : 5 pcs", 5000.0, R.drawable.arah),
    CheckoutItem("Aneka Gorengan", "Deskripsi : 5 pcs", 5000.0, R.drawable.arah),
    CheckoutItem("Mie Goreng", "Deskripsi : Pakai telur 2", 10000.0, R.drawable.arah)
)

// Fungsi format harga unik untuk file ini
fun formatPriceToRupiah(price: Double): String {
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    format.maximumFractionDigits = 0
    return format.format(price).replace("Rp", "Rp ")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembayaranScreen(
    orderItems: List<CheckoutItem>,
    onBackPressed: () -> Unit = {},
    onPesanClicked: () -> Unit = {}
) {
    val totalItems = orderItems.size
    val totalPrice = orderItems.sumOf { it.price }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Pembayaran",
                        modifier = Modifier.fillMaxWidth(),
                        color = checkoutTitleColor,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = checkoutTitleColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        // PERUBAHAN UTAMA: Memindahkan bagian bawah ke BottomBar
        bottomBar = {
            BottomCheckoutLayout(
                totalItems = totalItems,
                totalPrice = totalPrice,
                onPesanClicked = onPesanClicked
            )
        },
        containerColor = checkoutBackgroundColor
    ) { innerPadding ->
        // LazyColumn sekarang hanya berisi daftar item yang bisa di-scroll
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 16.dp // Tambahan padding bawah
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Header Tabel
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("ITEM", color = checkoutTextColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1.5f))
                    Text("DESKRIPSI", color = checkoutTextColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(2f))
                    Text("HARGA", color = checkoutTextColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Daftar Item Pesanan
            items(orderItems) { item ->
                CheckoutItemCard(item = item)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

// PERUBAHAN UTAMA: Composable baru untuk menampung seluruh bagian bawah
@Composable
fun BottomCheckoutLayout(
    totalItems: Int,
    totalPrice: Double,
    onPesanClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(checkoutBackgroundColor) // Latar belakang untuk area total dan pembayaran
            .padding(horizontal = 16.dp)
    ) {
        // Bagian Subtotal dan Total
        TotalCheckoutSection(totalItems, totalPrice)
        Spacer(modifier = Modifier.height(24.dp))

        // Bagian Pilihan Pembayaran
        PaymentMethodSelector()
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Pesan dan baris bawahnya
        BottomCheckoutButton(onPesanClicked)
    }
}


@Composable
fun CheckoutItemCard(item: CheckoutItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, checkoutPrimaryColor)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, fontWeight = FontWeight.Bold, color = checkoutTitleColor, fontSize = 14.sp)
                Text(item.description, color = checkoutTextColor, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                formatPriceToRupiah(item.price),
                fontWeight = FontWeight.Bold,
                color = checkoutTitleColor,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun TotalCheckoutSection(totalItems: Int, totalPrice: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier.width(200.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Subtotal ($totalItems)", color = checkoutTextColor, fontSize = 14.sp)
            Text(formatPriceToRupiah(totalPrice), color = checkoutTextColor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.width(200.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total", color = checkoutTitleColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(formatPriceToRupiah(totalPrice), color = checkoutTitleColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PaymentMethodSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(checkoutPrimaryColor)
            .clickable { /* Aksi saat metode pembayaran diklik */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Pembayaran", color = Color.White, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Tunai/Qris", color = Color.White, fontSize = 14.sp)
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Pilih Pembayaran",
                tint = Color.White
            )
        }
    }
}

// Mengganti nama BottomCheckoutSection menjadi BottomCheckoutButton
@Composable
fun BottomCheckoutButton(onPesanClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = checkoutPrimaryColor,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onPesanClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("Pesan", color = checkoutTitleColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(134.dp)
                .height(5.dp)
                .background(Color.White, shape = RoundedCornerShape(100))
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun PembayaranScreenPreview() {
    KantinTheme {
        PembayaranScreen(orderItems = sampleCheckoutItems)
    }
}

