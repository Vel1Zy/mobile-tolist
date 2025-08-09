package edu.bluejack23_2.to_LIst.viewmodel.ToDoListDetailViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.builder.TaskBuilder
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.repositories.TaskRepositories
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import edu.bluejack23_2.to_LIst.ui.interfaces.TaskCreate
import edu.bluejack23_2.to_LIst.ui.interfaces.UpdateToDoList
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ToDolistDetailViewModel : ViewModel() {
    val _toDoList = MutableStateFlow<ToDoList?>(null)
    val toDoList: StateFlow<ToDoList?> get() = _toDoList
    var taskCreate by mutableStateOf(TaskCreate())
    var toDoListUpdate by mutableStateOf(UpdateToDoList())

    fun changeTitle(titleInput: String) {
        this.taskCreate = this.taskCreate.withTitle(titleInput)
    }

    fun changeDescription(descriptionInput: String) {
        this.taskCreate = this.taskCreate.withDescription(descriptionInput)
    }

    fun changeCategory(categoryInput: String) {
        this.taskCreate = this.taskCreate.withCategory(categoryInput)
    }

    fun changeDeadline(deadline: String) {
        this.taskCreate = this.taskCreate.withDeadline(deadline)
    }

    fun changeCreatedBy(createdBy: String) {
        this.taskCreate = this.taskCreate.withCreatedBy(createdBy)
    }

    fun defaultTagIfEmpty() {
        if (this.taskCreate.category == "") {
            this.taskCreate = this.taskCreate.withCategory("Default")
        }
    }

    fun fetchToDoList(id: String) {
        ToDoListRepositories.getToDolistByID(this._toDoList, id)
    }

    fun updateTitle(input: String) {
        this.toDoListUpdate = this.toDoListUpdate.withTitle(input)
    }

    fun updateDescription(input: String) {
        this.toDoListUpdate = this.toDoListUpdate.withDescription(input)
    }

    suspend fun deleteToDoList(toDoList: ToDoList): Response<Boolean> {
        val result = ToDoListRepositories.deleteToDoList(toDoList)
        val message = if (result) "success deleting to do list" else "failed deleting to do list"

        return Response(result, message, result)
    }

    suspend fun createTaskHandle(toDoListId: String): Response<Boolean> {
        if (GlobalViewModel.getUserInformation() == null) {
            Log.d("Creating to do list", "User information is null cannot create")
            return Response(false, "User information is null", false)
        }

        val email = GlobalViewModel.getUserInformation()?.email ?: return Response(
            false,
            "Email is null",
            false
        )

        this.changeCreatedBy(email)
        this.defaultTagIfEmpty()

        Log.d(
            "creating task",
            "${taskCreate.title} ${taskCreate.deadline} ${taskCreate.description} ${taskCreate.category}, ${taskCreate.createdBy}"
        )

        val task = TaskBuilder.createTask(this.taskCreate)

        if (this.toDoList.value != null) {
            var result = TaskRepositories.createTask(toDoListId, task)
            if (!result) {
                return Response(result, "$result creating task", result)
            }

            result = ToDoListRepositories.createCategory(toDoListId, task.category)
            return Response(result, "$result creating task", result)
        }

        return Response(false, "failed creating task", false)
    }

    suspend fun updateToDoList(toDoList: ToDoList): Response<Boolean> {
        toDoList.title = this.toDoListUpdate.title
        toDoList.description = this.toDoListUpdate.description
        val result = ToDoListRepositories.updateToDoList(toDoList)

        val message = if (result) "Success updating to do list" else "failed updating to do list"

        return Response(result, message, result)
    }

    suspend fun setTaskDone(taskId: String, toDoListId: String) {
        Log.d("setTaskDone", "taskId: $taskId, toDoListId: $toDoListId")
//        TaskRepositories.completeTask(taskId, toDoListId)
    }

    suspend fun toggleTaskCompletion(taskId: String, toDoListId: String) {
        Log.d("toggleTaskCompletion", "taskId: $taskId, toDoListId: $toDoListId")
        val res = TaskRepositories.toggleTaskCompletion(taskId, toDoListId)
        Log.d("toggleTaskCompletion", "res: $res")
    }

    suspend fun getTodoListIdOfTask(taskId: String): String {
        return TaskRepositories.getTodoListIdOfTask(taskId)
    }
}