package edu.bluejack23_2.to_LIst.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.bluejack23_2.to_LIst.ui.navbar.BottomNavigationBar
import edu.bluejack23_2.to_LIst.ui.navbar.CircularFab
import edu.bluejack23_2.to_LIst.ui.pages.CategoriesPage.CategoriesPage
import edu.bluejack23_2.to_LIst.ui.pages.CreateToDoList.CreateToDoList
import edu.bluejack23_2.to_LIst.ui.pages.HomePage.HomePage
import edu.bluejack23_2.to_LIst.ui.pages.LandingPage.LandingPage
import edu.bluejack23_2.to_LIst.ui.pages.LoginPage.LoginPage
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.ProfilePage
import edu.bluejack23_2.to_LIst.ui.pages.RegisterPage.RegisterPage
import edu.bluejack23_2.to_LIst.ui.pages.TaskDetailPage.TaskDetailPage
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.ToDoListDetail
import edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage.ScheduleTask
import edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage.TodayToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.ShowTaskPage
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel

@Preview
@Composable
fun ToLIstApp() {
    val landingPage = LandingPage()
    val loginPage = LoginPage()
    val registerPage = RegisterPage()
    val createToDolistPage = CreateToDoList()
    val profilePage = ProfilePage()
    val homePage = HomePage()
    val toDoListDetailPage = ToDoListDetail()
    val taskDetail = TaskDetailPage()
    val scheduleTask = ScheduleTask()
    val todayToDoList = TodayToDoList()
    val categoryPage = CategoriesPage()
    val showTaskPage = ShowTaskPage()
    val lifecycleOwner = LocalLifecycleOwner.current

    val isLoggedIn by GlobalViewModel.isLoggedIn.collectAsStateWithLifecycle(lifecycleOwner)

    var isLoggedInState by remember { mutableStateOf(isLoggedIn) }

    LaunchedEffect(isLoggedIn) {
        isLoggedInState = isLoggedIn
    }

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val fabOffset = -(screenWidth / 2) + 50.dp

    Scaffold(
        bottomBar = {
            if (isLoggedInState) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentBackStackEntry?.destination?.route
                )
            }
        },

        floatingActionButton = {
            if (isLoggedInState) {
                CircularFab(
                    onClick = { navController.navigate("create-to-do-list") },
                    modifier = Modifier
                        .offset(x = fabOffset, y = 50.dp)
                        .size(70.dp)
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = "landing"
            ) {
                composable("landing") { landingPage.LandingComposable(navController) }
                composable("show-completed") {showTaskPage.ShowTaskComposable(true)}
                composable("show-all") {showTaskPage.ShowTaskComposable(false)}
                composable("login") { loginPage.LoginComposable(navController) }
                composable("register") { registerPage.RegisterComposable(navController) }
                composable("create-to-do-list") { createToDolistPage.CreateToDoListComposable(navController) }
                composable("profile") { profilePage.ProfileComposable(navController) }
                composable("home") { homePage.HomePageComposable(navController) }
                composable("category") { categoryPage.CategoriesPageComposable(navController) }
                composable("schedule-to-do-list") { scheduleTask.TodayToDoListComposable(navController) }

                composable("today-to-do-list") { todayToDoList.TodayToDoListComposable() }
                composable("to-do-list/{toDoListId}") { currentBackStackEntry ->
                    toDoListDetailPage.ToDoListPage(
                        navController,
                        currentBackStackEntry.arguments?.getString("toDoListId")
                    )
                }
                composable("to-do-list/{toDoListId}/{category}") { currentBackStackEntry ->
                    taskDetail.TaskDetailComposable(
                        navController, toDoListID = currentBackStackEntry.arguments?.getString(
                            "toDoListId"
                        ), category = currentBackStackEntry.arguments?.getString("category")
                    )
                }
            }
        }
    }
}