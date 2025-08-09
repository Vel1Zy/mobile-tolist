package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import java.util.Vector

@Composable
fun CategorySelect(toDoList: ToDoList, navController: NavController) {
    Column {
        toDoList.categories.forEach {
            CategoryCard(category = it.value, navController, toDoList)
        }
    }
}


@Composable
fun CategoryCard(category: String, navController: NavController, toDoList: ToDoList) {

    Card(
        onClick = { navController.navigate("to-do-list/${toDoList.id}/$category") },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = UISettings.secondaryColor,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        , modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                imageVector = Icons.Default.Category,
                contentDescription = "Category Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between icon and text

            Row(horizontalArrangement = Arrangement.Start,){
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium, // Style the category text
                )
                Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Go to Category",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Trailing Icon (Optional)

        }
    }
}