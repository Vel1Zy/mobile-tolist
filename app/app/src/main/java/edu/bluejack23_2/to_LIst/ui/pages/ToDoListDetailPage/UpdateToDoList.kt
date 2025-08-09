package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ToDolistDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun UpdateToDoList(
    detailViewModel: ToDolistDetailViewModel,
    dismiss: () -> Unit,
    toDoList: ToDoList
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    FullScreenDialog(onDismiss = { dismiss() }) {
        Box(modifier = UISettings.fullScreenModifier.padding(16.dp)) {
            Column {
                OutlinedTextField(
                    label = {
                        Text(text = "New Title")},
                    value = detailViewModel.toDoListUpdate.title,
                    onValueChange = { input -> detailViewModel.updateTitle(input) },
                    modifier = Modifier.fillMaxWidth()
                    )

                OutlinedTextField(
                    label = {
                        Text(text = "New Description")},
                    value = detailViewModel.toDoListUpdate.description,
                    onValueChange = { input -> detailViewModel.updateDescription(input) }
                ,modifier = Modifier.fillMaxWidth()
                )


                Row {
                    OutlinedButton(onClick = {
                        coroutineScope.launch {
                            val response = detailViewModel.updateToDoList(toDoList)
                            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()

                            if (response.data == true) dismiss()
                        }
                    },
                        modifier = Modifier
                                .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        ) {
                        Text(text = "Update")
                    }

                    OutlinedButton(onClick = { dismiss() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                    ){
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}