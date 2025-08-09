package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage

import androidx.compose.foundation.border
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.ui.interfaces.ProfileFormData
import edu.bluejack23_2.to_LIst.ui.pages.DeveloperPage.components.DatePickerComposable
import edu.bluejack23_2.to_LIst.ui.pages.DeveloperPage.components.DevIconButton
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.EditButton
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.RoundProfileImage
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.SettingsButton
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.UpdateProfileForm
import edu.bluejack23_2.to_LIst.ui.pages.SettingsPage.SettingsPageComposable
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import edu.bluejack23_2.to_LIst.viewmodel.LoggedInViewModel.LoggedInViewModel
import edu.bluejack23_2.to_LIst.viewmodel.ProfileViewModel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfilePage {

    //    @Preview(showBackground = true)

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProfileComposable(navController: NavController, topBarColor: Color = Color(0xfff6cefc)) {
        val coroutineScope = rememberCoroutineScope()
        val user: User? = GlobalViewModel.getUserInformation()

        val profileViewModel: ProfileViewModel = viewModel()

        var showUpdateScreen by remember { mutableStateOf(false) }
        var showSettings by remember { mutableStateOf(false) }


        // DEVELOPER SETTINGS
        var showDeveloperPage by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                Column{
                    CenterAlignedTopAppBar(
                        title = { Text(fontSize = UISettings.topBarFontSize ,text = "Profile") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = UISettings.topNavBarColor // Your desired background color
                        )
                    )
                    Divider(color = Color.Gray, thickness = (0.5).dp)

                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                contentAlignment = Alignment.CenterStart
            ) {

                Column(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        SettingsButton(onClick = { showSettings = true })
                    }
//                    if (user?.profileImageLink == "") {
//                        RoundProfileImage(imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/49/A_black_image.jpg")
//                    } else {
//                        RoundProfileImage(imageUrl = user?.profileImageLink)
//                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Surface (
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.3.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),
                        tonalElevation = (1.5).dp,
                        color = UISettings.secondaryColor
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 15.dp)
                        ) {

                            Text("Username:", style = MaterialTheme.typography.bodyMedium, color = UISettings.primaryColor)
                            Text(text = user?.username ?: "Username kosong",  fontSize = UISettings.profileTextSizePrimary)

                            Spacer(modifier = Modifier.height(6.dp))

                            Text("Email:",  style = MaterialTheme.typography.bodyMedium, color = UISettings.primaryColor)
                            Text(text = user?.email ?: "Email kosong",  fontSize = UISettings.profileTextSizeSecondary)

//                            Spacer(modifier = Modifier.height(6.dp))

//                            Text("Date of Birth:", style = MaterialTheme.typography.bodyMedium, color = UISettings.primaryColor, )
//                            Text(text = user?.dateOfBirth ?: "Tanggal kosong",fontSize = UISettings.profileTextSizeSecondary)

//                            Spacer(modifier = Modifier.height(24.dp))
//                            EditButton(onClick = { showUpdateScreen = true }, modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp))

                            Spacer(modifier = Modifier.height(5.dp))

                            Button(
                                onClick = {
                                    GlobalViewModel.logOut()
                                    navController.navigate("login")
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                            ) {
                                Text("Logout")
                            }

//                            Spacer(modifier = Modifier.height(16.dp))
//                            DevIconButton {
//                                showDeveloperPage = true
//                            }
                        }
                    }
                }
            }
        }

        if (showUpdateScreen) {
            FullScreenDialog(onDismiss = { showUpdateScreen = false }) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Update Profile") },
                            navigationIcon = {
                                IconButton(onClick = { showUpdateScreen = false }) {
                                    Icon(Icons.Filled.Close, contentDescription = "Close")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        if (user != null) {
                            UpdateProfileForm(
                                initialFormData = ProfileFormData(
                                    username = user.username,
                                    email = user.email,
                                    profilePictureUrl = user.profileImageLink,
                                    dateOfBirth = user.dateOfBirth
                                ),
                                onFormSubmit = { updatedData ->
                                    coroutineScope.launch {
                                        profileViewModel.updateUserProfile(updatedData)
                                        showUpdateScreen = false
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (showSettings) {
            FullScreenDialog(onDismiss = { showSettings = false }) {
                Scaffold(
                    topBar = {
                        Column{
                            CenterAlignedTopAppBar(
                                title = { Text("Settings") },
                                navigationIcon = {
                                    IconButton(onClick = { showSettings = false }) {
                                        Icon(Icons.Filled.Close, contentDescription = "Close")
                                    }
                                }
                            )
                            Divider(color = Color.Gray, thickness = (0.5).dp)
                        }
                    }
                ) {innerPadding->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
                        SettingsPageComposable { showSettings = false }
                    }


                }

            }
        }

        // Developer Page
        if (showDeveloperPage) {
            FullScreenDialog(onDismiss = { showDeveloperPage = false }) {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Developer Page It is") },
                            navigationIcon = {
                                IconButton(onClick = { showDeveloperPage = false }) {
                                    Icon(Icons.Filled.Close, contentDescription = "Close")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DatePickerComposable()
                    }

                }
            }
        }


    }
}