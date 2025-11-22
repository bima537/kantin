package com.example.kantin

import KantinTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeKantin () {
    val backgroundColor = Color(0xFFFFFFFF)
    val primary2Color = Color(0xFFF5F5F5)
    val primaryColor = Color(0xFFDE97A5)
    val borderColor = Color(0xFF9E3274)

    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(primaryColor)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
        )
        Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(value = query,
                onValueChange = {newText -> query = newText},
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                placeholder = { Text("Cari",
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.pencarian),
                        contentDescription = "Search Icon",
                        tint = primaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = primary2Color,
                    unfocusedContainerColor = primary2Color,
                    disabledContainerColor = primary2Color,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.hati),
                        contentDescription = "Home Icon",
                        tint = primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Favorit",
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.riwayat),
                        contentDescription = "History Icon",
                        tint = primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Riwayat",
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.pesanan),
                        contentDescription = "Pesanan Icon",
                        tint = primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Pesanan",
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.benner),
                contentDescription = "Logo Kantin",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(primaryColor)
                    .height(40.dp)
                    .border(
                        width =1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Menu Makanan",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arah),
                        contentDescription = "Arrow Icon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bima")
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun HomeKantinPreview() {
    KantinTheme {
        HomeKantin()
    }
}