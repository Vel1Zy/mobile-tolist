package edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.runtime.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.copy
import coil.compose.AsyncImage
import edu.bluejack23_2.to_LIst.ui.interfaces.ProfileFormData
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.utils.Tools
import edu.bluejack23_2.to_LIst.viewmodel.ProfileViewModel.ProfileViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileForm(
    initialFormData: ProfileFormData,
    onFormSubmit: (ProfileFormData) -> Unit
) {
    var formData by remember { mutableStateOf(initialFormData) }
//    var showDatePicker by remember { mutableStateOf(false) }

    val profileViewModel: ProfileViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    var successMessage by remember {
        mutableStateOf("Upload Picture First!!")
    }

    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    var showDatePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = UISettings.primaryColor
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Icon(
                    Icons.Filled.ImageSearch,
                    contentDescription = "Image Picker",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text("Choose Image", color = Color.White)
            }


            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    if (uri != null) {
                        val imageUri = uri
                        coroutineScope.launch {
                            var responseResult: Response<String>? =
                                imageUri?.let {
                                    profileViewModel.uploadUserProfileImage(
                                        it,
                                        context
                                    )
                                }
                            if (responseResult?.success == true) {
                                successMessage = "Upload Picture Success!!, now you can submit"
                                formData.profilePictureUrl = responseResult.data.toString()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(200.dp) // Fixed width
                    .height(IntrinsicSize.Min),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UISettings.primaryColor
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Upload, // Upload icon from Material Icons
                    contentDescription = "Upload",
                    modifier = Modifier.size(15.dp)
                )
                Spacer(Modifier.size(12.dp)) // Add some spacing
                Text("Upload Image")
            }
        }

        RoundedBox {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = successMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = UISettings.primaryColor
                )
            }
        }

        OutlinedTextField(
            value = formData.username,
            onValueChange = { input -> formData = formData.withUsername(input) },
            label = { Text("Username") }
        )

//        var dateResult by remember { mutableStateOf("Date Of Birth") }
        var openDialog by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = formData.dateOfBirth,
            onValueChange = {},
            label = { Text("Date Of Birth") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { openDialog = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Date Picker")
                }
            }
        )


        if (openDialog) {
            val datePickerState = rememberDatePickerState()
            val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
            val confirmEnabled = derivedStateOf { datePickerState.displayMode != null }
            DatePickerDialog(
                onDismissRequest = {
                    openDialog = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog = false

                            val tempcalendar = Calendar.getInstance()

                            val newDate = LocalDateTime.of(
                                tempcalendar.get(Calendar.YEAR),
                                tempcalendar.get(Calendar.MONTH) + 1,
                                tempcalendar.get(Calendar.DAY_OF_MONTH),
                                tempcalendar.get(Calendar.HOUR_OF_DAY),
                                tempcalendar.get(Calendar.MINUTE),
                                tempcalendar.get(Calendar.SECOND),
                                tempcalendar.get(Calendar.MILLISECOND) * 1_000_000
                            ).atZone(tempcalendar.timeZone.toZoneId())
                                .toLocalDateTime()

                            var date = newDate

                            if (datePickerState.selectedDateMillis != null) {
                                val selectedMillis = datePickerState.selectedDateMillis

                                date =
                                    selectedMillis?.let { millis ->
                                        val calendar = Calendar.getInstance()
                                        calendar.timeInMillis = millis
                                        LocalDateTime.of(
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH) + 1, // Months are 0-based in Calendar
                                            calendar.get(Calendar.DAY_OF_MONTH),
                                            calendar.get(Calendar.HOUR_OF_DAY),
                                            calendar.get(Calendar.MINUTE),
                                            calendar.get(Calendar.SECOND),
                                            calendar.get(Calendar.MILLISECOND) * 1_000_000 // Convert milliseconds to nanoseconds
                                        ).atZone(calendar.timeZone.toZoneId())
                                            .toLocalDateTime()
                                    }
                            }
                            formData.dateOfBirth = date.toString()
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    IconButton(
                        onClick = {
                            openDialog = false
                        }
                    ) {
                        Icon(Icons.Filled.Close, "Close Button")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

//            if (showDatePicker) {
//                DatePickerDialog(
//                    context,
//                    { _, year, month, dayOfMonth ->
//                        formData = formData.copy(dateOfBirth = LocalDate.of(year, month + 1, dayOfMonth))
//                        showDatePicker = false
//                    },
//                    formData.dateOfBirth?.year ?: 1990,
//                    formData.dateOfBirth?.monthValue?.minus(1) ?: 1,
//                    formData.dateOfBirth?.dayOfMonth ?: 1
//                ).show()
//            }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val response = profileViewModel.updateUserProfile(formData)
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save Changes")
            }
        }

    }

}