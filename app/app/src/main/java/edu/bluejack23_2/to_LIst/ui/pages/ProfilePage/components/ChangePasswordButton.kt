package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

@Composable
fun ChangePasswordButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = UISettings.primaryColor,
            contentColor = Color.White
        )
    ) {

        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit",
            tint = Color.White
        )
        Text(text = "  Change Password")
    }
}