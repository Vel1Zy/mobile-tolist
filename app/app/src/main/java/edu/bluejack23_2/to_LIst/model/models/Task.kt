package edu.bluejack23_2.to_LIst.model.models

import java.time.LocalDate
import java.time.LocalDateTime

class Task(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var date: String = LocalDate.now().toString(),
    var category: String = "",
    var isDone: Boolean = false,
    var doneBy: String? = null,
    var createdBy: String = "",
    var subToDoList: MutableList<ToDoList> = mutableListOf(),
    var imageLink: String? = null
) {

    // Constructor for creating a task without an image link
    constructor(
        id: String,
        title: String,
        description: String,
        category: String,
        createdBy: String,
        date: String
    ) : this(
        id,
        title,
        description,
        date,
        category,
        false,
        null,
        createdBy,
        mutableListOf(),
        null
    )
}
