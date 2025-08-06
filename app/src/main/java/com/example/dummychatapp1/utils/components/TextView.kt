package com.example.dummychatapp1.utils.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextView(
    value: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: Int = 18,
    color: Color = Color.Black,
    modifier: Modifier = Modifier,

    ) {
    Text(
        text = value,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        color = color,
        modifier = modifier
    )
}