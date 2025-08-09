package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel.ViewCollaboratorViewModel
import kotlinx.coroutines.launch

class ViewCollaborators {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ViewCollaboratorsComposable(dismiss: () -> Unit, toDoList: ToDoList) {
        val viewCollaboratorViewModel: ViewCollaboratorViewModel = viewModel()
        viewCollaboratorViewModel.fetchCollaborators(toDoList.id)



        FullScreenDialog(onDismiss = { dismiss() }) {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text("Collaborator List") },
                    navigationIcon = {
                        IconButton(onClick = { dismiss() }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                )
            }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    CollaboratorView(
                        viewCollaboratorViewModel = viewCollaboratorViewModel,
                        toDoList
                    )
                }
            }
        }
    }

    @Composable
    fun CollaboratorView(viewCollaboratorViewModel: ViewCollaboratorViewModel, toDoList: ToDoList) {
        val collaborators by viewCollaboratorViewModel.collaborators.collectAsState()
        val coroutineScope = rememberCoroutineScope()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(collaborators) {
                    Card() {
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
                        ){
                            Row {
                                Text(text = it, fontSize = 18.sp)
                                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                                    ElevatedButton(
                                        onClick = {
                                            coroutineScope.launch {
                                                viewCollaboratorViewModel.deleteCollaborator(toDoList, it)
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
                                        Text(text = "Remove")
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}