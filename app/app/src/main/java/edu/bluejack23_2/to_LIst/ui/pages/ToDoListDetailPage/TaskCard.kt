package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.CheckedLogo
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ToDolistDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun TaskCard(
    task: Task,
    detailViewModel: ToDolistDetailViewModel = viewModel(),
    toDoListId: String
) {
    val coroutineScope = rememberCoroutineScope()
    val backgroundColor = if (task.isDone) Color.Green else Color.Red
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp)
        .background(UISettings.secondaryColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CheckedLogo(isDone = task.isDone, onClick = {
                    coroutineScope.launch {
                        detailViewModel.toggleTaskCompletion(task.id, toDoListId)
                    }
                })
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = task.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Text(
                        text = task.description,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = task.date,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )


                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
