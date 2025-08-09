package edu.bluejack23_2.to_LIst.ui.pages.DeveloperPage.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.bluejack23_2.to_LIst.utils.Tools
import java.time.LocalDate


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DatePickerComposable(){

    var dateResult by remember { mutableStateOf("Date Of Birth") }
    var openDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        OutlinedTextField(
            value = dateResult,
            onValueChange = {},
            label = { Text("Date Of Birth") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { openDialog = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Date Picker")
                }
            }
        )
    }

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
                        var date = "no selection"
                        if(datePickerState.selectedDateMillis != null){
                            date = Tools.convertLongtoTime(datePickerState.selectedDateMillis!!)
                        }
                        dateResult = date
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                IconButton(
                    onClick = {
                        openDialog= false
                    }
                ) {
                    Icon(Icons.Filled.Close , "Close Button")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }


}
