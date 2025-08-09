package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class) // Opt in for experimental Material 3 APIs
@Composable
fun UpdateToDoListButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Transparent) // Use MaterialTheme color for consistency
            .clickable(onClick = onClick, enabled = true)
    ) {
        Icon(
            imageVector = Icons.Outlined.Edit,
            contentDescription = "Update To Do List",
            tint = Color.Black,
        )
    }
}