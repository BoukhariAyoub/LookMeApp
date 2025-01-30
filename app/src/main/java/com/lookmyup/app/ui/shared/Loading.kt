package com.lookmyup.app.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun ShimmerLoadingComponent(
    itemHeight: Int = 20,
    itemWidth: Float = 1f,
    itemCount: Int = 3,
    isVertical: Boolean = true
) {
    if (isVertical) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(itemCount) {
                Box(
                    modifier = Modifier
                        .height(itemHeight.dp)
                        .fillMaxWidth(itemWidth)
                        .placeholder(
                            visible = true,
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer()
                        )
                )
            }
        }
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(itemCount) {
                Box(
                    modifier = Modifier
                        .height(itemHeight.dp)
                        .width((itemWidth * 100).dp)
                        .placeholder(
                            visible = true,
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer()
                        )
                )
            }
        }
    }
}