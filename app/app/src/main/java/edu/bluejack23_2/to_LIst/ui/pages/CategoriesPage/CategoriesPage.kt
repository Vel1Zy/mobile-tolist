package edu.bluejack23_2.to_LIst.ui.pages.CategoriesPage

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.pages.HomePage.components.HomeToDoListView
import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.CategorySelect
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel.HomeViewModel

class CategoriesPage {
    @Composable
    fun CategoriesPageComposable(navController: NavController) {
        var homeViewModel: HomeViewModel = viewModel()
        val toDoLists by homeViewModel.toDoLists.collectAsState()



        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()){

            Column {
                Spacer(modifier = Modifier.height(32.dp))

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)) {
                    Text(text = "Categories", fontWeight = FontWeight.Bold, color = UISettings.primaryColor, fontSize = UISettings.titleFontSizeSecondary)
                }
                if(toDoLists.isEmpty()){
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.Center) {

                        Text("Empty list", style = TextStyle(fontSize = 24.sp))

                    }
                }
                else{
                    LazyColumn {
                        items(toDoLists) {
                            HomeToDoListView(navController = navController, toDoList = it)
                        }
                    }
                }
            }

        }


    }
}