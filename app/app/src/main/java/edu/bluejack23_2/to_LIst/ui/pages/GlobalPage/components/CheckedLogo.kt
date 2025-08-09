package edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CheckedLogo(isDone: Boolean, onClick: () -> Unit) {

    val color = Color.Black

    Box(
        modifier = Modifier
            .clickable { onClick() }
    ){
        if (isDone) {
            Icon(
                imageVector = Icons.Outlined.CheckBox,
                contentDescription = "Task Done",
                tint = color,
            )

        } else {
            Icon(
                imageVector = Icons.Outlined.CheckBoxOutlineBlank,
                contentDescription = "Task not done",
                tint = color
            )
        }
    }

}