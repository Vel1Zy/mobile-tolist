package edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel

import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewCollaboratorViewModel : ViewModel() {
    private val _collaborators = MutableStateFlow<List<String>>(emptyList())
    val collaborators: StateFlow<List<String>> get() = _collaborators

    suspend fun deleteCollaborator(toDoList: ToDoList, email: String) {
        ToDoListRepositories.deleteCollaborator(toDoList = toDoList, email)
    }

    fun fetchCollaborators(toDoListId: String) {
        ToDoListRepositories.getCollaborator(_collaborators, toDoListId)
    }
}