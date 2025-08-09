package edu.bluejack23_2.to_LIst.ui.interfaces

import edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.tasks.getCurrentDateWithTemplate
import java.time.LocalDateTime

class TaskCreate(
    var title: String = "",
    var description: String = "",
    var category: String = "",
    var deadline: String = "",
    var createdBy: String = ""
) {
    fun withTitle(titleInput: String): TaskCreate {
        return TaskCreate(
            titleInput,
            this.description,
            this.category,
            this.deadline,
            this.createdBy
        )
    }

    fun withDescription(descriptionInput: String): TaskCreate {
        return TaskCreate(
            this.title,
            descriptionInput,
            this.category,
            this.deadline,
            this.createdBy
        )
    }

    fun withCategory(categoryInput: String): TaskCreate {
        return TaskCreate(
            this.title,
            this.description,
            categoryInput,
            this.deadline,
            this.createdBy
        )
    }

    fun withDeadline(deadlineInput: String): TaskCreate {
        return TaskCreate(
            this.title,
            this.description,
            this.category,
            deadlineInput,
            this.createdBy
        )
    }

    fun withCreatedBy(email: String): TaskCreate {
        return TaskCreate(
            this.title,
            this.description,
            this.category,
            this.deadline,
            email
        )
    }
}