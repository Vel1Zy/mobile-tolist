package edu.bluejack23_2.to_LIst.model.models

class ToDoList(
    var id: String = "",
    var task: MutableMap<String, Task> = mutableMapOf(),
    var madeBy: String = "",
    var collaborators: MutableMap<String, String> = mutableMapOf(),
    var isPersonal: Boolean = false,
    var categories: MutableMap<String, String> = mutableMapOf(),
    var title: String = "",
    var description: String = ""
) {
    // Constructor for making first time
    constructor(
        id: String,
        madeBy: String,
        isPersonal: Boolean,
        title: String,
        description: String
    ) : this(
        id,
        mutableMapOf(),
        madeBy,
        mutableMapOf(),
        isPersonal,
        mutableMapOf(),
        title,
        description
    )

    constructor(
        id: String,
        task: MutableMap<String, Task>,
        madeBy: String,
        title: String,
        description: String,
        collaborators: MutableMap<String, String>,
        isPersonal: Boolean,
        categories: MutableMap<String, String>
    ) : this(id, task, madeBy, collaborators, isPersonal, categories, title, description)
}
