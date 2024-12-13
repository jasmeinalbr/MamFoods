package com.example.mamfoods.adminscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mamfoods.ui.theme.TitleText

@Composable
fun OutForDeliveryScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header dengan tombol Back dan judul di tengah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol Back
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            // Judul di tengah
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Out for Delivery",
                style = TitleText,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(5f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar pengiriman
        val deliveries = listOf(
            Triple("Customer 1", true, true),  // Sudah dikirim dan sudah dibayar
            Triple("Customer 2", false, false) // Belum dikirim dan belum dibayar
        )

        deliveries.forEach { (customer, status, paymentStatus) ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nama Customer
                    Text(customer, style = MaterialTheme.typography.bodyLarge)

                    // Icon status pengiriman
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (status) "Delivered" else "Not Delivered",
                            color = if (status) Color.Green else Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(if (status) Color.Green else Color.Red, CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Status pembayaran
                Text(
                    text = if (paymentStatus) "Payment Completed" else "Payment Pending",
                    color = if (paymentStatus) Color.Green else Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOutForDeliveryScreen() {
    OutForDeliveryScreen(onBackClick = {})
}

