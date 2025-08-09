package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage.ScheduleTask
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel.HomeViewModel

class ShowTaskPage {
    @Composable
    fun ShowTaskComposable(showOnlyCompleted: Boolean){
        val scheduleTask:ScheduleTask = ScheduleTask()
        val homeViewModel: HomeViewModel = viewModel()
        val toDoLists by homeViewModel.toDoLists.collectAsState()



        val filteredTasks = toDoLists.flatMap { toDoList ->
            toDoList.task.filter { (_, task) ->
                !task.isDone
            }.values
        }

        val filteredTasks2 = toDoLists.flatMap { toDoList ->
            toDoList.task.filter { (_, task) ->
                task.isDone && showOnlyCompleted
            }.values
        }


        val filteredTaskFinal = if(showOnlyCompleted) filteredTasks2 else filteredTasks

        Box(modifier = UISettings.fullScreenModifier.padding(16.dp)) {
            LazyColumn {
                items(filteredTaskFinal) {
                    scheduleTask.TaskCard(it)
                }
            }
        }
    }
}