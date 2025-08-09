package edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.CheckedLogo
import edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.tasks.TaskDescription
import edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.tasks.TaskTitle
import edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage.components.ScheduledPageTitle
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.utils.DateHelper
import edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel.HomeViewModel
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ToDolistDetailViewModel
import kotlinx.coroutines.launch

class ScheduleTask {
    @Composable
    fun TodayToDoListComposable(
        navController: NavController,
        homeViewModel: HomeViewModel = viewModel()
    ) {
        val toDoLists by homeViewModel.toDoLists.collectAsState()

        Box(modifier = UISettings.fullScreenModifier.padding(16.dp)) {
            Column {
                ScheduledPageTitle()

                TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    -10000,
                    -1,
                    "Past Due"
                )
                TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    0,
                    0,
                    "Today"
                )

                TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    1,
                    7,
                    "Week"
                )

                TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    8,
                    30,
                    "Month"
                )

                TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    31,
                    365,
                    "Year"
                )
            }
        }
    }

    @Composable
    fun TaskViewByDayInterval(
        toDoLists: List<ToDoList>,
        homeViewModel: HomeViewModel,
        startInterval: Int,
        dayInterval: Int,
        title: String
    ) {
        val filteredTasks = toDoLists.flatMap { toDoList ->
            toDoList.task.filter { (_, task) ->
                DateHelper.dateIsInInterval(task.date, startInterval, dayInterval)
            }.values
        }

        Column {
            Text(text = title, style = UISettings.scheduledTextStyle)
            Spacer(modifier = Modifier.height(4.dp))

            if (filteredTasks.isNotEmpty()) {
                LazyColumn {
                    items(filteredTasks) { task ->
                        TaskCard(task = task)
                    }
                }
            } else {
                Text(text = "No task", style = UISettings.noScheduledTextStyle)
            }

            Spacer(modifier = Modifier.height(6.dp))
            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(6.dp))
        }
    }

    @Composable
    fun TaskCard(task: Task) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val detailViewModel: ToDolistDetailViewModel = viewModel()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .background(UISettings.secondaryColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CheckedLogo(isDone = task.isDone, onClick = {
                        coroutineScope.launch {
                            val toDoListId = detailViewModel.getTodoListIdOfTask(task.id)
                            Log.d("TaskCard", "toDoListId: $toDoListId")

                            detailViewModel.toggleTaskCompletion(task.id, toDoListId)
                        }
                    })

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        TaskTitle(text = task.title)
                        TaskDescription(text = task.title)
                    }
                }
            }
        }
    }
}