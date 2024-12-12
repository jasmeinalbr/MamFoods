package com.example.mamfoods.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun ChooseLocationScreen(
    onNextHomeClick: () -> Unit
) {
    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth * 0.06f),
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.1f))
            // Judul
            Text(
                text = "Choose Your Location",
                fontFamily = YeonSung,
                fontSize = (screenWidth * 0.07f).value.sp,
                color = RedPrimary,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Dropdown
            var expanded by remember { mutableStateOf(false) }
            var selectedLocation by remember { mutableStateOf("Cibiru") }
            // List of districts in Bandung
            val locations = listOf(
                "Cibiru", "Bandung Wetan", "Bandung Kulon", "Andir", "Bojonegara", "Kiaracondong",
                "Ujungberung", "Cidadap", "Batununggal", "Buahbatu", "Lengkong", "Panyileukan",
                "Cimahi", "Cililin", "Sukajadi", "Rancasari", "Sukapura", "Kebon Kelapa"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .clickable { expanded = !expanded }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedLocation,
                        color = Color.Black,
                        fontFamily = Lato
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(screenWidth * 0.88f).height(200.dp)
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            onClick = {
                                selectedLocation = location
                                expanded = false
                            }
                        ) {
                            Text(
                                text = location,
                                color = Color.Black,
                                fontFamily = Lato
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.25f))

            // Deskripsi
            Text(
                text = "To provide you with the best experience, we need your permission to access your device's location. Please select your location in Bandung City. This will allow us to show you a list of restaurants in Bandung, track your order, and ensure your food is delivered quickly and efficiently. Enjoy the convenience of ordering from top restaurants exclusively in Bandung!",
                style = SubText,
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontSize = (screenWidth * 0.04f).value.sp
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.1f))

            Button(
                onClick = {
                    onNextHomeClick()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
                    .height(screenHeight * 0.07f),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
            ) {
                androidx.compose.material3.Text(
                    text = "Next",
                    color = Color.White,
                    fontFamily = YeonSung,
                    fontSize = (screenWidth * 0.05f).value.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewLocation() {
    ChooseLocationScreen(
        onNextHomeClick = {}
    )
}
