package edu.bluejack23_2.to_LIst.ui.pages.SettingsPage

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.core.view.Change
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.ChangePasswordButton
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import edu.bluejack23_2.to_LIst.viewmodel.ProfileViewModel.ChangePasswordViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPageComposable(dismiss: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var openChangePassword by remember { mutableStateOf(false) }
    Box(modifier = UISettings.fullScreenModifier.padding(20.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
//            ChangePasswordButton(onClick = { openChangePassword = true }, modifier = Modifier.fillMaxWidth())
//            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val response = UserRepositories.deleteUser()
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Account")
            }
        }
    }

    if (openChangePassword) {
        FullScreenDialog(onDismiss = { openChangePassword = false }) {
            Scaffold(
                topBar = {
                    Column {
                        CenterAlignedTopAppBar(
                            title = { Text("Change Password") },
                            navigationIcon = {
                                IconButton(onClick = { openChangePassword = false }) {
                                    Icon(Icons.Filled.Close, contentDescription = "Close")
                                }
                            }
                        )
                        Divider(color = Color.Gray, thickness = (0.5).dp)
                    }

                }
            ) { innerPadding ->
                Column(modifier = UISettings.fullScreenModifier.padding(innerPadding)) {
                    ChangePasswordForm {
                        openChangePassword = false
                    }
                }

            }
        }
    }

}


@Composable
fun ChangePasswordForm(dismiss: () -> Unit) {
    val changePasswordViewModel: ChangePasswordViewModel = viewModel()
    val context = LocalContext.current
    var coroutineScope = rememberCoroutineScope()
    var verificated by remember {
        mutableStateOf(false)
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ){
        Spacer(modifier = Modifier.height(20.dp))

        if (!verificated) {
            OutlinedTextField(
                value = changePasswordViewModel.verificationInput,
                onValueChange = { input -> changePasswordViewModel.verificationInput = input },
                label = { Text(text = "OTP Code")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ){
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            verificated = changePasswordViewModel.checkVerification()
                        }
                    },
                    colors = ButtonColors(
                        containerColor = UISettings.primaryColor,
                        contentColor = UISettings.buttonTextColor,
                        disabledContentColor = UISettings.primaryColor,
                        disabledContainerColor = UISettings.buttonTextColor
                    )
                ) {
                    Text(text = "Check OTP")
                }

                Spacer(modifier = Modifier.width(15.dp))

                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            changePasswordViewModel.sendVerification()
                        }
                    },
                    colors = ButtonColors(
                        containerColor = UISettings.primaryColor,
                        contentColor = UISettings.buttonTextColor,
                        disabledContentColor = UISettings.primaryColor,
                        disabledContainerColor = UISettings.buttonTextColor
                    )
                ) {
                    Text(text = "Send OTP")
                }
            }


        } else {
            OutlinedTextField(value = changePasswordViewModel.password, onValueChange = {
                changePasswordViewModel.password = it
            }, label = { Text(text = "Your Password") })

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = changePasswordViewModel.newPassword, onValueChange = {
                changePasswordViewModel.newPassword = it
            }, label = { Text(text = "New Password") })

            ElevatedButton(onClick = {
                coroutineScope.launch {
                    val response = changePasswordViewModel.clickHandle()
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }
            },
                colors = ButtonColors(
                    containerColor = UISettings.primaryColor,
                    contentColor = UISettings.buttonTextColor,
                    disabledContentColor = UISettings.primaryColor,
                    disabledContainerColor = UISettings.buttonTextColor
                )) {
                Text(text = "Change Password")
            }

            ElevatedButton(onClick = {
                dismiss()
            },
                colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor = UISettings.buttonTextColor,
                    disabledContentColor = Color.Red,
                    disabledContainerColor = UISettings.buttonTextColor
                )) {
                Text(text = "Cancel")
            }
        }
    }
}