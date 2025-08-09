package edu.bluejack23_2.to_LIst.model.repositories

import android.util.Log
import androidx.compose.animation.core.snap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import kotlin.contracts.ExperimentalContracts

object ToDoListRepositories {
    val databaseReference = FirebaseEnum.toDoListDatabaseReference

    suspend fun createToDoList(toDoList: ToDoList): Response<Boolean> {
        try {
            this.databaseReference.child(toDoList.id)
                .setValue(toDoList).await()
            return Response(true, "Success creating to do list", true)
        } catch (e: Exception) {
            Log.d("Creating to do list", e.toString());
            return Response(false, "Failed to do list", false)
        }
    }

    suspend fun deleteToDoList(toDoList: ToDoList): Boolean {
        try {
            this.databaseReference.child(toDoList.id).removeValue().await()
            return true
        } catch (e: Exception) {
            Log.d("deleting to do list", e.toString());
            return false
        }
    }

    fun getToDolistByID(toDoList: MutableStateFlow<ToDoList?>, id: String) {
        this.databaseReference.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val toDoListFind = snapshot.getValue(ToDoList::class.java)
                toDoList.value = toDoListFind
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun createCategory(id: String, category: String): Boolean {
        try {
            this.databaseReference.child(id).child("categories").child(category).setValue(category)
            return true
        } catch (e: Exception) {
            Log.d("error creating category", e.toString())
            return false
        }
    }

    fun updateToDoList(toDoList: ToDoList): Boolean {
        try {
            this.databaseReference.child(toDoList.id).setValue(toDoList)
            return true
        } catch (e: Exception) {
            Log.d("error creating category", e.toString())
            return false
        }
    }

    fun validateToDoList(list: MutableList<ToDoList>, toDoList: ToDoList?) {
        val currentUserEmail = GlobalViewModel.getUserInformation()?.email
        if (toDoList == null) return

        if (currentUserEmail == null) return

        if (toDoList.madeBy == currentUserEmail) {
            list.add(toDoList)
            return
        }

        toDoList.collaborators.forEach { email ->
            if (email.value == currentUserEmail) {
                list.add(toDoList)
                return
            }
        }
    }

    fun fetchUser(toDoLists: MutableStateFlow<List<ToDoList>>) {
        this.databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (GlobalViewModel.getUserInformation() == null) return
                val listOfToDoList = mutableListOf<ToDoList>()

                for (toDoListSnapshot in snapshot.children) {
                    val toDoList = toDoListSnapshot.getValue(ToDoList::class.java)
                    validateToDoList(listOfToDoList, toDoList)
                }

                toDoLists.value = listOfToDoList
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }

    fun getCollaborator(collaborators: MutableStateFlow<List<String>>, toDoListId: String) {
        this.databaseReference.child(toDoListId).child("collaborators")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (GlobalViewModel.getUserInformation() == null) return
                    val listOfUser = mutableListOf<String>()

                    for (snapshotChild in snapshot.children) {
                        val email = snapshotChild.getValue(String::class.java) ?: continue
                        listOfUser.add(email)
                    }

                    collaborators.value = listOfUser
                }


                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }
            })
    }


    suspend fun updateCategory(
        toDoList: ToDoList,
        oldCategory: String,
        newCategory: String
    ): Boolean {
        try {
            this.databaseReference.child(toDoList.id).child("category").child(oldCategory)
                .removeValue().await()

            val updateCategoryResult =
                TaskRepositories.updateCategory(toDoList, oldCategory, newCategory)

            return updateCategoryResult
        } catch (e: Exception) {
            Log.d("removing category", e.toString())
            return false
        }
    }

    suspend fun addCollaborator(collaboratorEmail: String, toDoList: ToDoList): Boolean {
        try {
            this.databaseReference.child(toDoList.id).child("collaborators")
                .child(collaboratorEmail.split("@")[0])
                .setValue(collaboratorEmail).await()
            return true
        } catch (e: Exception) {
            Log.d("add collaborator", e.toString())
            return false
        }
    }

    suspend fun deleteCollaborator(toDoList: ToDoList, collaboratorEmail: String): Boolean {
        val childKey = collaboratorEmail.split("@")[0]

        try {
            this.databaseReference.child(toDoList.id).child("collaborators").child(childKey)
                .removeValue().await()
            return true
        } catch (e: Exception) {
            Log.d("removing collaborator", e.toString())
            return false
        }
    }
}