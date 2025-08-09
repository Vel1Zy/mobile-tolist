package edu.bluejack23_2.to_LIst.ui.pages.RegisterPage

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.RegisterViewModel.RegisterViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun RegisterForm() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val registerViewModel: RegisterViewModel = viewModel()
    val inputSpacing = 30.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Register",
                fontSize = UISettings.titleFontSize,
                fontWeight = UISettings.titleFontWeight
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = registerViewModel.userRegister.username,
                onValueChange = { usernameInput -> registerViewModel.updateUsername(usernameInput) },
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(inputSpacing))

            OutlinedTextField(
                value = registerViewModel.userRegister.email,
                onValueChange = { emailInput -> registerViewModel.updateEmail(emailInput) },
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(inputSpacing))

            OutlinedTextField(
                value = registerViewModel.userRegister.password,
                onValueChange = { passwordInput -> registerViewModel.updatePassword(passwordInput) },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(inputSpacing))


            OutlinedTextField(
                value = registerViewModel.userRegister.confirmPassword,
                onValueChange = { input -> registerViewModel.updateConfirmPassword(input) },
                label = { Text(text = "Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(inputSpacing))


            ElevatedButton(
                onClick = {
                    coroutineScope.launch {
                        val validationResult = registerViewModel.registerButtonClicked()
                        Toast.makeText(context, validationResult.message, Toast.LENGTH_SHORT).show()
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
                Text(text = "Register")
            }
        }
    }
}
