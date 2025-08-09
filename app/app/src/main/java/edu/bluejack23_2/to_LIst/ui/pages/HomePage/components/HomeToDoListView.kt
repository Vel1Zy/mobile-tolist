package edu.bluejack23_2.to_LIst.ui.pages.HomePage.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import kotlinx.coroutines.launch

@Composable
fun HomeToDoListView(navController: NavController, toDoList: ToDoList) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .border(
                border = BorderStroke(
                    0.5.dp,
                    Color.LightGray
                ),
                shape = MaterialTheme.shapes.extraSmall
            )
            .background(UISettings.secondaryColor)
            .padding(16.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.height(2.dp))
            Column {
                Text(text = toDoList.title, fontSize = 23.sp)
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = toDoList.description, color = Color.Gray)
            }

            Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End){
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            navController.navigate("to-do-list/${toDoList.id}")
                        }
                    } ,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = UISettings.primaryColor,
                        contentColor = UISettings.buttonTextColor,
                        disabledContentColor = UISettings.primaryColor,
                        disabledContainerColor = UISettings.buttonTextColor
                    )
                ) {
                    Text(text = "Details")
                }
            }
        }
    }
}