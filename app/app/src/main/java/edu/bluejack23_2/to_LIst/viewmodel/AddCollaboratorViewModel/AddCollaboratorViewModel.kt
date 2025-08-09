package edu.bluejack23_2.to_LIst.viewmodel.AddCollaboratorViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import edu.bluejack23_2.to_LIst.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddCollaboratorViewModel : ViewModel() {
    private val _allUser = MutableStateFlow<List<User>>(emptyList())
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users
    var userInput by mutableStateOf("")

    init {
        this.fetchUser()
    }

    fun valueChanged(input: String) {
        this.userInput = input
    }

    private fun fetchUser() {
        UserRepositories.fetchUser(this._users)
        _allUser.value = this._users.value
    }

    private fun addCollaboratorValidation(): Response<Boolean> {
        this._users.value.forEach { user ->
            if (user.email == userInput) return Response(true, "User is found", true)
        }

        return Response(false, "User is not found", false)
    }

    suspend fun addCollaboratorToDoList(toDoList: ToDoList): Response<Boolean> {
        val validationResult = addCollaboratorValidation()

        if (validationResult.data == false) return validationResult

        val result = ToDoListRepositories.addCollaborator(this.userInput, toDoList)

        return Response(result, "$result adding collaborator", result)
    }
}