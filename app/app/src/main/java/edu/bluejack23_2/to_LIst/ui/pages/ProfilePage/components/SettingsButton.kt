package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

@Composable
fun SettingsButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(

            imageVector = Icons.Outlined.Settings,
            contentDescription = "Settings",
            modifier = Modifier.size(36.dp),
            tint = Color.Gray

        )
    }
}
