package com.example.mamfoods.adminscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mamfoods.ui.theme.TitleText

// Mendefinisikan warna background untuk tombol
val RedGradient3 = Color(0xFFE85353)

@Composable
fun AllItemsScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header dengan tombol Back di kiri atas dan judul di tengah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Spacer untuk mengatur jarak

            Text(
                text = "All Items",
                style = TitleText,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(5f)
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer tambahan
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar item
        val items = listOf(
            Pair("Love and Coffee", "https://assets-a1.kompasiana.com/statics/crawl/555d4de00423bde5188b4567.jpeg"),
            Pair("Tenderloin Steak", "https://asset.kompas.com/crops/rPrzci9RRSeRCRNXG8yFyaEcNN8=/100x77:900x610/1200x800/data/photo/2022/05/21/62887abe8f50f.jpg"),
            Pair("Mociii", "https://cdn.rri.co.id/berita/Palangkaraya/o/1720844152016-FB_IMG_1720843523754/vrobe818xjgq2op.jpeg")
        )

        items.forEach { item ->
            ItemRow(item)
        }
    }
}

@Composable
fun ItemRow(item: Pair<String, String>) {
    var quantity by remember { mutableStateOf(1) } // State untuk mengatur jumlah item

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(), // Ganti IntrinsicSize.Min dengan wrapContentHeight
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Gambar Item (menggunakan URL)
        val painter = rememberAsyncImagePainter(item.second)

        Image(
            painter = painter,
            contentDescription = item.first,
            modifier = Modifier
                .size(80.dp) // Ukuran gambar yang diinginkan
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop // Gambar akan dipotong sesuai dengan ukuran
        )

        // Nama Item
        Column(
            modifier = Modifier
                .weight(1f) // Memastikan nama item mengambil sisa ruang
        ) {
            Text(item.first) // Judul Item
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Kontrol untuk mengubah kuantitas
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f) // Menambahkan weight untuk memastikan tombol berada di tengah
        ) {
            // Tombol untuk mengurangi kuantitas (ikon minus)
            IconButton(
                onClick = {
                    if (quantity > 1) quantity -= 1 // Mengurangi kuantitas jika lebih dari 1
                },
                modifier = Modifier
                    .size(30.dp) // Mengatur ukuran tombol tambah/kurang
                    .background(RedGradient3, shape = CircleShape) // Memberikan background pada tombol
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove, // Ikon minus untuk pengurangan
                    contentDescription = "Decrease Quantity",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Menampilkan jumlah item
            Text(
                text = "$quantity",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Tombol untuk menambah kuantitas (ikon plus)
            IconButton(
                onClick = {
                    quantity += 1 // Menambah kuantitas
                },
                modifier = Modifier
                    .size(30.dp) // Ukuran tombol tambah/kurang disamakan
                    .background(RedGradient3, shape = CircleShape) // Memberikan background pada tombol
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, // Ikon plus untuk penambahan
                    contentDescription = "Increase Quantity",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        // Tombol untuk menghapus item (keranjang sampah) - Diletakkan di kanan
        IconButton(
            onClick = { /* Logic untuk menghapus item */ },
            modifier = Modifier
                .size(31.dp) // Ukuran tombol delete disesuaikan
                .padding(start = 4.dp)
                .background(RedGradient3, shape = CircleShape) // Memberikan background pada tombol delete
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete, // Tombol untuk menghapus item
                contentDescription = "Delete Item",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAllItemScreen() {
   AllItemsScreen {  }
}

