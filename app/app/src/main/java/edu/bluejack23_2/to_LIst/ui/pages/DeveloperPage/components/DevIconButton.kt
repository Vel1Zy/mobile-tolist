package edu.bluejack23_2.to_LIst.ui.pages.DeveloperPage.components


import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DevIconButton(onClick: () -> Unit) {

    Button(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Code,  // Replace with your chosen icon
            contentDescription = "Developer",
            modifier = Modifier.size(16.dp) // Set appropriate size
        )
    }
}