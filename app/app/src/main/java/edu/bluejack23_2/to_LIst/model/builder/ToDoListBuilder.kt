package edu.bluejack23_2.to_LIst.model.builder

import edu.bluejack23_2.to_LIst.model.models.ToDoList
import edu.bluejack23_2.to_LIst.model.repositories.ToDoListRepositories
import java.util.UUID

object ToDoListBuilder {
    fun createToDoList(
        title: String,
        description: String,
        madeBy: String,
        isPersonal: Boolean
    ): ToDoList {
        val toDoList: ToDoList =
            ToDoList(UUID.randomUUID().toString(), madeBy, isPersonal, title, description)
        return toDoList
    }
}