package edu.bluejack23_2.to_LIst.ui.pages.RegisterPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

class RegisterPage() {

    @Composable
    fun RegisterComposable(navController: NavController) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RegisterForm()
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "Already Have An Account?", fontSize = UISettings.bottomFontSize)
                    Spacer(modifier = Modifier.width(3.dp))
                    Box(modifier = Modifier.clickable { navController.navigate("login") }) {
                        Text(
                            text = "Login Here",
                            style = TextStyle(textDecoration = TextDecoration.Underline),
                            fontSize = UISettings.bottomFontSize,
                            color = Color.Blue
                        )
                    }
                }
            }
        }
    }
}