package edu.bluejack23_2.to_LIst.ui.pages.CreateToDoList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

class CreateToDoList {
    @Composable
    fun CreateToDoListComposable(navController: NavController) {
        Box(modifier = UISettings.fullScreenModifier.padding(30.dp)) {
            Column {
                FormCreateToDoList(navController)
            }
        }
    }
}