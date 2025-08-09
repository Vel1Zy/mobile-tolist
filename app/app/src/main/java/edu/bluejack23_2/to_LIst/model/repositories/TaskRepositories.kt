package edu.bluejack23_2.to_LIst.model.repositories

import android.util.Log
import com.google.firebase.database.DatabaseReference
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import kotlinx.coroutines.tasks.await

object TaskRepositories {
    val databaseReference: DatabaseReference = FirebaseEnum.toDoListDatabaseReference

    suspend fun createTask(toDoListId: String, task: Task): Boolean {
        try {
            this.databaseReference.child(toDoListId).child("task").child(task.id).setValue(task)
                .await()
            return true
        } catch (e: Exception) {
            Log.d("Creating task", e.toString())
            return false
        }
    }

    suspend fun completeTask(taskId: String, toDoListId: String): Boolean {
        try {
            this.databaseReference.child(toDoListId).child("task").child(taskId).child("done")
                .setValue(true)
                .await()
            return true
        } catch (e: Exception) {
            Log.d("Creating task", e.toString())
            return false
        }
    }

    suspend fun toggleTaskCompletion(taskId: String, toDoListId: String): Boolean {
        try {
            val dataSnapshot = this.databaseReference.child(toDoListId).child("task").child(taskId).get().await()
            val task = dataSnapshot.getValue(Task::class.java) ?: return false
            this.databaseReference.child(toDoListId).child("task").child(taskId).child("done")
                .setValue(!task.isDone)
                .await()
            return true
        } catch (e: Exception) {
            Log.d("Creating task", e.toString())
            return false
        }
    }

    suspend fun getTodoListIdOfTask(taskId: String): String {
        try {
            val dataSnapshot = this.databaseReference.get().await()
            for (toDoListSnapshot in dataSnapshot.children) {
                val toDoList = toDoListSnapshot.getValue(ToDoList::class.java) ?: continue
                for (taskMap in toDoList.task) {
                    if (taskMap.key == taskId) {
                        return toDoList.id
                    }
                }
            }
            return ""
        } catch (e: Exception) {
            Log.d("Getting to do list of task", e.toString())
            return ""
        }
    }

    suspend fun updateCategory(
        toDoList: ToDoList,
        oldCategory: String,
        newCategory: String
    ): Boolean {
        try {
            val dataSnapshot = this.databaseReference.child(toDoList.id).child("task").get().await()

            for (taskSnapshot in dataSnapshot.children) {
                val task = taskSnapshot.getValue(Task::class.java) ?: continue
                if (task.category == oldCategory) {
                    this.databaseReference.child(toDoList.id).child("task").child(task.id)
                        .child("category").setValue(newCategory)
                }
            }

            return true
        } catch (e: Exception) {
            Log.d("updating category", e.toString())
            return false
        }
    }


}
