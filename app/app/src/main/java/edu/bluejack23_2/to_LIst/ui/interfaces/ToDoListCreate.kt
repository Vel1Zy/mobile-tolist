package edu.bluejack23_2.to_LIst.ui.interfaces

import edu.bluejack23_2.to_LIst.model.models.ToDoList

class ToDoListCreate(
    var title: String = "",
    var description: String = "",
    var madeBy: String = "",
) {
    val isPersonal: Boolean = false
    val collaborators: MutableList<String> = mutableListOf()

    public fun withTitle(titleInput: String): ToDoListCreate {
        return ToDoListCreate(titleInput, this.description, this.madeBy)
    }

    fun withMadeBy(userEmail: String): ToDoListCreate {
        return ToDoListCreate(this.title, this.description, userEmail)
    }

    fun withDescription(descriptionInput: String): ToDoListCreate {
        return ToDoListCreate(this.title, descriptionInput, this.madeBy)
    }
}