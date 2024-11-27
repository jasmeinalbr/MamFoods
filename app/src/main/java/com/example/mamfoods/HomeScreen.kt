package com.example.mamfoods

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.PriceText
import com.example.mamfoods.ui.theme.SubText
import kotlinx.coroutines.delay
import com.google.accompanist.pager.*
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeScreen(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        // Periksa apakah pengguna sudah login
        if (auth.currentUser == null) {
            // Arahkan ke layar login jika belum login
            navController.navigate("login") {
                popUpTo("home") { inclusive = true } // Menghapus stack sebelumnya
            }
        }
    }
    Column (modifier = Modifier.fillMaxSize()){
        // Bagian Search Bar
       // SearchBar()


        // Bagian Banner
        BannerSection()

        // Bagian Popular Products
        PopularSection(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSection() {
    val banners = listOf("Banner 1", "Banner 2", "Banner 3") // Simulasi data
    val pagerState = rememberPagerState(0)

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // 3 detik
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % banners.size)
        }
    }

    HorizontalPager(
        count = banners.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray) // Placeholder warna
        ) {
            Text(
                text = banners[page],
                modifier = Modifier.align(Alignment.Center),
                style = SubText
            )
        }
    }
}

@Composable
fun PopularSection(navController: NavHostController) {
    val products = listOf(
        Product("Pancake", "Cake Castle", 20000),
        Product("Matcha Latte", "Wijie Resto", 15000),
        Product("Mie Hijau", "Warung Mie", 25000)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Popular", style = BodyText)
            Text(
                "View More",
                modifier = Modifier.clickable { navController.navigate("popular") },
                color = Color.Blue
            )
        }

        Spacer(Modifier.height(8.dp))

        products.forEach { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Navigate to detail */ }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
        ) // Placeholder
        Spacer(Modifier.width(16.dp))
        Column {
            Text(product.name, style = BodyText)
            Text(product.restaurant, style = BodyText)
            Text("${product.price}k", style = PriceText)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewL() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}