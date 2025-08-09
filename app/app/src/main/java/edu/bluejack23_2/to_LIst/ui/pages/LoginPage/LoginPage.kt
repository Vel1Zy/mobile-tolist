package edu.bluejack23_2.to_LIst.ui.pages.LoginPage

import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import edu.bluejack23_2.to_LIst.viewmodel.LoggedInViewModel.LoggedInViewModel
import edu.bluejack23_2.to_LIst.viewmodel.LoginViewModel.LoginViewModel
import kotlinx.coroutines.launch

class LoginPage {
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @Composable
    fun LoginComposable(navController: NavController) {
        val context = LocalContext.current
        val loginViewModel: LoginViewModel = viewModel()
        val loggedInViewModel: LoggedInViewModel = viewModel()
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(UISettings.pagePadding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Login",
                    fontSize = UISettings.titleFontSize,
                    fontWeight = UISettings.titleFontWeight,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(50.dp))

                Column {
                    OutlinedTextField(
                        value = loginViewModel.userLogin.email,
                        onValueChange = { input -> loginViewModel.setUsername(input) },
                        label = { Text(text = "Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = loginViewModel.userLogin.password,
                        onValueChange = { input -> loginViewModel.setPassword(input) },
                        label = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            val result = loginViewModel.loginButtonClicked()

                            if (result.data == true) {
                                LoggedInViewModel.setLoggedIn(true)
                                GlobalViewModel.setUserLoggedIn(true)
                                navController.navigate("home")
                            }

                            Toast.makeText(
                                context,
                                result.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = UISettings.primaryColor,
                        contentColor = UISettings.buttonTextColor,
                        disabledContentColor = UISettings.primaryColor,
                        disabledContainerColor = UISettings.buttonTextColor
                    )
                ) {
                    Text(
                        text = "Login"
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(text = "Don't have an Account?", fontSize = UISettings.bottomFontSize)
                    Spacer(modifier = Modifier.width(3.dp))
                    Box(modifier = Modifier.clickable { navController.navigate("register") }) {
                        Text(
                            text = "Register Here",
                            style = TextStyle(textDecoration = TextDecoration.Underline),
                            fontSize = UISettings.bottomFontSize,
                            color = UISettings.primaryColor
                        )
                    }
                }
            }
        }
    }

}