package edu.bluejack23_2.to_LIst.ui.pages.HomePage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.pages.HomePage.components.HomeToDoListView
import edu.bluejack23_2.to_LIst.ui.pages.HomePage.components.NewTopPart
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel.HomeViewModel
import kotlinx.coroutines.launch

class HomePage {
    @Composable
    fun HomePageComposable(navController: NavController) {
        Box(modifier = UISettings.fullScreenModifier.padding(10.dp)) {
            Column {
                Spacer(modifier = Modifier.height(50.dp))

                NewTopPart(navController = navController)

                Spacer(modifier = Modifier.height(50.dp))

                HomePageToDoListTitle()

                HomePagePreview(navController)
            }
        }
    }

    @Composable
    fun HomePageToDoListTitle(){
        Column (modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)) {
            Text(text = "Categories", fontWeight = FontWeight.Bold, color = UISettings.primaryColor, fontSize = UISettings.titleFontSizeSecondary)
        }
    }

//    @Composable
//    fun TopPart(navController: NavController) {
//        val coroutineScope = rememberCoroutineScope()
//
//        Row {
//            Button(onClick = {
//                coroutineScope.launch {
//                    navController.navigate("schedule-to-do-list")
//                }
//            }) {
//                Text(text = "Schedule")
//            }
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    navController.navigate("today-to-do-list")
//                }
//            }) {
//                Text(text = "Today")
//            }
//        }
//    }

    @Composable
    fun HomePagePreview(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
        val toDoLists by homeViewModel.toDoLists.collectAsState()
//        LazyColumn (
//            modifier = Modifier.fillMaxSize()
//            .border(
//                border = BorderStroke(
//                    0.5.dp,
//                    Color.LightGray
//                ),
//                shape = MaterialTheme.shapes.extraSmall
//            )
//            .padding(16.dp),
//
//        ){
//            items(toDoLists) { toDoList ->
//                HomeToDoListView(navController, toDoList = toDoList)
//                Spacer(modifier = Modifier.height(5.dp))
//            }
//        }

        if (toDoLists.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().border(
                border = BorderStroke(0.5.dp, Color.LightGray),
                shape = MaterialTheme.shapes.extraSmall
            )
                .padding(16.dp), contentAlignment = Alignment.Center) {
                Text("Empty list", style = TextStyle(fontSize = 16.sp))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        border = BorderStroke(0.5.dp, Color.LightGray),
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .padding(16.dp),
            ) {
                items(toDoLists) { toDoList ->
                    HomeToDoListView(navController, toDoList = toDoList)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}