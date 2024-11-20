package com.example.mamfoods

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun ChooseLocationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        // Judul
        Text(
            text = "Choose Your Location",
            fontFamily = YeonSung,
            fontSize = 24.sp,
            color = RedPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdown
        var expanded by remember { mutableStateOf(false) }
        var selectedLocation by remember { mutableStateOf("Cibiru") }
        val locations = listOf("Cibiru", "Bandung", "Jakarta", "Surabaya")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable { expanded = !expanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedLocation, color = Color.Black, fontFamily = Lato)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                locations.forEach { location ->
                    DropdownMenuItem(onClick = {
                        selectedLocation = location
                        expanded = false
                    }) {
                        Text(text = location)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(70.dp))

        // Deskripsi
        Text(
            text = "To provide you with the best dining experience, we need your permission to access your device's location. By enabling location services, we can offer personalized restaurant recommendations, accurate delivery estimates, and ensure a seamless food delivery experience.",
            style = SubText,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 32.dp),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocation() {
    ChooseLocationScreen()
}
