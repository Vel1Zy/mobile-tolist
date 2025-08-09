package edu.bluejack23_2.to_LIst.ui.interfaces

import edu.bluejack23_2.to_LIst.ui.pages.ToDoListDetailPage.UpdateToDoList


class UpdateToDoList(
    var title: String = "",
    var description: String = ""
) {
    fun withTitle(input: String): UpdateToDoList {
        return UpdateToDoList(input, this.description)
    }

    fun withDescription(input: String): UpdateToDoList {
        return UpdateToDoList(this.title, input)
    }
}