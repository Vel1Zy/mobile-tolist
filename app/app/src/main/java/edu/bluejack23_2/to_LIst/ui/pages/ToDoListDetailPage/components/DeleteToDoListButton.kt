package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DeleteToDoListButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .clickable(onClick = onClick, enabled = true)
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            tint = Color.Red
        )
    }
}