package edu.bluejack23_2.to_LIst.viewmodel.HomeViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _toDoLists = MutableStateFlow<List<ToDoList>>(emptyList())
    val toDoLists: StateFlow<List<ToDoList>> get() = _toDoLists

    init {
        this.fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            ToDoListRepositories.fetchUser(_toDoLists)
        }
    }
}