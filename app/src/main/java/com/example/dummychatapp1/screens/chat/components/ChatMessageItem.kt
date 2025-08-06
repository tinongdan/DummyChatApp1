package com.example.dummychatapp1.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.dummychatapp1.model.Message
import com.example.dummychatapp1.ui.theme.BgColor
import com.example.dummychatapp1.utils.components.VerticalSpace
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@Composable
fun ChatMessageItem(
    model: Message,
    isCurrentUser: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            horizontalAlignment = if (isCurrentUser) Alignment.End else Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = if (isCurrentUser) Color(0xFFDCF8C6) else BgColor,
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomEnd = if (isCurrentUser) 0.dp else 16.dp,
                            bottomStart = if (isCurrentUser) 16.dp else 0.dp
                        )
                    )
                    .padding(12.dp)
                    .widthIn(max = 280.dp)
            ) {
                Column {
                    Text(
                        text = model.content,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatTimestamp(model.sendAt),
                        fontSize = 11.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
    VerticalSpace(6)
}

fun formatTimestamp(timestamp: Long): String {
    val date = Date(
        if (timestamp < 1000000000000) timestamp * 1000 else timestamp
    )
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}