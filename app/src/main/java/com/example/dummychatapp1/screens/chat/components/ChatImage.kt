package com.example.dummychatapp1.screens.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ChatImage(
    imageUrl: String,
    maxWidthFraction: Float = 0.6f // 60% màn hình
) {
    val context = LocalContext.current
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val density = LocalContext.current.resources.displayMetrics.density
    val maxWidthPx = screenWidth * maxWidthFraction

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Chat Image",
        loading = {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray)
            )
        },
        error = {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_report_image),
                contentDescription = "Error image"
            )
        },
        success = { imageState ->
            val painter = imageState.painter
            val intrinsicSize = painter.intrinsicSize
            val scale = if (intrinsicSize.width > 0 && intrinsicSize.height > 0) {
                val targetWidth = intrinsicSize.width
                val targetHeight = intrinsicSize.height
                if (targetWidth > maxWidthPx) {
                    maxWidthPx / targetWidth
                } else {
                    1f
                }
            } else {
                1f
            }

            val displayWidth = (painter.intrinsicSize.width * scale / density).dp
            val displayHeight = (painter.intrinsicSize.height * scale / density).dp

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(displayWidth)
                    .height(displayHeight)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    )
}
