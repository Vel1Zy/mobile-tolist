package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

@Composable
fun RoundedBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit

){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = UISettings.secondaryColor,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        content()
    }

}