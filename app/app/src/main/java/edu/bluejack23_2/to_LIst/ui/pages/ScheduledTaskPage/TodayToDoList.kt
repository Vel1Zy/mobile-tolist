package edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel.HomeViewModel

class TodayToDoList {

    @Composable
    fun TodayToDoListComposable(homeViewModel: HomeViewModel = viewModel()) {
        val scheduleTask = ScheduleTask()
        val toDoLists by homeViewModel.toDoLists.collectAsState()

        Box(modifier = Modifier.fillMaxSize().padding(16.dp)){
            scheduleTask.TaskViewByDayInterval(
                toDoLists = toDoLists,
                homeViewModel = homeViewModel,
                startInterval = 0,
                dayInterval = 0,
                title = "Today"
            )
        }

        @Composable
        fun TodayToDoListComposable(homeViewModel: HomeViewModel = viewModel()) {
            val scheduleTask = ScheduleTask()
            val toDoLists by homeViewModel.toDoLists.collectAsState()

            Box(modifier = Modifier.fillMaxSize().padding(16.dp)){
                scheduleTask.TaskViewByDayInterval(
                    toDoLists = toDoLists,
                    homeViewModel = homeViewModel,
                    startInterval = 0,
                    dayInterval = 0,
                    title = "Today"
                )
            }

        }
    }
}