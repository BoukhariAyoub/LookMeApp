package com.lookmyup.app.ui.shared

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonWithBadge(
    icon: ImageVector,
    iconSelected: ImageVector,
    isSelected: Boolean,
    count: Int,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Text(count.toString())
                    }
                }
            }
        ) {
            IconButton(
                modifier = Modifier.padding(0.dp),
                onClick = onClick
            ) {
                AnimatedContent(targetState = isSelected, label = "Icon Animation") { selected ->
                    Icon(
                        imageVector = if (selected) iconSelected else icon,
                        contentDescription = "Action",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}