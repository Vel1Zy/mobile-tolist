package edu.bluejack23_2.to_LIst.viewmodel.CreateToDoListViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.builder.ToDoListBuilder
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import edu.bluejack23_2.to_LIst.ui.interfaces.ToDoListCreate
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel

class CreateToDoListViewModel : ViewModel() {
    var toDoListCreate by mutableStateOf(ToDoListCreate())

    fun changeTitle(titleInput: String) {
        this.toDoListCreate = toDoListCreate.withTitle(titleInput)
    }

    fun changeMadeBy(userEmail: String) {
        this.toDoListCreate = toDoListCreate.withMadeBy(userEmail)
    }

    fun changeDescription(descriptionInput: String) {
        this.toDoListCreate = toDoListCreate.withDescription(descriptionInput)
    }

    fun validateToDoListCreation(toDoList: ToDoList): Response<Boolean> {
        var message = ""
        if (toDoList.title.isEmpty()) message = "Title Cannot be Empty"
        else if (toDoList.madeBy.isEmpty()) message = "Made by cannot be empty"
        else if (toDoList.description.isEmpty()) message = "Description cannot be Empty"

        val success = message == ""

        return Response(success, message, success)
    }

    suspend fun buttonClickHandle(): Response<Boolean> {
        if (GlobalViewModel.getUserInformation() == null) {
            Log.d("Creating to do list", "User information is null cannot create")
            return Response(false, "User information is null", false)
        }

        val email = GlobalViewModel.getUserInformation()?.email ?: return Response(
            false,
            "Email is null",
            false
        )

        this.changeMadeBy(email)

        val toDoList = ToDoListBuilder.createToDoList(
            this.toDoListCreate.title,
            this.toDoListCreate.description,
            this.toDoListCreate.madeBy,
            false
        )

        val validationResponse = this.validateToDoListCreation(toDoList)
        if (validationResponse.data == false) return validationResponse

        val response = ToDoListRepositories.createToDoList(toDoList)

        return response
    }
}