package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.components.DeleteToDoListButton
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.components.UpdateToDoListButton
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ToDolistDetailViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar

class ToDoListDetail {

    @Composable
    fun ToDoListPage(navController: NavController, toDoListId: String?) {
        Box(modifier = UISettings.fullScreenModifier) {
            if (toDoListId != null) {
                val detailViewModel: ToDolistDetailViewModel = viewModel()
                val toDoListState by detailViewModel.toDoList.collectAsState()
                detailViewModel.fetchToDoList(toDoListId)

                toDoListState?.let { toDoList ->
                    DetailTop(toDoList, navController)
                }
            }
        }
    }

    @Composable
    fun DetailTop(toDoList: ToDoList, navController: NavController) {
        val context = LocalContext.current
        val detailViewModel: ToDolistDetailViewModel = viewModel()
        var showDialog by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        var collaborator by remember {
            mutableStateOf(false)
        }
        val viewCollaborators = ViewCollaborators()

        var viewCollaborator by remember {
            mutableStateOf(false)
        }

        var updatingToDoList by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = toDoList.title,
                                    fontWeight = FontWeight.Bold,
                                    color = UISettings.primaryColor,
                                    fontSize = UISettings.titleFontSizeSecondary
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = toDoList.description, fontSize = 18.sp
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                UpdateToDoListButton(onClick = { updatingToDoList = true })
                                Spacer(modifier = Modifier.width(10.dp))
                                DeleteToDoListButton(onClick = {
                                    coroutineScope.launch {
                                        val response = detailViewModel.deleteToDoList(toDoList)
                                        Toast.makeText(
                                            context,
                                            response.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        if (response.data == true) {
                                            navController.navigate("home")
                                        }
                                    }
                                })
                            }
                        }

//                        Spacer(modifier = Modifier.height(5.dp))
//                        HorizontalDivider()

                        if (collaborator) AddCollaborator({ collaborator = false }, toDoList)
                        if (viewCollaborator) viewCollaborators.ViewCollaboratorsComposable({
                            viewCollaborator = false
                        }, toDoList)
                        if (updatingToDoList) UpdateToDoList(
                            detailViewModel = detailViewModel,
                            dismiss = { updatingToDoList = false },
                            toDoList = toDoList
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                Canvas(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val width = size.width
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, size.height),
                        end = androidx.compose.ui.geometry.Offset(width, size.height),
                        strokeWidth = 2.dp.toPx()
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                    ElevatedButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonColors(
                            containerColor = UISettings.primaryColor,
                            contentColor = UISettings.buttonTextColor,
                            disabledContentColor = UISettings.primaryColor,
                            disabledContainerColor = UISettings.buttonTextColor
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Create New Task   ",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "+",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                // show dialog
                if (showDialog) {
                    MinimalDialog(onDismissRequest = {
                        showDialog = false
                    }, toDoList = toDoList, navController)
                }

                CategorySelect(toDoList = toDoList, navController)
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun MinimalDialog(
        onDismissRequest: () -> Unit,
        toDoList: ToDoList,
        navController: NavController
    ) {
        val coroutineScope = rememberCoroutineScope()
        val detailViewModel: ToDolistDetailViewModel = viewModel()
        var expanded by remember {
            mutableStateOf(false)
        }
        var openDialog by remember { mutableStateOf(false) }
        val dateState = rememberDatePickerState()
        val context = LocalContext.current

        FullScreenDialog({ expanded = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                Scaffold(
                    bottomBar = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ElevatedButton(
                                onClick = {
                                    coroutineScope.launch {
                                        val response = detailViewModel.createTaskHandle(toDoList.id)
                                        Toast.makeText(
                                            context,
                                            response.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        if (response.success) {
                                            navController.navigate("home")
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonColors(
                                    containerColor = UISettings.primaryColor,
                                    contentColor = UISettings.buttonTextColor,
                                    disabledContentColor = UISettings.primaryColor,
                                    disabledContainerColor = UISettings.buttonTextColor
                                ),
                                modifier = Modifier.width(100.dp)
                            ) {
                                Text(text = "Add")
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            ElevatedButton(
                                onClick = { onDismissRequest() },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonColors(
                                    containerColor = UISettings.primaryColor,
                                    contentColor = UISettings.buttonTextColor,
                                    disabledContentColor = UISettings.primaryColor,
                                    disabledContainerColor = UISettings.buttonTextColor
                                ),
                                modifier = Modifier.width(100.dp)
                            ) {
                                Text(text = "Close")
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(20.dp)) {
                        Column(modifier = Modifier.padding(innerPadding)) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "Create New Task",
                                fontWeight = FontWeight.Bold,
                                color = UISettings.primaryColor,
                                fontSize = UISettings.titleFontSizeSecondary
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = detailViewModel.taskCreate.title,
                                onValueChange = { value -> detailViewModel.changeTitle(value) },
                                label = { Text(text = "Title") }
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                value = detailViewModel.taskCreate.description,
                                onValueChange = { value -> detailViewModel.changeDescription(value) },
                                label = { Text(text = "Description") }
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                            Column {
                                OutlinedTextField(
                                    value = detailViewModel.taskCreate.category,
                                    onValueChange = { value ->
                                        detailViewModel.changeCategory(
                                            value
                                        )
                                    }, label = { Text(text = "Tag") },
                                    modifier = Modifier.fillMaxWidth(),
                                    trailingIcon = {
                                        IconButton(onClick = { expanded = !expanded }) {
                                            Icon(
                                                imageVector = Icons.Default.MoreVert,
                                                contentDescription = "More"
                                            )
                                        }
                                    }
                                )
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }) {
                                    val categories = toDoList.categories
                                    if (categories.isEmpty()) {
                                        categories["Default"] = "Default"
                                    }

                                    toDoList.categories.forEach {
                                        DropdownMenuItem(
                                            text = { Text(it.value) },
                                            onClick = {
                                                detailViewModel.changeCategory(it.value)
                                            }
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = detailViewModel.taskCreate.deadline,
                                    onValueChange = {},
                                    label = { Text("Due Date") },
                                    readOnly = true,
                                    trailingIcon = {
                                        IconButton(onClick = { openDialog = true }) {
                                            Icon(
                                                Icons.Filled.DateRange,
                                                contentDescription = "Date Picker"
                                            )
                                        }
                                    }
                                )
                            }

                            // urusan wing
//                            DatePicker(state = dateState, showModeToggle = true)
                            if (openDialog) {
                                val datePickerState = rememberDatePickerState()
                                val state =
                                    rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                                val confirmEnabled =
                                    derivedStateOf { datePickerState.displayMode != null }
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
                                                    val selectedMillis =
                                                        datePickerState.selectedDateMillis

                                                    // Convert the selected date to LocalDate
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

                                                detailViewModel.taskCreate.deadline =
                                                    date.toString()
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
                        }
                    }
                }
            }
        }
    }
}