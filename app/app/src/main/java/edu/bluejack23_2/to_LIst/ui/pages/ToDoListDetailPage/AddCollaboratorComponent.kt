package edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.ui.pages.ProfilePage.components.FullScreenDialog
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.AddCollaboratorViewModel.AddCollaboratorViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCollaborator(operation: () -> Unit, toDoList: ToDoList) {
    val addCollaboratorViewModel: AddCollaboratorViewModel = viewModel()
    val users by addCollaboratorViewModel.users.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var expanded by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    FullScreenDialog(onDismiss = { operation() }) {
        Scaffold(
            topBar = {
                Column{
                    CenterAlignedTopAppBar(
                        title = { Text("Add New Collaborator") },
                        navigationIcon = {
                            IconButton(onClick = { operation }) {
                                Icon(Icons.Filled.Close, contentDescription = "Close")
                            }
                        }
                    )
                    Divider(color = Color.Gray, thickness = (0.5).dp)
                }
            }
        ) {innerPadding->
            Box(modifier = UISettings.fullScreenModifier.padding(innerPadding)) {
                Column {

                    OutlinedTextField(
                        value = addCollaboratorViewModel.userInput, onValueChange = {
                            addCollaboratorViewModel.valueChanged(it)
                        },
                        modifier = Modifier.fillMaxWidth()
                        , label = { Text(text = "User Email")}
                    )

//                    Spacer(modifier = Modifier.width(20.dp))

                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = UISettings.primaryColor,
                            contentColor = Color.White

                        )
                    ) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Find")
                        Text(
                            text = "   Find",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        )
                    }


                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        users.forEach {
                            if (it.email.contains(
                                    addCollaboratorViewModel.userInput,
                                    ignoreCase = true
                                )
                            ) {
                                DropdownMenuItem(
                                    text = { Text(it.email) },
                                    onClick = {
                                        addCollaboratorViewModel.userInput = it.email
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                val response = addCollaboratorViewModel.addCollaboratorToDoList(toDoList)
                                Toast.makeText(context, response.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = UISettings.primaryColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Add",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        )
                    }
                }
            }


        }


    }
}