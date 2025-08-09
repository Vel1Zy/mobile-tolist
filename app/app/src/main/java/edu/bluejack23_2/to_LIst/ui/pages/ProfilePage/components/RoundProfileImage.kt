package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

@Composable
fun RoundProfileImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile Picture",
        modifier = modifier
            .clip(CircleShape)
            .size(240.dp)
            .border(3.dp, UISettings.topNavBarColor, CircleShape),
        contentScale = ContentScale.Crop
    )

}