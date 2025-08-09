package edu.bluejack23_2.to_LIst.ui.pages.TaskDetailPage

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.TaskCard
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ToDolistDetailViewModel

class TaskDetailPage {

    @Composable
    fun TaskDetailComposable(navController: NavController, toDoListID: String?, category: String?) {
        if (toDoListID == null || category == null) return
        val detailViewModel: ToDolistDetailViewModel = viewModel()
        val toDoListState by detailViewModel.toDoList.collectAsState()
        var completedShowing by remember {
            mutableStateOf(false)
        }

        detailViewModel.fetchToDoList(toDoListID)

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            Column {
                Text(text = category, fontWeight = FontWeight.Bold, color = UISettings.primaryColor, fontSize = UISettings.titleFontSizeSecondary)

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    toDoListState?.let { toDoList ->
                        OutlinedButton(
                            onClick = { completedShowing = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = UISettings.primaryColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "View Completed",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            )
                        }

                        if (completedShowing) {
                            CompletedTask(
                                tasks = toDoList.task,
                                dismiss = { completedShowing = false },
                                toDoListID = toDoList.id,
                                category
                            )
                        }

                        toDoList.task.forEach {
                            if (it.value.category == category && !it.value.isDone) {
                                TaskCard(
                                    task = it.value,
                                    detailViewModel = detailViewModel,
                                    toDoListId = toDoListID
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CompletedTask(
        tasks: MutableMap<String, Task>,
        dismiss: () -> Unit,
        toDoListID: String,
        category: String
    ) {
        FullScreenDialog(onDismiss = { dismiss() }) {
            Scaffold(topBar = {
                Column{
                    CenterAlignedTopAppBar(
                        title = { Text("Completed") },
                        navigationIcon = {
                            IconButton(onClick = { dismiss() }) {
                                Icon(Icons.Filled.Close, contentDescription = "Close")
                            }
                        }
                    )
                    Divider(color = Color.Gray, thickness = (0.5).dp)
                }
            }) {
                val tempPadding = it
                Box(modifier = Modifier
                    .fillMaxWidth().fillMaxHeight()
                    .padding(tempPadding)
                    .background(
                        color = UISettings.secondaryColor,
                        shape = MaterialTheme.shapes.small
                    ),) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        tasks.forEach {
                            if (it.value.isDone && it.value.category == category) TaskCard(
                                task = it.value,
                                toDoListId = toDoListID
                            )
                        }
                    }
                }
            }
        }
    }
}