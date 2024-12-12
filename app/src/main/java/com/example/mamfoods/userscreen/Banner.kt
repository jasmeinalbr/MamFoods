// Banner.kt
package com.example.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingBanner(images: List<Painter>) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-slide logic
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // 3 seconds delay
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(200.dp)
            .aspectRatio(16 / 9f)
    ) {
        // Pager for sliding images
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent) // Placeholder for image
            ) {
                Image(
                    painter = images[page],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Dots indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            activeColor = Color.Red,
            inactiveColor = Color.Gray
        )
    }
}
