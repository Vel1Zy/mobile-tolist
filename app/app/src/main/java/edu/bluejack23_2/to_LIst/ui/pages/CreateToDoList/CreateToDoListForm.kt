package edu.bluejack23_2.to_LIst.ui.pages.CreateToDoList

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.theme.UISettings
import edu.bluejack23_2.to_LIst.viewmodel.CreateToDoListViewModel.CreateToDoListViewModel
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun FormCreateToDoList(navController: NavController) {
    val createViewModel: CreateToDoListViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(20.dp))

    Column (modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)) {
        Text(text = "Create To Do List Category", fontWeight = FontWeight.Bold, color = UISettings.primaryColor, fontSize = UISettings.titleFontSizeSecondary)
    }

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = createViewModel.toDoListCreate.title,
        onValueChange = { input -> createViewModel.changeTitle(input) },
        label = { Text(text = "Category Title") }, modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = createViewModel.toDoListCreate.description,
        onValueChange = { input -> createViewModel.changeDescription(input) },
        label = { Text(text = "Category Description") },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    )

    Spacer(modifier = Modifier.height(10.dp))

    ElevatedButton(onClick = {
        coroutineScope.launch {
            val response = createViewModel.buttonClickHandle()
            Toast.makeText(
                context,
                response.message,
                Toast.LENGTH_SHORT
            ).show()

            if (response.success) {
                // Navigate to home page
                navController.navigate("home")
            }
        }
    },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = UISettings.primaryColor,
            contentColor = UISettings.buttonTextColor,
            disabledContentColor = UISettings.primaryColor,
            disabledContainerColor = UISettings.buttonTextColor
        )) {
        Text(text = "Create New To-Do List Category")
    }
}